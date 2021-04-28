package com.neconico.neconico.file.process;

import com.neconico.neconico.file.policy.FilePolicy;
import com.neconico.neconico.file.s3provider.S3Deleter;
import com.neconico.neconico.file.s3provider.S3Uploader;
import com.neconico.neconico.dto.file.FileResultInfoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class S3FileProcessTest {

    @Autowired
    private S3Uploader s3Uploader;

    @Autowired
    private S3Deleter s3Deleter;

    private FileProcess fileProcess;

    private MockMultipartFile[] files;

    @BeforeEach
    void getMockMultipartFiles() {
        files = createMockMultipartFiles();
    }

    private MockMultipartFile[] createMockMultipartFiles() {

        MockMultipartFile[] files = new MockMultipartFile[4];

        for (int i = 0; i < 4; i++) {
            StringBuffer fileName = new StringBuffer("test" + i + 1 + ".jpg");

            files[i] = new MockMultipartFile(
                    "content",
                    fileName.toString(),
                    "multipart/mixed",
                    "hello word".getBytes(StandardCharsets.UTF_8));
        }

        return files;
    }

    /**
     * 파일정책에 따른 파일저장
     * 파일정책에 따른 파일삭제
     */
    @Test
    @DisplayName("STORE 정책에 따른 파일 저장")
    void upload_according_to_store_file_policy() throws Exception {
        //given
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.STORE);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        MockMultipartFile[] files = Arrays.copyOf(this.files, 1);

        //when
        FileResultInfoDto fileResultInfoDto = fileProcess.uploadFile(files);

        //then
        assertThat(fileResultInfoDto.getFileNames()).contains(files[0].getOriginalFilename());
    }

    @Test
    @DisplayName("ITEM 정책에 따른 1개 파일 저장")
    void upload_one_file_according_to_item_file_policy() throws Exception {
        //given
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.ITEM);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        MockMultipartFile[] files = Arrays.copyOf(this.files, 1);

        //when
        FileResultInfoDto fileResultInfoDto = fileProcess.uploadFile(files);

        //then
        assertThat(fileResultInfoDto.getFileNames()).contains(files[0].getOriginalFilename());

    }

    @Test
    @DisplayName("ITEM 정책에 따른 3개 파일 저장")
    void upload_three_file_according_to_item_file_policy() throws Exception {
        //given
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.ITEM);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        MockMultipartFile[] files = Arrays.copyOf(this.files, 3);

        //when
        FileResultInfoDto fileResultInfoDto = fileProcess.uploadFile(files);

        //then
        assertThat(fileResultInfoDto.getFileNames())
                .contains(
                        files[0].getOriginalFilename(),
                        files[1].getOriginalFilename(),
                        files[2].getOriginalFilename()
                );
    }

    @Test
    @DisplayName("ADVERTISEMENT 정책에 따른 파일 저장")
    void upload_according_to_advertisement_file_policy() throws Exception {
        //given
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.ADVERTISEMENT);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        MockMultipartFile[] files = Arrays.copyOf(this.files, 1);

        //when
        FileResultInfoDto fileResultInfoDto = fileProcess.uploadFile(files);

        //then
        assertThat(fileResultInfoDto.getFileNames()).contains(files[0].getOriginalFilename());
    }

    @Test
    @DisplayName("STORE 정책에 따른 파일 삭제")
    void delete_according_to_store_file_policy() throws Exception {
        //given
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.STORE);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        MockMultipartFile[] files = Arrays.copyOf(this.files, 1);

        //when
        FileResultInfoDto fileResultInfoDto = fileProcess.uploadFile(files);

        boolean result = fileProcess.canDeleteFiles(fileResultInfoDto.getFileNames());

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("ITEM 정책에 따른 1개 파일 삭제")
    void delete_one_file_according_to_item_file_policy() throws Exception {
        //given
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.ITEM);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        MockMultipartFile[] files = Arrays.copyOf(this.files, 1);

        //when
        FileResultInfoDto fileResultInfoDto = fileProcess.uploadFile(files);

        boolean result = fileProcess.canDeleteFiles(fileResultInfoDto.getFileNames());

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("ITEM 정책에 따른 3개 파일 삭제")
    void delete_three_file_according_to_item_file_policy() throws Exception {
        //given
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.ITEM);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        MockMultipartFile[] files = Arrays.copyOf(this.files, 3);

        //when
        FileResultInfoDto fileResultInfoDto = fileProcess.uploadFile(files);

        boolean result = fileProcess.canDeleteFiles(fileResultInfoDto.getFileNames());

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("ADVERTISEMENT 정책에 따른 파일 삭제")
    void delete_according_to_advertisement_file_policy() throws Exception {
        //given
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.ADVERTISEMENT);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        MockMultipartFile[] files = Arrays.copyOf(this.files, 1);

        //when
        FileResultInfoDto fileResultInfoDto = fileProcess.uploadFile(files);

        boolean result = fileProcess.canDeleteFiles(fileResultInfoDto.getFileNames());

        //then
        assertThat(result).isTrue();
    }

    /**
     * 파일저장 예외처리
     */
    @Test
    @DisplayName("STORE 정책에 1개 보다 많은 파일이 들어갔을 경우 예외처리")
    void exception_handling_more_than_one_file_entered_in_store_policy() {
        //given
        MockMultipartFile[] files = Arrays.copyOf(this.files, 2);

        //when
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.STORE);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        //then
        assertThatThrownBy(() -> fileProcess.uploadFile(files))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("There are more files than should be received");
    }

    @Test
    @DisplayName("ADVERTISEMENT 정책에 1개보다 많은 파일이 들어갔을 경우 예외처리")
    void exception_handling_more_than_one_file_entered_in_advertisement_policy() {
        //given
        MockMultipartFile[] files = Arrays.copyOf(this.files, 2);

        //when
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.ADVERTISEMENT);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        //then
        assertThatThrownBy(() -> fileProcess.uploadFile(files))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("There are more files than should be received");
    }

    @Test
    @DisplayName("ITEM 정책에 3개보다 많은 파일이 들어갔을 경우 예외처리")
    void exception_handling_more_than_one_file_entered_in_item_policy() {
        //given
        MockMultipartFile[] files = Arrays.copyOf(this.files, 4);

        //when
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.ITEM);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        //then
        assertThatThrownBy(() -> fileProcess.uploadFile(files))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("There are more files than should be received");
    }


    @Test
    @DisplayName("ADVERTISEMENT 정책에 따른 파일삭제 경로에 빈값을 입력하였을 경우 예외처리")
    void exception_handling_incorrect_file_entered_in_advertisement_policy_deleteMethod() {
        //given
        String fileNames= "";

        //when
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.ADVERTISEMENT);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        //then
        assertThatThrownBy(() -> fileProcess.canDeleteFiles(fileNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Entered the wrong path");
    }

    @Test
    @DisplayName("ADVERTISEMENT 정책에 따른 파일삭제 경로에 null값을 입력하였을 경우 예외처리")
    void exception_handling_null_entered_in_advertisement_policy_deleteMethod() {
        //given
        String fileNames = null;

        //when
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.ADVERTISEMENT);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        //then
        assertThatThrownBy(() -> fileProcess.canDeleteFiles(fileNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Entered the wrong path");
    }


    @Test
    @DisplayName("ITEM 정책에 따른 파일삭제 경로를 잘못 입력하였을 경우 예외처리")
    void exception_handling_incorrect_file_entered_in_item_policy_deleteMethod() {
        //given
        String fileNames = "";

        //when
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.ITEM);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        //then
        assertThatThrownBy(() -> fileProcess.canDeleteFiles(fileNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Entered the wrong path");
    }

    @Test
    @DisplayName("ITEM 정책에 따른 파일삭제 경로에 null값을 입력하였을 경우 예외처리")
    void exception_handling_null_entered_in_item_policy_deleteMethod() {
        //given
        String fileNames = null;

        //when
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.ITEM);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        //then
        assertThatThrownBy(() -> fileProcess.canDeleteFiles(fileNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Entered the wrong path");
    }

    @Test
    @DisplayName("STORE 정책에 따른 파일삭제 경로에 빈값을 입력하였을 경우 예외처리")
    void exception_handling_incorrect_file_entered_in_store_policy_deleteMethod() {
        //given
        String fileNames = "";

        //when
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.STORE);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        //then
        assertThatThrownBy(() -> fileProcess.canDeleteFiles(fileNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Entered the wrong path");
    }

    @Test
    @DisplayName("STORE 정책에 따른 파일삭제 경로에 null값을 입력하였을 경우 예외처리")
    void exception_handling_null_entered_in_store_policy_deleteMethod() {
        //given
        String fileNames = null;

        //when
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.STORE);
        s3FileProcess.setUploaderAndDeleter(s3Uploader, s3Deleter);

        this.fileProcess = s3FileProcess;

        //then
        assertThatThrownBy(() -> fileProcess.canDeleteFiles(fileNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Entered the wrong path");
    }
}