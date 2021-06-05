package com.neconico.neconico.service.admin.advertisement;

import lombok.Getter;

@Getter
public enum AdvertStatus {

    ADVERTISING("광고중"),
    HIDDEN("숨김");

    private String advertStatus;

    AdvertStatus(String advertStatus) {
        this.advertStatus = advertStatus;
    }
}
