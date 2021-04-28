package com.neconico.neconico.file.process;

import com.neconico.neconico.immutable.FileResultInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileProcess {

    FileResultInfo uploadFile(MultipartFile... files) throws IOException, IllegalStateException, IllegalArgumentException;

    boolean canDeleteFiles(String fileNames) throws IllegalArgumentException;

}
