package com.team10.preproject.answer.service;


import com.team10.preproject.answer.dto.AnswerCreateRequestDto;
import com.team10.preproject.answer.dto.AnswerDto;
import com.team10.preproject.answer.entity.Answer;
import com.team10.preproject.answer.entity.AnswerLike;
import com.team10.preproject.answer.entity.DeleteStatus;
import com.team10.preproject.answer.repository.AnswerLikeRepository;
import com.team10.preproject.answer.repository.AnswerRepository;
import com.team10.preproject.member.entity.Member;
import com.team10.preproject.member.repository.MemberRepository;
import com.team10.preproject.question.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AnswerService {

    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;
    private MemberRepository memberRepository;
    private AnswerLikeRepository answerLikeRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository,
                         MemberRepository memberRepository, AnswerLikeRepository answerLikeRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.memberRepository = memberRepository;
        this.answerLikeRepository = answerLikeRepository;
    }

    @Transactional
    public AnswerDto anwserWrite(Long memberId, Long questionId, AnswerCreateRequestDto requestDto) {

        questionRepository.findById(questionId).orElseThrow(() -> {
            return new IllegalArgumentException("게시글 id를 찾을 수 없습니다.");
        });

        Answer answer = answerRepository.save(
                Answer.createAnswer(requestDto.getComment(),
                        questionRepository.findById(questionId).orElseThrow(IllegalArgumentException::new),
                        memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new),
                        requestDto.getParentId() != null ?
                                answerRepository.findById(requestDto.getParentId()).orElseThrow(IllegalArgumentException::new) : null)
        );

        return AnswerDto.convertAnswerToDto(answer);
    }

    @Transactional
    public Answer answerUpdate(Long answerId, Answer requestAnswer) {

        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("댓글 찾기 실패 : 댓글을 찾을 수 없습니다.");
                });

        answer.setComment(requestAnswer.getComment());

        return answerRepository.save(answer);
    }

    @Transactional
    public void answerDelete(Long answerId) {

        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> {
            return new IllegalArgumentException("댓글을 찾을 수 없습니다.");
        });

        if (answer.getParent() == null &&
                answerRepository.findByIsDeleted(answer.getAnswerId()).size() == 0) {

            answerRepository.delete(answer);

        } else if (answer.getIsDeleted() == DeleteStatus.Y &&
                answerRepository.findByIsDeletedNotIn(answer.getParent().getAnswerId(), answerId)
                        .size() == 0) {

            answerRepository.delete(answer.getParent());

        } else if (answer.getParent() != null && answerRepository.findByLast(answer.getParent().getAnswerId())){

            answerRepository.delete(answer.getParent());

        } else {
            answer.changeDeletedStatus(DeleteStatus.Y);
        }
    }

    @Transactional
    public void answerLike(Long answerId, Long memberId) {

        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("좋아요 댓글 찾기 실패 : 해당 글을 찾을 수 없습니다.");
                });
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->{
                    return new IllegalArgumentException("해당 유저는 없는 유저입니다.");
                });

        Optional<AnswerLike> byAnswerAndMember = answerLikeRepository.findByAnswerAndMember(answer, member);

        byAnswerAndMember.ifPresentOrElse(

                answerLike -> {
                    answerLikeRepository.delete(answerLike);
                    answer.discountLike(answerLike);
                    answer.updateLikeCount();
                },
                () -> {
                    AnswerLike answerLike = AnswerLike.builder().build();
                    answerLike.mappingAnswer(answer);
                    answerLike.mappingMember(member);
                    answer.updateLikeCount();
                    answerLikeRepository.save(answerLike);
                }
        );

    }
}
