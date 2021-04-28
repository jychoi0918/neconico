package com.neconico.neconico.file.process;

import com.neconico.neconico.file.policy.FilePolicy;
import com.neconico.neconico.immutable.FileResultInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LocalFileProcess implements FileProcess {

    private static final String LOCAL_DIR = "C:/Temp/";

    private String dirName;

    private int fileCount;

    private StringBuffer fileUrls = new StringBuffer();

    private StringBuffer fileNames = new StringBuffer();

    public LocalFileProcess(FilePolicy filePolicy) {
        dirName = filePolicy.getDirName();
        fileCount = filePolicy.getFileCount();
    }

    @Override
    public FileResultInfo uploadFile(MultipartFile... files) throws IOException, IllegalStateException, IllegalArgumentException {
        if(files.length > fileCount) {
            throw new IllegalArgumentException("There are more files than should be received");
        }

        for (MultipartFile file : files) {
            createDir();

            File dest = createDest(file);

            file.transferTo(dest);

            makeFileNamesAndURLs(dest);

        }

        return makeFileResultInfo();
    }

    private void createDir() {
        File file = new File(LOCAL_DIR + dirName + "/");
        if (!file.exists()) {
            file.mkdir();
        }
    }

    private File createDest(MultipartFile file) {
        return new File(LOCAL_DIR + dirName + "/" + convertUniqueName(file.getOriginalFilename()));
    }

    private String convertUniqueName(String originalFileName) {
        return UUID.randomUUID() + originalFileName;
    }

    private void makeFileNamesAndURLs(File dest) throws IOException {
        fileNames.append(dest + ":");
        fileUrls.append(dest.toURI().toURL() + ":");
    }

    private FileResultInfo makeFileResultInfo() {
        String fileUrls = this.fileUrls.deleteCharAt(this.fileUrls.length() - 1).toString();

        String fileNames = this.fileNames.deleteCharAt(this.fileNames.length() - 1).toString();

        return new FileResultInfo(fileUrls, fileNames);
    }

    @Override
    public boolean deleteFiles(String fileNames) throws IllegalArgumentException {
        if(fileNames == null || fileNames.length() == 0) {
            throw new IllegalArgumentException("Entered the wrong path");
        }

        String[] originalFileNames = fileNames.split(":");

        return deleteOriginFiles(originalFileNames);

    }

    private boolean deleteOriginFiles(String[] originalFileNames) {
        List<Boolean> deleteResults = new ArrayList<>();

        for(String originalFileName : originalFileNames) {
            File originalFile = new File(originalFileName);

            if(originalFile.exists()) {
                deleteResults.add(originalFile.delete());
            }

            deleteResults.add(false);
        }

        return countResult(deleteResults);

    }

    private boolean countResult(List<Boolean> deleteResults) {
        int resultCount = 0;

        for(boolean result : deleteResults) {
            if(result) {
                resultCount++;
            }
        }

        return resultCount > 0 ;
    }
}
