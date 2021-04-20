package com.neconico.neconico.dto.users;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SessionUser implements Serializable {

    private Long userId;

    private String accountId;

    private String accountName;

    private String email;

    private String authority;

    @Builder()
    public SessionUser(Long userId, String accountId, String accountName, String email, String authority) {
        this.userId = userId;
        this.accountId = accountId;
        this.accountName = accountName;
        this.email = email;
        this.authority = authority;
    }
}
