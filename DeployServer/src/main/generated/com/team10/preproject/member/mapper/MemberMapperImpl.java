package com.team10.preproject.member.mapper;

import com.team10.preproject.member.dto.MemberDto;
import com.team10.preproject.member.entity.Member;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-02T04:35:18+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberPostToMember(MemberDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Member member = new Member();

        member.setEmail( requestBody.getEmail() );
        member.setNickname( requestBody.getNickname() );
        member.setPassword( requestBody.getPassword() );

        return member;
    }

    @Override
    public Member memberPatchToMember(MemberDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberId( requestBody.getMemberId() );
        member.setNickname( requestBody.getNickname() );
        member.setPassword( requestBody.getPassword() );

        return member;
    }

    @Override
    public MemberDto.Response memberToMemberResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        long memberId = 0L;
        String username = null;
        String email = null;
        String nickname = null;
        String password = null;

        if ( member.getMemberId() != null ) {
            memberId = member.getMemberId();
        }
        username = member.getUsername();
        email = member.getEmail();
        nickname = member.getNickname();
        password = member.getPassword();

        MemberDto.Response response = new MemberDto.Response( memberId, username, email, nickname, password );

        return response;
    }

    @Override
    public List<MemberDto.Response> membersToMemberResponses(List<Member> members) {
        if ( members == null ) {
            return null;
        }

        List<MemberDto.Response> list = new ArrayList<MemberDto.Response>( members.size() );
        for ( Member member : members ) {
            list.add( memberToMemberResponse( member ) );
        }

        return list;
    }
}
