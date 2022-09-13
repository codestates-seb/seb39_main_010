package com.team10.preproject.member.repository;

import com.team10.preproject.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Member findByEmail(String email);

    public Member findByUsername(String username);

//    Member save(Member member);
}
