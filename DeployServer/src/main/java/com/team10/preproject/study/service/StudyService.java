package com.team10.preproject.study.service;

import com.team10.preproject.global.exception.BusinessLogicException;
import com.team10.preproject.global.exception.ExceptionCode;
import com.team10.preproject.member.entity.Member;
import com.team10.preproject.member.repository.MemberRepository;
import com.team10.preproject.study.dto.StudyOneResponse;
import com.team10.preproject.study.entity.Study;
import com.team10.preproject.study.repository.StudyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudyService {

    private MemberRepository memberRepository;
    private StudyRepository studyRepository;

    public StudyService(MemberRepository memberRepository, StudyRepository studyRepository) {
        this.memberRepository = memberRepository;
        this.studyRepository = studyRepository;
    }

    @Transactional
    public Study studyWrite(Study study, Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> {
            return new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        });

        return studyRepository.save(study);
    }

    @Transactional(readOnly = true)
    public Page<Study> studyList(Pageable pageable) {

        return studyRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public StudyOneResponse studyView(Long studyId) {

        StudyOneResponse studyOneResponse = studyRepository.findOneStudyById(studyId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NoSuchElementException));

        Long memberId = null;
        commentsExtractor(studyId, studyOneResponse, memberId);

        return studyOneResponse;
    }


    private void commentsExtractor(Long studyId, StudyOneResponse studyOneResponse, @Nullable Long memberId) {

        studyOneResponse.getStudyComment()
                .forEach(comment -> {
                    if(memberId != null){
                        if()
                    }
                });
    }


}
