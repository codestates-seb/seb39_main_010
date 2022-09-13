package com.team10.preproject.question.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.team10.preproject.answer.entity.Answer;
import com.team10.preproject.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob  // 대용량 데이터
    private String content;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER) // mappedBy - FK가 아니고 컬럼 생성 X
    @JsonIgnoreProperties("question")
    @OrderBy("id desc")
    private List<Answer> answer;
}
