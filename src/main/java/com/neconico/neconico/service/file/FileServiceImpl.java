package com.neconico.neconico.service.file;

import com.neconico.neconico.file.process.FileProcess;
import com.neconico.neconico.file.process.S3FileProcess;
import com.neconico.neconico.file.s3provider.S3Deleter;
import com.neconico.neconico.file.s3provider.S3Uploader;
import com.neconico.neconico.immutable.FileResultInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

    private final S3Uploader s3Uploader;

    private final S3Deleter s3Deleter;

    private FileProcess fileProcess;

    @Override
    public void setFileProcess(FileProcess fileProcess) {

        if(fileProcess instanceof S3FileProcess) {
            ((S3FileProcess) fileProcess).setUploaderAndDeleter(s3Uploader, s3Deleter);
        }
        this.fileProcess = fileProcess;
    }

    @Override
    public FileResultInfo uploadFiles(MultipartFile... files) throws IOException, IllegalStateException, IllegalArgumentException {
        return fileProcess.uploadFile(files);
    }

    @Override
    public boolean canDeleteFiles(String fileNames) throws IllegalArgumentException {
        return fileProcess.canDeleteFiles(fileNames);
    }

}
