package com.team10.preproject.member.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class MemberPostDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank(message = "Nickname cannot be null")
    @Length(max = 20)
    private String nickname;

    @NotBlank(message = "Password cannot be null")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
            message = "password must be 8-20 characters long and contain one uppercase and one lowercase and one special character.")
    private String password;
}
