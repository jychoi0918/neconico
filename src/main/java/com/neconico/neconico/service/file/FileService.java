package com.neconico.neconico.service.file;

import com.neconico.neconico.file.process.FileProcess;
import com.neconico.neconico.dto.file.FileResultInfoDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    void setFileProcess(FileProcess fileProcess);

    FileResultInfoDto uploadFiles(MultipartFile... files) throws IOException, IllegalStateException, IllegalArgumentException;

    boolean canDeleteFiles(String fileNames) throws IllegalArgumentException;

}
