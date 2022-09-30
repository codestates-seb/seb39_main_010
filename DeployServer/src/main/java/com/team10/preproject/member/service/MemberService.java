package com.team10.preproject.member.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.team10.preproject.global.exception.BusinessLogicException;
import com.team10.preproject.global.exception.ExceptionCode;
import com.team10.preproject.global.helper.event.MemberRegistrationApplicationEvent;
import com.team10.preproject.member.entity.Member;
import com.team10.preproject.member.entity.Role;
import com.team10.preproject.member.repository.MemberRepository;
import com.team10.preproject.token.service.TokenService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.Optional;

@Transactional
@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Autowired
//    private JavaMailSender mailSender;

    private final ApplicationEventPublisher publisher;

    public MemberService(MemberRepository memberRepository,
                         ApplicationEventPublisher publisher) {

        this.memberRepository = memberRepository;
        this.publisher = publisher;
    }

    public Member createMember(Member member, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {

        verifyExistsEmail(member.getEmail());
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        member.setRole(Role.USER);
        String randomCode = RandomString.make(64);
        member.setVerificationCode(randomCode);
        member.setEnabled(false);
//        sendSignupVerificationEmail(member, getSiteURL(request));
        Member savedMember = memberRepository.save(member);
        publisher.publishEvent(new MemberRegistrationApplicationEvent(this, savedMember));

        return savedMember;
    }

    private void verifyExistsEmail(String email) {

        Member member = memberRepository.findByEmail(email);
        if (member != null)
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }

    private String getSiteURL(HttpServletRequest request) {

        String siteURL = request.getRequestURL().toString();

        return siteURL.replace(request.getServletPath(), "");
    }

//    private void sendSignupVerificationEmail(Member member, String siteURL)
//            throws MessagingException, UnsupportedEncodingException {
//
//        String toAddress = member.getEmail();
//        String fromAddress = "CS@Weply.com";
//        String senderName = "Weply";
//        String subject = "Please verify your registration";
//        String content = "Dear [[name]],<br>"
//                + "Please click the link below to verify your registration:<br>"
//                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
//                + "Thank you,<br>"
//                + "Weply.";
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//        helper.setFrom(fromAddress, senderName);
//        helper.setTo(toAddress);
//        helper.setSubject(subject);
//        content = content.replace("[[name]]", member.getNickname());
//        String verifyURL = siteURL + "/api/v1/users/signup-verification?code=" + member.getVerificationCode();
//        content = content.replace("[[URL]]", verifyURL);
//        helper.setText(content, true);
//        mailSender.send(message);
//    }

    public boolean signupVerify(String verificationCode) {

        Member member = memberRepository.findByVerificationCode(verificationCode);
        if (member == null || member.isEnabled()) {

            return false;
        } else {
            member.setVerificationCode(null);
            member.setEnabled(true);
            memberRepository.save(member);

            return true;
        }
    }

    public void recoveryPassword(String email) throws Exception {

        Member member = findExistsEmail(email);
        String randomPasswordCode = RandomString.make(15);
        member.setPassword(bCryptPasswordEncoder.encode(randomPasswordCode));
        // 비밀번호 변경
        memberRepository.save(member);
        // 비밀번호 변경 메일 발송
//        sendTempPasswordEmail(member, randomPasswordCode);
    }

    private Member findExistsEmail(String email) {

        Member member = memberRepository.findByEmail(email);
        if (member == null)
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);

        return member;
    }

//    private void sendTempPasswordEmail(Member member, String randomPasswordCode)
//            throws MessagingException, UnsupportedEncodingException {
//
//        String toAddress = member.getEmail();
//        String fromAddress = "CS@Weply.com";
//        String senderName = "Weply";
//        String subject = "Password Recovery";
//        String content = "Dear [[name]],<br>"
//                + "Please change your password after the first login<br>"
//                + "Password : [[TempPw]]<br>"
//                + "Thank you,<br>"
//                + "Weply.";
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//        helper.setFrom(fromAddress, senderName);
//        helper.setTo(toAddress);
//        helper.setSubject(subject);
//        content = content.replace("[[name]]", member.getNickname());
//        content = content.replace("[[TempPw]]", randomPasswordCode);
//        helper.setText(content, true);
//        mailSender.send(message);
//    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Member updateMember(Member member) {

        Member findMember = findVerifiedMember(member.getMemberId());
        Optional.ofNullable(member.getEmail())
                .ifPresent(findMember::setEmail);
        Optional.ofNullable(member.getNickname())
                .ifPresent(findMember::setNickname);
        Optional.ofNullable(member.getPassword())
                .ifPresent(findMember::setPassword);
        Optional.ofNullable(member.getPicture())
                .ifPresent(findMember::setPicture);
        Optional.ofNullable(member.getFavoriteCompany())
                .ifPresent(findMember::setFavoriteCompany);
        Optional.ofNullable(member.getSelfIntroductions())
                .ifPresent(findMember::setSelfIntroductions);
//        Optional.ofNullable(member.getMemberStatus())
//                .ifPresent(memberStatus -> findMember.setMemberStatus(memberStatus));

        return memberRepository.save(findMember);
    }

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
        return optionalMember.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Member findMember(long memberId) {
        return findVerifiedMember(memberId);
    }

    public void checkOwnerShip(HttpServletRequest request, Long memberId) {

        String jwtToken = request.getHeader("Refresh");
        String email = JWT.require(Algorithm.HMAC512("cos_jwt_token")).build().verify(jwtToken).getClaim("email").asString();
        Member member = memberRepository.findByEmail(email);
        if(!Objects.equals(member.getMemberId(), memberId)) {

            throw new BusinessLogicException(ExceptionCode.INVALID_MEMBER_AUTHENTICATION);
        }
    }

    public void removeCookies(HttpServletRequest request, HttpServletResponse response) {

        Cookie rememberMeCookie = new Cookie("remember-me", "");
        rememberMeCookie.setMaxAge(0);
        response.addCookie(rememberMeCookie);
    }
}
