package com.team10.preproject.studycomment.service;

import com.team10.preproject.global.exception.BusinessLogicException;
import com.team10.preproject.global.exception.ExceptionCode;
import com.team10.preproject.member.entity.Member;
import com.team10.preproject.member.repository.MemberRepository;
import com.team10.preproject.study.repository.StudyRepository;
import com.team10.preproject.studycomment.dto.StudyCommentRequestDto;
import com.team10.preproject.studycomment.entity.DeleteStatus;
import com.team10.preproject.studycomment.entity.StudyComment;
import com.team10.preproject.studycomment.entity.StudyCommentLike;
import com.team10.preproject.studycomment.repository.StudyCommentLikeRepository;
import com.team10.preproject.studycomment.repository.StudyCommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StudyCommentService {

    private StudyCommentRepository studyCommentRepository;
    private StudyRepository studyRepository;
    private MemberRepository memberRepository;
    private StudyCommentLikeRepository studyCommentLikeRepository;

    public StudyCommentService(StudyCommentRepository studyCommentRepository, StudyRepository studyRepository,
                               MemberRepository memberRepository, StudyCommentLikeRepository studyCommentLikeRepository) {
        this.studyCommentRepository = studyCommentRepository;
        this.studyRepository = studyRepository;
        this.memberRepository = memberRepository;
        this.studyCommentLikeRepository = studyCommentLikeRepository;
    }

    @Transactional
    public StudyComment commentWrite(Long memberId, Long studyId, StudyCommentRequestDto requestDto) {

        studyRepository.findById(studyId).orElseThrow(() -> {
            return new BusinessLogicException(ExceptionCode.NoSuchElementException);
        });

        StudyComment studyComment = studyCommentRepository.save(
                StudyComment.createStudyComment(requestDto.getComment(),
                        studyRepository.findById(studyId).orElseThrow(IllegalArgumentException::new),
                        memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new),
                        requestDto.getParentId() != null ?
                                studyCommentRepository.findById(requestDto.getParentId())
                                        .orElseThrow(IllegalArgumentException::new) : null)
        );
        return studyComment;
    }

    @Transactional
    public void commentDelete(Long studyCommentId) {

        StudyComment studyComment = studyCommentRepository.findById(studyCommentId)
                .orElseThrow(() -> {
                    return new BusinessLogicException(ExceptionCode.NoSuchElementException);
                });

        if (studyComment.getParent() == null &&
                studyCommentRepository.findByIsDeleted(studyComment.getStudyCommentId()).size() == 0) {

            studyCommentRepository.delete(studyComment);

        } else if (studyComment.getIsDeleted() == DeleteStatus.Y &&
        studyCommentRepository.findByIsDeletedNotIn(studyComment.getParent().getStudyCommentId(), studyCommentId)
                .size() == 0) {

            studyCommentRepository.delete(studyComment.getParent());

        } else if (studyComment.getParent() != null &&
                studyCommentRepository.findByLast(studyComment.getParent().getStudyCommentId())) {

            studyCommentRepository.delete(studyComment.getParent());

        } else {
            studyComment.changeDeletedStatus(DeleteStatus.Y);
        }
    }

    @Transactional
    public StudyComment commentUpdate(Long studyCommentId, StudyComment requestComment) {

        StudyComment studyComment = studyCommentRepository.findById(studyCommentId)
                .orElseThrow(() -> {
                    return new BusinessLogicException(ExceptionCode.NoSuchElementException);
                });

        studyComment.setComment(requestComment.getComment());

        return studyCommentRepository.save(studyComment);
    }

    @Transactional
    public void commentLike(Long studyCommentId, Long memberId) {

        StudyComment studyComment = studyCommentRepository.findById(studyCommentId)
                .orElseThrow(() -> {
                    return new BusinessLogicException(ExceptionCode.NoSuchElementException);
                });
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> {
                    return new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
                });

        Optional<StudyCommentLike> commentAndMember =
                studyCommentLikeRepository.findByStudyCommentAndMember(studyComment, member);

        commentAndMember.ifPresentOrElse(

                studyCommentLike -> {
                    studyCommentLikeRepository.delete(studyCommentLike);
                    studyComment.discountLike(studyCommentLike);
                    studyComment.updateLikeCount();
                },
                () -> {
                    StudyCommentLike studyCommentLike = StudyCommentLike.builder().build();
                    studyCommentLike.mappingStudyComment(studyComment);
                    studyCommentLike.mappingMember(member);
                    studyComment.updateLikeCount();
                    studyCommentLikeRepository.save(studyCommentLike);
                }
        );
    }
}
