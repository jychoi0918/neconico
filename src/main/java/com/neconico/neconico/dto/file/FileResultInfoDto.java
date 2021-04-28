package com.neconico.neconico.dto.file;

import lombok.Getter;

@Getter
public class FileResultInfoDto {
    private String fileUrls;

    private String fileNames;

    public FileResultInfoDto(String fileUrls, String fileNames) {
        this.fileUrls = fileUrls;
        this.fileNames = fileNames;
    }
}
