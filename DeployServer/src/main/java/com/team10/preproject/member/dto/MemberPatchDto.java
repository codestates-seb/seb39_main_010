package com.team10.preproject.member.dto;

import com.team10.preproject.global.validator.NotSpace;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
public class MemberPatchDto {
    private long memberId;

    @NotSpace(message = "NickName cannot be null")
    @Length(max = 20)
    private String nickname;

    @NotSpace
    @Email
    private String email;

    @NotSpace(message = "Password cannot be null")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
            message = "Password Must be 8 to 20 Characters and a Combination of UpperCase Letters and LowerCase Letters and Special Characters.")
    private String password;

    @Length(max = 2000)
    private String picture;

    @Length(max = 200)
    private String favoriteCompany;

    @Length(max = 200)
    private String selfIntroductions;
//    private Member.MemberStatus memberStatus;

    public void setMemberId(long memberId) {

        this.memberId = memberId;
    }
}
