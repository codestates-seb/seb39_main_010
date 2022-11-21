package com.team10.preproject.study.controller;

import com.team10.preproject.global.auth.PrincipalDetails;
import com.team10.preproject.global.response.dto.SingleResponseDto;
import com.team10.preproject.study.dto.StudyDto;
import com.team10.preproject.study.dto.StudyOneResponse;
import com.team10.preproject.study.dto.StudyResponseDto;
import com.team10.preproject.study.entity.Study;
import com.team10.preproject.study.mapper.StudyMapper;
import com.team10.preproject.study.service.StudyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/studies")
public class StudyController {

    private StudyService studyService;
    private StudyMapper mapper;

    public StudyController(StudyService studyService, StudyMapper mapper) {
        this.studyService = studyService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity studyWrite(@Valid @RequestBody StudyDto.Post requestBody,
                                     @AuthenticationPrincipal PrincipalDetails principal) {

        requestBody.setMember(principal.getMember());
        Study study = mapper.studyPostToStudy(requestBody);
        StudyResponseDto studyResponseDto
                = mapper.studyToResponseDto(studyService.studyWrite(study, principal.getMemberId()));

        return new ResponseEntity<>(
                new SingleResponseDto<>(studyResponseDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity studyList(@PageableDefault(size = 8, sort = "studyId", direction = Sort.Direction.DESC)
                                    Pageable pageable) {

        Page<Study> studyList = studyService.studyList(pageable);
        Page<StudyResponseDto> pageDto = studyList.map(entity -> {
            StudyResponseDto dto = mapper.studyToResponseDto(entity);
            return dto;
        });
        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @GetMapping("/{study-id}")
    public StudyOneResponse studyView(@PathVariable("study-id") Long studyId,
                                      HttpServletRequest request, HttpServletResponse response,
                                      @Nullable @AuthenticationPrincipal PrincipalDetails principal) {

        return null;
    }
}
