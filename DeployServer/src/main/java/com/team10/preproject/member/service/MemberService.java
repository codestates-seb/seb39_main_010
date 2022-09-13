package com.team10.preproject.member.service;

import com.team10.preproject.exception.BusinessLogicException;
import com.team10.preproject.exception.ExceptionCode;
import com.team10.preproject.helper.event.MemberRegistrationApplicationEvent;
import com.team10.preproject.member.entity.Member;
import com.team10.preproject.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Transactional
@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ApplicationEventPublisher publisher;

    public MemberService(MemberRepository memberRepository,
                         ApplicationEventPublisher publisher) {
        this.memberRepository = memberRepository;
        this.publisher = publisher;
    }

    public void removeCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie rememberMeCookie = new Cookie("remember-me", "");
        rememberMeCookie.setMaxAge(0);
        response.addCookie(rememberMeCookie);
    }


    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        member.setRoles("ROLE_USER");
        member.setUsername(member.getEmail());
        Member savedMember = memberRepository.save(member);

        publisher.publishEvent(new MemberRegistrationApplicationEvent(this, savedMember));
        return savedMember;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Member updateMember(Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getNickname())
                .ifPresent(findMember::setNickname);
        Optional.ofNullable(member.getPassword())
                .ifPresent(findMember::setPassword);
//        Optional.ofNullable(member.getMemberStatus())
//                .ifPresent(memberStatus -> findMember.setMemberStatus(memberStatus));

        return memberRepository.save(findMember);
    }

    @Transactional(readOnly = true)
    public Member findMember(long memberId) { return findVerifiedMember(memberId); }

    public Page<Member> findMembers(int page, int size) {
        return memberRepository.findAll(PageRequest.of(page, size,
                Sort.by("memberId").descending()));
    }

    public void deleteMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);

        memberRepository.delete(findMember);
    }

    @Transactional(readOnly = true)
    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    private void verifyExistsEmail(String email) {
        Member member = memberRepository.findByEmail((email));
        if (member != null)
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
}
