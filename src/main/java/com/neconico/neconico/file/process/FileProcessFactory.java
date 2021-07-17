package com.neconico.neconico.file.process;

import com.neconico.neconico.file.policy.FilePolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileProcessFactory {

    private static String fileType;

    public static FileProcess getFileProcess(FilePolicy filePolicy) throws IllegalArgumentException {
        switch (fileType) {
            case "s3":
                return new S3FileProcess(filePolicy);
            case "local":
                return new LocalFileProcess(filePolicy);
            default:
                throw new IllegalArgumentException("filetype error");
        }
    }

    @Value("${file.type}")
    private void setFileType(String type) {
        fileType = type;
    }
}
