package com.neconico.neconico.file.process;

import com.neconico.neconico.dto.file.FileResultInfoDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileProcess {

    FileResultInfoDto uploadFile(MultipartFile... files) throws IOException, IllegalStateException, IllegalArgumentException;

    void deleteFiles(String fileNames) throws IllegalArgumentException;

}
