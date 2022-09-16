package com.team10.preproject.member.controller;

import com.team10.preproject.dto.SingleResponseDto;
import com.team10.preproject.member.dto.MemberDto;
import com.team10.preproject.member.dto.PasswordForgotDto;
import com.team10.preproject.member.entity.Member;
import com.team10.preproject.member.mapper.MemberMapper;
import com.team10.preproject.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1/users")
@Validated
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId) {

        Member member = memberService.findMember(memberId);
        MemberDto.Response response = mapper.memberToMemberResponse(member);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity logoutMember(
            HttpServletRequest request, HttpServletResponse response) {
        memberService.removeCookies(request, response);

        return ResponseEntity.ok()
                .body("You've been signed out!");
    }

    @PostMapping("/signup")
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        Member member = mapper.memberPostToMember(requestBody);

        Member createMember = memberService.createMember(member, getSiteURL(request));
        MemberDto.Response response = mapper.memberToMemberResponse(createMember);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response),
                HttpStatus.CREATED);
    }

    private String getSiteURL (HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (memberService.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity findPassword(@RequestBody @Valid PasswordForgotDto requestBody) throws Exception {
        memberService.recoveryPassword(requestBody.getEmail());
        return ResponseEntity.ok().body(("Please check your email"));
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(
            @PathVariable("member-id") @Positive long memberId,
            @Valid @RequestBody MemberDto.Patch requestBody) {
        requestBody.setMemberId(memberId);

        Member member =
                memberService.updateMember(mapper.memberPatchToMember(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.memberToMemberResponse(member))
                , HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
            @PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
