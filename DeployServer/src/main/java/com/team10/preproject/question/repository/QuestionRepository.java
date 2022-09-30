package com.team10.preproject.question.repository;

import com.team10.preproject.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionRepository extends JpaRepository<Question,Long>, CustomQuestionRepository {

    Page<Question> findByTitleContaining(String title, Pageable pageable);
    Page<Question> findByContentContaining(String content, Pageable pageable);
    Page<Question> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
