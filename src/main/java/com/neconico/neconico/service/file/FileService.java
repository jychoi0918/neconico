package com.neconico.neconico.service.file;

import com.neconico.neconico.dto.file.FileResultInfoDto;
import com.neconico.neconico.file.policy.FilePolicy;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    FileResultInfoDto uploadFiles(FilePolicy filePolicy, MultipartFile... files) throws IOException, IllegalStateException, IllegalArgumentException;

    void deleteFiles(FilePolicy filePolicy, String fileNames) throws IllegalArgumentException;

}
