package com.neconico.neconico.dto.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter @Getter
@NoArgsConstructor
@Alias("userFindAccountIdForm")
public class UserFindAccountIdForm {

    @NotEmpty(message = "이름을 입력해 주세요.")
    @Size(min = 2, max = 10, message = "이름은 최소 2글자에서 최대 10글자 사이이어야 합니다.")
    private String accountName;

    @Email(message = "이메일 형식에 알맞지 않습니다.")
    @NotEmpty(message = "이메일을 입력해 주세요.")
    private String email;
}
