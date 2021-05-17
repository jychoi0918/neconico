package com.neconico.neconico.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SessionUser implements Serializable {

    private Long userId;

    private String accountId;

    private String accountName;

    private String email;

    private String authority;
}
