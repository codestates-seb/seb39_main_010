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

    // ??? ??????
    @Transactional
    public Question questionwrite(Question question, Long memberId, Long categoryId, Long tagId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("??? ?????? ?????? : ?????? ????????? ?????????.");
                });
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->{
                    return new IllegalArgumentException("??? ?????? ?????? : ?????? ???????????? ?????????.");
                });
        Subcategory subcategory = subcategoryRepository.findById(tagId)
                .orElseThrow(() ->{
                    return new IllegalArgumentException("??? ?????? ?????? : ?????? ?????? ?????????.");
                });

        question.changeMember(member);
        question.changeCategory(category);
        question.changeSubcategory(subcategory);

        return questionRepository.save(question);
    }

    // ??? ?????????
    @Transactional(readOnly = true)
    public Page<Question> questionList(Pageable pageable) {

        return questionRepository.findAll(pageable);
    }

    //??? ??????
    @Transactional
    public Page<QuestionResponseDto> searchPageList(Pageable pageable,String searchType,
                                                    String keyword, String category,
                                                    String orderCriteria) {

        pageable = PageRequest.of(0, 8, Sort.Direction.DESC, orderCriteria);
        return new PageImpl<>(questionRepository.findCategoryQuestion(category, searchType, keyword, pageable));
    }


    // ??? ????????????
    @Transactional
    public QuestionOneResponse questionView(Long questionId) {

        QuestionOneResponse questionOneResponse = questionRepository.findOneQuestionById(questionId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NoSuchElementException));

        Long memberId = null;
        commentsExtractor(questionId, questionOneResponse, memberId);

        return questionOneResponse;
    }

    @Transactional
    public QuestionOneResponse questionloginView(Long questionId, Long memberId) {

        QuestionOneResponse questionOneResponse = questionRepository.findOneQuestionById(questionId)
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

    // ??? ??????
    @Transactional
    public Question questionUpdate(long questionId,Question requestQuestion) {

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() ->{
                    return new IllegalArgumentException("??? ?????? ?????? : ?????? ?????? ?????? ??? ????????????.");
                });
        Category category = categoryRepository.findById(requestQuestion.getCategory().getCategoryId())
                .orElseThrow(() ->{
                    return new IllegalArgumentException("??? ?????? ?????? : ?????? ???????????? ?????????.");
                });

        Subcategory subcategory = subcategoryRepository.findById(requestQuestion.getTag().getSubcategoryId())
                .orElseThrow(() ->{
                    return new IllegalArgumentException("??? ?????? ?????? : ?????? ?????? ?????????.");
                });

        question.changeTitle(requestQuestion.getTitle());
        question.changeContent(requestQuestion.getContent());
        question.changeCategory(category);
        question.changeSubcategory(subcategory);

        return questionRepository.save(question);
    }

    // ??? ????????????
    @Transactional
    public void questionDelete(Long questionId) {

        questionRepository.deleteById(questionId);
    }

    // ????????? ??????
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
                    return new IllegalArgumentException("??? ?????? ?????? : ?????? ?????? ?????? ??? ????????????.");
                });
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->{
                    return new IllegalArgumentException("?????? ????????? ?????? ???????????????.");
                });

        Optional<QuestionLike> byQuestionAndMember = questionLikeRepository.findByQuestionAndMember(question, member);

        byQuestionAndMember.ifPresentOrElse(
                // ????????? ???????????? ??????
                questionLike -> {
                    questionLikeRepository.delete(questionLike);
                    question.discountLike(questionLike);
                    question.updateLikeCount();
                },
                // ???????????? ?????? ?????? ????????? ??????
                () -> {
                    QuestionLike questionLike = QuestionLike.builder().build();
                    questionLike.mappingQuestion(question);
                    questionLike.mappingMember(member);
                    question.updateLikeCount();
                    questionLikeRepository.save(questionLike);
                }
        );

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
