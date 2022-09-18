package com.team10.preproject.answer.service;


import com.team10.preproject.answer.entity.Answer;
import com.team10.preproject.answer.repository.AnswerRepository;
import com.team10.preproject.member.entity.Member;
import com.team10.preproject.question.entity.Question;
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


    @Transactional
    public Answer anserWrite(Member member, Long questionId, Answer requestAnswer){

        Question question = questionRepository.findById(questionId).orElseThrow(() ->{
            return new IllegalArgumentException("게시글 id를 찾을 수 없습니다.");
        });

        requestAnswer.setMember(member);
        requestAnswer.setQuestion(question);
        return answerRepository.save(requestAnswer);
    }

    @Transactional
    public Answer answerUpdate(Long answerId, Answer requestAnswer) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() ->{
                    return new IllegalArgumentException("답변 찾기 실패 : 답변을 찾을 수 없습니다.");
                });
        answer.setComment(requestAnswer.getComment());
        return answerRepository.save(answer);
    }

    @Transactional
    public void anserDelete(Long answerId){
        answerRepository.deleteById(answerId);
    }

}
