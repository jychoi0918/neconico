package com.neconico.neconico.dto.admin.notice;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Slf4j
@Getter @Setter
@NoArgsConstructor
@ToString @Alias("NoticeReturnDto")
public class NoticeReturnDto {
    private Long noticeId;
    private String accountId;
    private String content;
    private String title;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String noticeStatus;

}
