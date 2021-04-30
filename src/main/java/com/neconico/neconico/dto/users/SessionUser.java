package com.neconico.neconico.dto.users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Getter
@Setter
@Alias("sessionUser")
@NoArgsConstructor
public class SessionUser implements Serializable {

    private Long userId;

    private String accountId;

    private String accountName;

    private String email;

    private String authority;
}
