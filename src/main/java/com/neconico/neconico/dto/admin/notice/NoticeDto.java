package com.neconico.neconico.dto.admin.notice;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;


@Slf4j
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
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
