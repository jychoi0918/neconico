package com.neconico.neconico.config.security.service;

import com.neconico.neconico.dto.users.UserInfoDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AccountContext extends User {

    private final UserInfoDto userInfoDto;

    public AccountContext(UserInfoDto userInfoDto, Collection<? extends GrantedAuthority> authorities) {
        super(userInfoDto.getAccountId(), userInfoDto.getAccountPw(), authorities);
        this.userInfoDto = userInfoDto;
    }

    public UserInfoDto getUserInfoDto() {
        return userInfoDto;
    }
}
