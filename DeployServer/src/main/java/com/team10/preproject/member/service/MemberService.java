package com.team10.preproject.member.service;

import com.team10.preproject.global.exception.BusinessLogicException;
import com.team10.preproject.global.exception.ExceptionCode;
import com.team10.preproject.global.helper.event.MemberRegistrationApplicationEvent;
import com.team10.preproject.member.entity.Member;
import com.team10.preproject.member.repository.MemberRepository;
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
import java.util.Optional;

@Transactional
@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    private final ApplicationEventPublisher publisher;

    public void recoveryPassword(String email) throws Exception {

        Member member = findExistsEmail(email);
        String randomPasswordCode = RandomString.make(15);
        member.setPassword(bCryptPasswordEncoder.encode(randomPasswordCode));
        // 비밀번호 변경
        memberRepository.save(member);
        // 비밀번호 변경 메일 발송
        sendTempPasswordEmail(member, randomPasswordCode);
    }

    private Member findExistsEmail(String email) {

        Member member = memberRepository.findByEmail((email));
        if (member == null)
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);

        return member;
    }

    private void sendTempPasswordEmail(Member member, String randomPasswordCode)
            throws MessagingException, UnsupportedEncodingException {

        String toAddress = member.getEmail();
        String fromAddress = "x2d7751347m@gmail.com";
        String senderName = "Motiv";
        String subject = "Password Recovery";
        String content = "Dear [[name]],<br>"
                + "Please change your password after the first login<br>"
                + "Password : [[TempPw]]<br>"
                + "Thank you,<br>"
                + "Motiv.";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        content = content.replace("[[name]]", member.getNickname());
        content = content.replace("[[TempPw]]", randomPasswordCode);
        helper.setText(content, true);
        mailSender.send(message);
    }

    public boolean verify(String verificationCode) {

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

    public Member createMember(Member member, String siteURL) throws UnsupportedEncodingException, MessagingException {

        verifyExistsEmail(member.getEmail());
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        member.setRole("ROLE_USER");
        String randomCode = RandomString.make(64);
        member.setVerificationCode(randomCode);
        member.setEnabled(false);
        sendVerificationEmail(member, siteURL);
        Member savedMember = memberRepository.save(member);
        publisher.publishEvent(new MemberRegistrationApplicationEvent(this, savedMember));

        return savedMember;
    }

    private void sendVerificationEmail(Member member, String siteURL)
            throws MessagingException, UnsupportedEncodingException {

        String toAddress = member.getEmail();
        String fromAddress = "x2d7751347m@gmail.com";
        String senderName = "Weply";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Weply.";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        content = content.replace("[[name]]", member.getNickname());
        String verifyURL = siteURL + "/verification?code=" + member.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);
        helper.setText(content, true);
        mailSender.send(message);
    }

    private String getSiteURL(HttpServletRequest request) {

        String siteURL = request.getRequestURL().toString();

        return siteURL.replace(request.getServletPath(), "");
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Member updateMember(Member member) {

        Member findMember = findVerifiedMember(member.getMemberId());
        Optional.ofNullable(member.getNickname())
                .ifPresent(findMember::setNickname);
        Optional.ofNullable(member.getPassword())
                .ifPresent(findMember::setPassword);
        Optional.ofNullable(member.getEmail())
                .ifPresent(findMember::setEmail);
//        Optional.ofNullable(member.getMemberStatus())
//                .ifPresent(memberStatus -> findMember.setMemberStatus(memberStatus));

        return memberRepository.save(findMember);
    }

    @Transactional(readOnly = true)
    public Member findMember(long memberId) {

        return findVerifiedMember(memberId);
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
