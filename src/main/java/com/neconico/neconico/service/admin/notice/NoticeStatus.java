package com.neconico.neconico.service.admin.notice;

import lombok.Getter;

@Getter
public enum NoticeStatus {

    PUBLIC("공개"),
    HIDDEN("숨김");

    private String noticeStatus;

    NoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus;
    }


}
