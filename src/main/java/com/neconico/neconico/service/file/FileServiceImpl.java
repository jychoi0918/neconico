package com.neconico.neconico.service.file;

import com.neconico.neconico.file.policy.FilePolicy;
import com.neconico.neconico.file.process.FileProcess;
import com.neconico.neconico.file.process.FileProcessFactory;
import com.neconico.neconico.file.process.S3FileProcess;
import com.neconico.neconico.file.s3provider.S3Deleter;
import com.neconico.neconico.file.s3provider.S3Uploader;
import com.neconico.neconico.dto.file.FileResultInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

    private final S3Uploader s3Uploader;
    private final S3Deleter s3Deleter;

    @Override
    public FileResultInfoDto uploadFiles(FilePolicy filePolicy, MultipartFile... files)
            throws IOException, IllegalStateException, IllegalArgumentException {

        FileProcess fileProcess = FileProcessFactory.getFileProcess(filePolicy);

        if(isS3FileProcess(fileProcess)) {
            setS3UploaderAndS3Deleter(fileProcess);
        }

        return fileProcess.uploadFile(files);
    }

    @Override
    public void deleteFiles(FilePolicy filePolicy, String fileNames) throws IllegalArgumentException {
        FileProcess fileProcess = FileProcessFactory.getFileProcess(filePolicy);

        if(isS3FileProcess(fileProcess)) {
            setS3UploaderAndS3Deleter(fileProcess);
        }

        fileProcess.deleteFiles(fileNames);
    }

    private boolean isS3FileProcess(FileProcess fileProcess) {
        return fileProcess instanceof S3FileProcess;
    }

    private void setS3UploaderAndS3Deleter(FileProcess fileProcess) {
        ((S3FileProcess) fileProcess).setUploaderAndDeleter(s3Uploader, s3Deleter);
    }

}
