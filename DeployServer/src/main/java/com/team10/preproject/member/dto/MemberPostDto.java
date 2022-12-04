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
    private final String email;

    @NotBlank(message = "ID cannot be null")
    @Length(max = 20)
    private final String username;

    @NotBlank(message = "Nickname cannot be null")
    @Length(max = 20)
    private final String nickname;

    @NotBlank(message = "Password cannot be null")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
            message = "Password Must be 8 to 20 Characters and a Combination of UpperCase Letters and LowerCase Letters and Special Characters.")
    private final String password;

    @Length(max = 2000)
    private final String picture;

    @Length(max = 200)
    private final String favoriteCompany;

    @Length(max = 200)
    private final String selfIntroductions;

    public MemberPostDto(String email, String username, String nickname, String password, String picture, String favoriteCompany, String selfIntroductions) {
        this.email = email;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.picture = picture;
        this.favoriteCompany = favoriteCompany;
        this.selfIntroductions = selfIntroductions;
    }
}
