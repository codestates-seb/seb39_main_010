package com.team10.preproject.member.dto;

import com.team10.preproject.global.validator.NotSpace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MemberDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class  Post {

        @NotBlank
        @Email
        private String email;

        @NotBlank(message = "ID cannot be null")
        @Length(max = 20)
        private String username;

        @NotBlank(message = "NickName cannot be null")
        @Length(max = 20)
        private String nickname;

        @NotBlank(message = "Password cannot be null")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "Password Must be 8 to 20 Characters and a Combination of UpperCase Letters and LowerCase Letters and Special Characters.")
        private String password;

        @Length(max = 2000)
        private String picture;

        @Length(max = 200)
        private String favoriteCompany;

        @Length(max = 200)
        private String selfIntroductions;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

        private long memberId;

        @NotSpace(message = "Nickname cannot be null")
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
//        private Member.MemberStatus memberStatus;

        public void setMemberId(long memberId) {

            this.memberId = memberId;
        }
    }

    @AllArgsConstructor
    @Getter
    public static class Response {

        private long memberId;
        private String username;
        private String email;
        private String nickname;
        private String password;
        private String picture;
        private String favoriteCompany;
        private String selfIntroductions;
//        private Member.MemberStatus memberStatus;
//        public String getMemberStatus() { return memberStatus.getStatus(); }
    }
}
