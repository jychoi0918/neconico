package com.neconico.neconico.dto.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Alias("userInfoDto")
@Getter @Setter
@NoArgsConstructor
public class UserInfoDto {
    private Long userId;
    private String accountId;
    private String accountPw;
    private String accountName;
    private String gender;
    private String birthdate;
    private String email;
    private String phoneNumber;
    private String zipNo;
    private String address;
    private String infoAgreement;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private String authority;
}
