package com.neconico.neconico.dto.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Getter @Setter
@NoArgsConstructor
@Alias("userFindAccountPwDto")
public class UserFindAccountPwDto {

    @NotEmpty(message = "아이디를 입력해 주세요.")
    @Size(min = 3, max = 10, message = "아이디는 최소 3글자에서 10글자 사이이어야 합니다.")
    private String accountId;

    @Pattern(regexp = "^(01[1|6|7|8|9|0])-(\\d{3,4})-(\\d{4})$", message = "핸드폰 번호가 알맞지 않습니다.")
    @NotEmpty(message = "핸드폰 번호를 입력해 주세요.")
    private String phoneNumber;

    @Email(message = "이메일 형식에 알맞지 않습니다.")
    @NotEmpty(message = "이메일을 입력해 주세요.")
    private String email;
}
