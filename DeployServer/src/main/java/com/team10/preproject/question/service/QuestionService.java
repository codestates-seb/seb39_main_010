package com.team10.preproject.question.service;

import com.team10.preproject.answer.repository.AnswerRepository;
import com.team10.preproject.global.exception.BusinessLogicException;
import com.team10.preproject.global.exception.ExceptionCode;
import com.team10.preproject.member.entity.Member;
import com.team10.preproject.member.repository.MemberRepository;
import com.team10.preproject.question.dto.CommentsChildrenResponse;
import com.team10.preproject.question.dto.QuestionOneResponse;
import com.team10.preproject.question.dto.QuestionResponseDto;
import com.team10.preproject.question.entity.Question;

import com.team10.preproject.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private MemberRepository memberRepository;


    // 글 작성
    @Transactional
    public Question questionwrite(Question question, Member member) {

        question.setMember(member);

        return questionRepository.save(question);
    }

    // 글 리스트
    @Transactional(readOnly = true)
    public Page<Question> questionList(Pageable pageable) {

        return questionRepository.findAll(pageable);
    }

    // 글 상세보기
    @Transactional
    public QuestionOneResponse questionView(Long questionId) {

        QuestionOneResponse questionOneResponse = questionRepository.findOneQuestionById(questionId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NoSuchElementException));
        commentsExtractor(questionId, questionOneResponse);

        return questionOneResponse;
    }

    private void commentsExtractor(Long questionId, QuestionOneResponse questionOneResponse) {

        questionOneResponse.getAnswers()
                .forEach(comment -> {
                    List<CommentsChildrenResponse> comments =
                            answerRepository.findQuestionAnswers(questionId, comment.getAnswerId());
                    comment.setChildren(comments);
                });
    }

    // 글 수정
    @Transactional
    public Question questionUpdate(long questionId,Question requestQuestion) {

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() ->{
                    return new IllegalArgumentException("글 찾기 실패 : 해당 글을 찾을 수 없습니다.");
                });

        question.setTitle(requestQuestion.getTitle());
        question.setContent(requestQuestion.getContent());

        return questionRepository.save(question);
    }

    // 글 삭제하기
    @Transactional
    public void questionDelete(Long questionId) {

        questionRepository.deleteById(questionId);
    }

    // 조회수 증가
    @Transactional
    public void updateViewCount(Long questionId, HttpServletRequest request, HttpServletResponse response) {

        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("questionView")) {
                    oldCookie = cookie;
                }
            }
        }

        if (oldCookie != null) {
            if (!oldCookie.getValue().contains("[" + questionId.toString() + "]")) {
                questionRepository.updateView(questionId);
                oldCookie.setValue(oldCookie.getValue() + "_[" + questionId + "]");
                oldCookie.setPath("/api/v1/questions");
                oldCookie.setHttpOnly(true);
                oldCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(oldCookie);
            }
        } else {
            questionRepository.updateView(questionId);
            Cookie newCookie = new Cookie("questionView", "[" + questionId + "]");
            newCookie.setPath("/api/v1/questions");
            newCookie.setMaxAge(60 * 60 * 24);
            newCookie.setHttpOnly(true);
            response.addCookie(newCookie);
        }
    }


    public Page<Question> questionSearchTitle(String title, Pageable pageable) {

        return questionRepository.findByTitleContaining(title, pageable);
    }

    public Page<Question> questionSearchTitleContent(String title, String content, Pageable pageable) {

        return questionRepository.findByTitleContainingOrContentContaining(title , content, pageable);
    }

    public Page<Question> questionSearchContent(String content, Pageable pageable) {

        return questionRepository.findByContentContaining(content, pageable);
    }

    public List<QuestionResponseDto> questionSearchWriter(String writer, Pageable pageable) {

        return  questionRepository.findWriterQuestion(writer, pageable);
    }

}
