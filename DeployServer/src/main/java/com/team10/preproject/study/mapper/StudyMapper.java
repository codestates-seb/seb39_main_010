package com.team10.preproject.study.mapper;

import com.team10.preproject.member.entity.Member;
import com.team10.preproject.study.dto.StudyDto;
import com.team10.preproject.study.dto.StudyResponseDto;
import com.team10.preproject.study.entity.Study;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudyMapper {

    public Study studyPostToStudy(StudyDto.Post post) {
        Member member = Member.builder()
                .memberId(post.getMember().getMemberId())
                .nickname(post.getMember().getNickname())
                .build();
        return Study.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .member(member)
                .build();
    }

    public Study studyPutToStudy(StudyDto.Put put) {
        Member member = Member.builder()
                .memberId(put.getMember().getMemberId())
                .nickname(put.getMember().getNickname())
                .build();

        return Study.builder()
                .title(put.getTitle())
                .content(put.getContent())
                .member(member)
                .build();
    }

    public StudyResponseDto studyToResponseDto(Study study) {
        return StudyResponseDto.builder()
                .studyId(study.getStudyId())
                .title(study.getTitle())
                .content(study.getContent())
                .nickname(study.getMember().getNickname())
                .viewCount(study.getViewCount())
                .likeCount(study.getLikeCount())
                .recruitment(study.isRecruitment())
                .userLike(study.isUserLike())
                .createdAt(study.getCreatedAt())
                .updatedAt(study.getUpdatedAt())
                .build();
    }
}
