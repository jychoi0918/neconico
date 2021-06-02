package com.neconico.neconico.service.admin;


import lombok.Getter;

@Getter
public enum Status {

    PUBLIC("공개"),
    ADVERTISING("광고중"),
    HIDDEN("숨김");

    private String status;

    Status(String status){
        this.status=status;
    }

}


