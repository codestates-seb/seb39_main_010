package com.team10.preproject.member.controller;

import com.team10.preproject.dto.SingleResponseDto;
import com.team10.preproject.member.dto.MemberDto;
import com.team10.preproject.member.entity.Member;
import com.team10.preproject.member.mapper.MemberMapper;
import com.team10.preproject.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

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

    @GetMapping("/")
    public String user() {
        return "user";
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
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {
        Member member = mapper.memberPostToMember(requestBody);

        Member createMember = memberService.createMember(member);
        MemberDto.Response response = mapper.memberToMemberResponse(createMember);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response),
                HttpStatus.CREATED);
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
