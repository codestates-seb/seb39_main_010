package com.team10.preproject.global.controller;


import com.team10.preproject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestApiController {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/api/v1/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/info")
    public String info() {
        return "<h1>info</h1>";
    }


    @GetMapping("/manager")
    public @ResponseBody String manager() {
        return "manager";
    }

    @PostMapping("/token")
    public String token() {
        return "<h1>token</h1>";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admintools")
    public @ResponseBody String admintools() {
        return "admintools";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/data")
    public @ResponseBody String data() {
        return "data";
    }


}