package com.neconico.neconico.dto.admin.notice;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;


@Getter @Setter
@NoArgsConstructor
@ToString()
@Alias("NoticeDto")
public class NoticeDto {
    private Long noticeId;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String noticeStatus;



}
