package com.team10.preproject.member.repository;

import com.team10.preproject.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByEmail(String email);
    public Member findByUsername(String username);
    public Member findByMemberId(Long memberId);
    public Member findByNickname(String nickname);

    @Query("SELECT u FROM Member u WHERE u.verificationCode = ?1")
    public Member findByVerificationCode(String code);
}
