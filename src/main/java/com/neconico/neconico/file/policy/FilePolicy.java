package com.neconico.neconico.file.policy;

import lombok.Getter;

@Getter
public enum FilePolicy {
    STORE("store", 1),
    ADVERTISEMENT("adver", 1),
    ITEM("item", 3);

    private String dirName;
    private int fileCount;

    FilePolicy(String dirName, int fileCount) {
        this.dirName = dirName;
        this.fileCount = fileCount;
    }
}
