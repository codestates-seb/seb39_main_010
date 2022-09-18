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

    @NotBlank(message = "Username cannot be null")
    @Length(max = 20)
    private String username;


    @NotBlank(message = "Nickname cannot be null")
    @Length(max = 20)
    private String nickname;

    @NotBlank(message = "Password cannot be null")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
            message = "Password Must be 8 to 20 Characters and a Combination of UpperCase/LowerCase Letters and Special Characters")
    private String password;
}
