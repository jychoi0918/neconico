package com.neconico.neconico.dto.admin.notice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter @Setter
@NoArgsConstructor
@Alias("NoticeStatusDto")
public class NoticeStatusDto {
    private Long noticeId;
    private String noticeStatus;
}
