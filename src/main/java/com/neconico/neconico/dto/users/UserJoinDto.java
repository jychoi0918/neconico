package com.neconico.neconico.dto.users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter @Setter
@Alias("userJoinDto")
@NoArgsConstructor
public class UserJoinDto {
    //PK값
    private Long userId;

    @NotEmpty(message = "아이디를 입력해 주세요.")
    @Size(min = 3, max = 10, message = "아이디는 최소 3글자에서 10글자 사이이어야 합니다.")
    private String accountId;

    @NotEmpty(message = "비밀번호를 입력해 주세요.")
    @Size(min = 4, max = 16, message = "비밀번호는 최소 4글자에서 최대 16글자 사이이어야 합니다.")
    private String accountPw;

    @NotEmpty(message = "이름을 입력해 주세요.")
    @Size(min = 2, max = 10, message = "이름은 최소 2글자에서 최대 10글자 사이이어야 합니다.")
    private String accountName;

    private String gender;

    @FutureOrPresent(message = "날짜가 알맞지 않습니다.")
    @Pattern(regexp = "^\\d{6}$", message = "생년월일 형식에 맞지 않습니다.")
    @NotEmpty(message = "생년월일을 입력해 주세요.")
    private String brithdate;

    @Email(message = "이메일 형식에 알맞지 않습니다.")
    @NotEmpty(message = "이메일을 입력해 주세요.")
    private String email;

    @Pattern(regexp = "^(01[1|6|7|8|9|0])-(\\d{3,4})-(\\d{4})$", message = "핸드폰 번호가 알맞지 않습니다.")
    @NotEmpty(message = "핸드폰 번호를 입력해 주세요.")
    private String phoneNumber;

    @NotEmpty(message = "우편번호를 입력해주세요.")
    private String zipNo;

    @NotEmpty(message = "집 주소를 입력해주세요.")
    private String address;

    private String infoAgreement;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private String authority;

}
