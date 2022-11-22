package com.team10.preproject.studycomment.mapper;

import com.team10.preproject.studycomment.dto.StudyCommentPutDto;
import com.team10.preproject.studycomment.dto.StudyCommentResponseDto;
import com.team10.preproject.studycomment.entity.StudyComment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudyCommentMapper {

    StudyCommentResponseDto commentResponseToDto(StudyComment studyComment);
//    StudyComment commentPostToComment(StudyComment studyComment);
    StudyComment commentPutToComment(StudyCommentPutDto studyCommentPutDto);
}
