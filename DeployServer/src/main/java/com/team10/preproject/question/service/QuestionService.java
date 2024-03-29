package com.team10.preproject.question.service;

import com.team10.preproject.answer.repository.AnswerLikeRepository;
import com.team10.preproject.answer.repository.AnswerRepository;
import com.team10.preproject.global.qnaCategory.entity.Category;
import com.team10.preproject.global.qnaCategory.entity.Subcategory;
import com.team10.preproject.global.qnaCategory.repository.CategoryRepository;
import com.team10.preproject.global.qnaCategory.repository.SubcategoryRepository;
import com.team10.preproject.global.exception.BusinessLogicException;
import com.team10.preproject.global.exception.ExceptionCode;
import com.team10.preproject.member.entity.Member;
import com.team10.preproject.member.repository.MemberRepository;
import com.team10.preproject.question.dto.CommentsChildrenResponse;
import com.team10.preproject.question.dto.QuestionOneResponse;
import com.team10.preproject.question.dto.QuestionResponseDto;
import com.team10.preproject.question.entity.Question;

import com.team10.preproject.question.entity.QuestionLike;
import com.team10.preproject.question.repository.QuestionLikeRepository;
import com.team10.preproject.question.repository.QuestionRepository;
import org.springframework.data.domain.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private MemberRepository memberRepository;
    private QuestionLikeRepository questionLikeRepository;
    private CategoryRepository categoryRepository;
    private SubcategoryRepository subcategoryRepository;
    private AnswerLikeRepository answerLikeRepository;

    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository,
                           MemberRepository memberRepository, QuestionLikeRepository questionLikeRepository,
                           CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository,
                           AnswerLikeRepository answerLikeRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.memberRepository = memberRepository;
        this.questionLikeRepository = questionLikeRepository;
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.answerLikeRepository = answerLikeRepository;
    }

    // 글 작성
    @Transactional
    public Question questionWrite(Question question, Long memberId, Long categoryId, Long tagId) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> {
            return new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        });
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->{
                    return new IllegalArgumentException("글 작성 실패 : 없는 카테고리 입니다.");
                });
        Subcategory subcategory = subcategoryRepository.findById(tagId)
                .orElseThrow(() ->{
                    return new IllegalArgumentException("글 작성 실패 : 없는 태그 입니다.");
                });

        question.changeMember(member);
        question.changeCategory(category);
        question.changeSubcategory(subcategory);

        return questionRepository.save(question);
    }

    // 글 리스트
    @Transactional(readOnly = true)
    public Page<Question> questionList(Pageable pageable) {

        return questionRepository.findAll(pageable);
    }

    //글 검색
    @Transactional
    public Page<QuestionResponseDto> searchPageList(Pageable pageable,String searchType,
                                                    String keyword, String category,
                                                    String orderCriteria) {

        pageable = PageRequest.of(0, 8, Sort.Direction.DESC, orderCriteria);
        return new PageImpl<>(questionRepository.findCategoryQuestion(category, searchType, keyword, pageable));
    }


    // 글 상세보기
    @Transactional
    public QuestionOneResponse questionView(Long questionId, String orderCriteria) {

        QuestionOneResponse questionOneResponse = questionRepository.findOneQuestionById(questionId, orderCriteria)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NoSuchElementException));

        Long memberId = null;
        commentsExtractor(questionId, questionOneResponse, memberId);

        return questionOneResponse;
    }

    @Transactional
    public QuestionOneResponse questionloginView(Long questionId, Long memberId, String orderCriteria) {

        QuestionOneResponse questionOneResponse = questionRepository.findOneQuestionById(questionId, orderCriteria)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NoSuchElementException));

        if(questionLikeRepository.likeView(questionId,memberId)){
            questionOneResponse.changeUserLike(true);
        }
        commentsExtractor(questionId, questionOneResponse, memberId);

        return questionOneResponse;
    }

    private void commentsExtractor(Long questionId, QuestionOneResponse questionOneResponse, @Nullable Long memberId) {

        questionOneResponse.getAnswers()
                .forEach(comment -> {
                    if(memberId != null){
                        if(answerLikeRepository.likeView(comment.getAnswerId(), memberId))
                            comment.changeUserLike(true);
                    }
                    List<CommentsChildrenResponse> comments =
                            answerRepository.findQuestionAnswers(questionId, comment.getAnswerId());
                    comment.setChildren(comments);
                });
    }

    // 글 수정
    @Transactional
    public Question questionUpdate(Long questionId,Question requestQuestion) {

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() ->{
                    return new IllegalArgumentException("글 찾기 실패 : 해당 글을 찾을 수 없습니다.");
                });
        Category category = categoryRepository.findById(requestQuestion.getCategory().getCategoryId())
                .orElseThrow(() ->{
                    return new IllegalArgumentException("글 작성 실패 : 없는 카테고리 입니다.");
                });

        Subcategory subcategory = subcategoryRepository.findById(requestQuestion.getTag().getSubcategoryId())
                .orElseThrow(() ->{
                    return new IllegalArgumentException("글 작성 실패 : 없는 태그 입니다.");
                });

        question.changeTitle(requestQuestion.getTitle());
        question.changeContent(requestQuestion.getContent());
        question.changeCategory(category);
        question.changeSubcategory(subcategory);

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

    @Transactional
    public void questionLike(Long questionId, Long memberId) {

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() ->{
                    return new IllegalArgumentException("글 찾기 실패 : 해당 글을 찾을 수 없습니다.");
                });
        Member member = memberRepository.findById(memberId).orElseThrow(() -> {
            return new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        });

        Optional<QuestionLike> byQuestionAndMember = questionLikeRepository.findByQuestionAndMember(question, member);

        byQuestionAndMember.ifPresentOrElse(
                // 좋아요 있을경우 삭제
                questionLike -> {
                    questionLikeRepository.delete(questionLike);
                    question.discountLike(questionLike);
                    question.updateLikeCount();
                },
                // 좋아요가 없을 경우 좋아요 추가
                () -> {
                    QuestionLike questionLike = QuestionLike.builder().build();
                    questionLike.mappingQuestion(question);
                    questionLike.mappingMember(member);
                    question.updateLikeCount();
                    questionLikeRepository.save(questionLike);
                }
        );
    }

    public Page<Question> memberQuestionList(Long memberId, Pageable pageable) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> {
            return new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        });

        return questionRepository.findByQuestionAndMember(member.getMemberId(), pageable);
    }
}
