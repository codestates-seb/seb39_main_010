package com.team10.preproject.member.dto;

import com.team10.preproject.validator.NotSpace;
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

        @NotBlank(message = "Name cannot be null")
        @Length(max = 20)
        private String nickname;

        @NotBlank(message = "Password cannot be null")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "password must be 8-20 characters long and contain one uppercase and one lowercase and one special character.")
        private String password;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long memberId;

        @NotSpace(message = "Nickname cannot be null")
        @Length(max = 20)
        private String nickname;

        @NotSpace(message = "Password cannot be null")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
                message = "password must be 8-20 characters long and contain one uppercase and one lowercase and one special character.")
        private String password;

//        private Member.MemberStatus memberStatus;

        public void setMemberId(long memberId) { this.memberId = memberId; }
    }

    @AllArgsConstructor
    @Getter
    public static class Response {
        private long memberId;
        private String username;
        private String email;
        private String nickname;
        private String password;
//        private Member.MemberStatus memberStatus;

//        public String getMemberStatus() { return memberStatus.getStatus(); }
    }
}
