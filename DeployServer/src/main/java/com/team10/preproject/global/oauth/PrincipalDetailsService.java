package com.team10.preproject.global.oauth;


import com.team10.preproject.member.entity.Member;
import com.team10.preproject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member memberEntity = memberRepository.findByUsername(username);

                if (memberEntity == null) {
            throw new UsernameNotFoundException("Not found!");
        }

        return new PrincipalDetails(memberEntity);
    }
}