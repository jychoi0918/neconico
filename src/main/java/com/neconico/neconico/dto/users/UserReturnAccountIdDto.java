package com.neconico.neconico.dto.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter @Setter
@Alias("userReturnAccountIdDto")
@NoArgsConstructor
public class UserReturnAccountIdDto {
    private String accountId;
}
