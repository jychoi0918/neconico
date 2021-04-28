package com.neconico.neconico.immutable;

import lombok.Getter;

@Getter
public class FileResultInfo {
    private String fileUrls;

    private String fileNames;

    public FileResultInfo(String fileUrls, String fileNames) {
        this.fileUrls = fileUrls;
        this.fileNames = fileNames;
    }
}
