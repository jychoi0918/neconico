package com.neconico.neconico.file.process;

import com.neconico.neconico.file.policy.FilePolicy;
import com.neconico.neconico.file.s3provider.S3Deleter;
import com.neconico.neconico.file.s3provider.S3Uploader;
import com.neconico.neconico.immutable.FileResultInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class S3FileProcess implements FileProcess{

    private S3Uploader s3Uploader;

    private S3Deleter s3Deleter;

    private String dirName;

    private int fileCount;

    private StringBuffer fileNames = new StringBuffer();

    private StringBuffer fileUrls = new StringBuffer();

    public S3FileProcess(FilePolicy filePolicy) throws IllegalArgumentException {
        this.dirName = filePolicy.getDirName();
        this.fileCount = filePolicy.getFileCount();
    }

    public void setUploaderAndDeleter(S3Uploader uploader, S3Deleter deleter) {
        s3Uploader = uploader;
        s3Deleter = deleter;
    }

    @Override
    public FileResultInfo uploadFile(MultipartFile... files) throws IOException, IllegalStateException, IllegalArgumentException {
        if(files.length > fileCount) {
            throw new IllegalArgumentException("There are more files than should be received");
        }

        for(MultipartFile file : files) {
            fileUrls.append(s3Uploader.upload(file, convertUniqueFileName(file), dirName) + ":");
        }

        deleteLastColon();

        return new FileResultInfo(fileUrls.toString(), fileNames.toString());
    }

    @Override
    public boolean canDeleteFiles(String fileName) throws IllegalArgumentException {
        if(fileName == null || fileName.length() == 0) {
            throw new IllegalArgumentException("Entered the wrong path");
        }

        String[] fileOriginNames = fileName.split(":");

        if(fileOriginNames.length > 1) {
            return deleteFiles(fileOriginNames);
        }

        s3Deleter.delete(dirName, fileOriginNames[0]);
        return true;
    }

    private String convertUniqueFileName(MultipartFile file) {
        String fileName = UUID.randomUUID() + file.getOriginalFilename();

        fileNames.append(fileName + ":");

        return fileName;
    }

    private void deleteLastColon() {
        fileNames.deleteCharAt(fileNames.length() - 1);
        fileUrls.deleteCharAt(fileUrls.length() - 1);
    }

    private boolean deleteFiles(String[] fileOriginNames) {
        s3Deleter.deletes(dirName, convertList(fileOriginNames));
        return true;
    }

    private List<String> convertList(String[] fileOriginNames) {
        return Arrays.stream(fileOriginNames)
                .map(f -> dirName + "/" + f)
                .collect(Collectors.toList());
    }

}
