package com.team10.preproject.study.service;

import com.team10.preproject.global.exception.BusinessLogicException;
import com.team10.preproject.global.exception.ExceptionCode;
import com.team10.preproject.member.entity.Member;
import com.team10.preproject.member.repository.MemberRepository;
import com.team10.preproject.study.dto.StudyCommentChildrenResponse;
import com.team10.preproject.study.dto.StudyOneResponse;
import com.team10.preproject.study.entity.Study;
import com.team10.preproject.study.entity.StudyLike;
import com.team10.preproject.study.repository.StudyLikeRepository;
import com.team10.preproject.study.repository.StudyRepository;
import com.team10.preproject.studycomment.repository.StudyCommentLikeRepository;
import com.team10.preproject.studycomment.repository.StudyCommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
public class StudyService {

    private MemberRepository memberRepository;
    private StudyRepository studyRepository;
    private StudyCommentLikeRepository studyCommentLikeRepository;
    private StudyCommentRepository studyCommentRepository;
    private StudyLikeRepository studyLikeRepository;

    public StudyService(MemberRepository memberRepository, StudyRepository studyRepository,
                        StudyCommentLikeRepository studyCommentLikeRepository,
                        StudyCommentRepository studyCommentRepository, StudyLikeRepository studyLikeRepository) {
        this.memberRepository = memberRepository;
        this.studyRepository = studyRepository;
        this.studyCommentLikeRepository = studyCommentLikeRepository;
        this.studyCommentRepository = studyCommentRepository;
        this.studyLikeRepository = studyLikeRepository;
    }

    @Transactional
    public Study studyWrite(Study study, Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> {
            return new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        });

        return studyRepository.save(study);
    }

    @Transactional(readOnly = true)
    public Page<Study> studyList(Pageable pageable) {

        return studyRepository.findAll(pageable);
    }

    @Transactional
    public StudyOneResponse studyView(Long studyId, String orderCriteria) {

        StudyOneResponse studyOneResponse = studyRepository.findOneStudyById(studyId, orderCriteria)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NoSuchElementException));

        Long memberId = null;
        commentsExtractor(studyId, studyOneResponse, memberId);

        return studyOneResponse;
    }

    @Transactional
    public StudyOneResponse studyLoginView(Long studyId, Long memberId, String orderCriteria) {

        StudyOneResponse studyOneResponse = studyRepository.findOneStudyById(studyId, orderCriteria)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NoSuchElementException));

        if(studyLikeRepository.likeView(studyId, memberId)) {
            studyOneResponse.changeUserLike(true);
        }
        commentsExtractor(studyId, studyOneResponse, memberId);

        return studyOneResponse;
    }


    private void commentsExtractor(Long studyId, StudyOneResponse studyOneResponse, @Nullable Long memberId) {

        studyOneResponse.getStudyComment()
                .forEach(comment -> {
                    if(memberId != null){
                        if(studyCommentLikeRepository.likeView(comment.getStudyCommentId(), memberId))
                            comment.changeUserLike(true);
                    }
                    List<StudyCommentChildrenResponse> comments =
                            studyCommentRepository.findStudyComment(studyId, comment.getStudyCommentId());
                    comment.setChildren(comments);
                });
    }

    @Transactional
    public void studyDelete(Long studyId) {

        studyRepository.deleteById(studyId);
    }

    @Transactional
    public Study studyUpdate(Long studyId, Study requestStudy) {

        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> {
                    return new BusinessLogicException(ExceptionCode.NoSuchElementException);
                });

        study.changeTitle(requestStudy.getTitle());
        study.changeContent(requestStudy.getContent());
        study.changeRecruitment(requestStudy.isRecruitment());

        return studyRepository.save(study);
    }


    @Transactional
    public void updateViewCount(Long studyId, HttpServletRequest request, HttpServletResponse response) {

        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("studyView")) {
                    oldCookie = cookie;
                }
            }
        }

        if (oldCookie != null) {
            if (!oldCookie.getValue().contains("[" + studyId.toString() + "]")) {
                studyRepository.updateView(studyId);
                oldCookie.setValue(oldCookie.getValue() + "_[" + studyId + "]");
                oldCookie.setPath("/api/v1/studies");
                oldCookie.setHttpOnly(true);
                oldCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(oldCookie);
            }
        } else {
            studyRepository.updateView(studyId);
            Cookie newCookie = new Cookie("studyView", "[" + studyId + "]");
            newCookie.setPath("/api/v1/studies");
            newCookie.setMaxAge(60 * 60 * 24);
            newCookie.setHttpOnly(true);
            response.addCookie(newCookie);
        }
    }

    @Transactional
    public void studyLike(Long studyId, Long memberId) {

        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> {
                    return new BusinessLogicException(ExceptionCode.NoSuchElementException);
                });
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> {
                    return new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
                });

        Optional<StudyLike> studyAndMember = studyLikeRepository.findByStudyAndMember(study, member);

        studyAndMember.ifPresentOrElse(

                studyLike -> {
                    studyLikeRepository.delete(studyLike);
                    study.discountLike(studyLike);
                    study.updateLikeCount();
                },
                () -> {
                    StudyLike studyLike = StudyLike.builder().build();
                    studyLike.mappingStudy(study);
                    studyLike.mappingMember(member);
                    study.updateLikeCount();
                    studyLikeRepository.save(studyLike);
                }
        );
    }
}
