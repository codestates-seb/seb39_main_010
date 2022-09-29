package com.team10.preproject.answer.service;


import com.team10.preproject.answer.dto.AnswerCreateRequestDto;
import com.team10.preproject.answer.dto.AnswerDto;
import com.team10.preproject.answer.entity.Answer;
import com.team10.preproject.answer.entity.DeleteStatus;
import com.team10.preproject.answer.repository.AnswerRepository;
import com.team10.preproject.member.entity.Member;
import com.team10.preproject.member.repository.MemberRepository;
import com.team10.preproject.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public AnswerDto anwserWrite(Member member, Long questionId, AnswerCreateRequestDto requestDto) {

        questionRepository.findById(questionId).orElseThrow(() -> {
            return new IllegalArgumentException("게시글 id를 찾을 수 없습니다.");
        });

        Answer answer = answerRepository.save(
                Answer.createAnswer(requestDto.getComment(),
                        questionRepository.findById(questionId).orElseThrow(IllegalArgumentException::new),
                        memberRepository.findById(member.getMemberId()).orElseThrow(IllegalArgumentException::new),
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

        Answer answer = answerRepository.findAnswerByIdWithParent(answerId).orElseThrow(() -> {
            return new IllegalArgumentException("댓글을 찾을 수 없습니다.");
        });

        if (answer == null &&
                answerRepository.findByIsDeleted(answer.getAnswerId()).size() == 0) {

            answerRepository.delete(answer);

        } else if (answer.getParent().getIsDeleted() == DeleteStatus.Y &&
                answerRepository.findByIsDeletedNotIn(answer.getParent().getAnswerId(), answerId)
                        .size() == 0) {

            answerRepository.delete(answer.getParent());

        } else {
            answer.changeDeletedStatus(DeleteStatus.Y);
        }
    }
}
