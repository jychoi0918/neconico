package com.neconico.neconico.dto.admin.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Alias("UserListDto")
@Getter @Setter
@NoArgsConstructor
@ToString
public class UserListDto {
    private Long userId;
    private String accountId;
    private String accountName;
    private String gender;
    private String email;
    private LocalDateTime createDate;
}
