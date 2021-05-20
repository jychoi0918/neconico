package com.neconico.neconico.dto.admin.notice;

import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter @Setter
public class NoticeViewDto {
    private Long noticeId;
    private String accountId;
    private String content;
    private String title;
    private String createdDate;
    private String modifiedDate;
    private String noticeStatus;
    private boolean compareDate;

    public NoticeViewDto(NoticeReturnDto noticeReturnDto) {
        noticeId = noticeReturnDto.getNoticeId();
        accountId = noticeReturnDto.getAccountId();
        content = noticeReturnDto.getContent();
        title = noticeReturnDto.getTitle();
        createdDate = noticeReturnDto.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        modifiedDate = noticeReturnDto.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        noticeStatus = noticeReturnDto.getNoticeStatus();
        compareDate = noticeReturnDto.getCreatedDate().toString().equals(noticeReturnDto.getModifiedDate().toString());
    }
}
