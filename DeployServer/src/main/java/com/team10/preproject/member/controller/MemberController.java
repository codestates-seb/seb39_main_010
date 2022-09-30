package com.team10.preproject.member.controller;

import com.team10.preproject.global.dto.SingleResponseDto;
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

    @PostMapping("/signup")
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {

        Member member = mapper.memberPostToMember(requestBody);
        memberService.verifyExistsEmail(member.getEmail());
        memberService.verifyExistsUsername(member.getUsername());
        memberService.verifyExistsNickname(member.getNickname());
        Member createMember = memberService.createMember(member, request);
        MemberDto.Response response = mapper.memberToMemberResponse(createMember);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response),
                HttpStatus.CREATED);
    }

    @GetMapping("/signup-verification")
    public String verifyUser(@Param("code") String code) {

        if (memberService.signupVerify(code)) {

            return "verify_success";
        } else {

            return "verify_fail";
        }
    }

    @GetMapping("/exists-username")
    public void existsUsername(
            @Valid @RequestBody MemberDto.Patch requestBody) {
        Member member = mapper.memberPatchToMember(requestBody);
        memberService.verifyExistsUsername(member.getUsername());
    }

    @GetMapping("/exists-email")
    public void existsEmail(
            @Valid @RequestBody MemberDto.Patch requestBody) {
        Member member = mapper.memberPatchToMember(requestBody);
        memberService.verifyExistsUsername(member.getEmail());
    }

    @GetMapping("/exists-nickname")
    public void existsNickname(
            @Valid @RequestBody MemberDto.Patch requestBody) {
        Member member = mapper.memberPatchToMember(requestBody);
        memberService.verifyExistsUsername(member.getNickname());
    }

    @GetMapping("/logout")
    public ResponseEntity logoutMember(
            HttpServletRequest request, HttpServletResponse response) {

        memberService.removeCookies(request, response);

        return ResponseEntity.ok()
                .body("You've been signed out!");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity findPassword(@RequestBody @Valid PasswordForgotDto requestBody) throws Exception {

        memberService.recoveryPassword(requestBody.getEmail());
        return ResponseEntity.ok().body("Please check your email");
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(HttpServletRequest request,
                                      @PathVariable("member-id")@Positive long memberId,
                                      @Valid @RequestBody MemberDto.Patch requestBody) {

        memberService.checkOwnerShip(request, memberId);
        requestBody.setMemberId(memberId);
        memberService.verifyExistsEmail(requestBody.getEmail());
        memberService.verifyExistsUsername(requestBody.getUsername());
        memberService.verifyExistsNickname(requestBody.getNickname());
        Member member =
                memberService.updateMember(mapper.memberPatchToMember(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.memberToMemberResponse(member))
                , HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(HttpServletRequest request,
            @PathVariable("member-id") @Positive long memberId) {

        memberService.checkOwnerShip(request, memberId);
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
