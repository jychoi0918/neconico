package com.neconico.neconico.service.file;

import com.neconico.neconico.dto.file.FileResultInfoDto;
import com.neconico.neconico.file.policy.FilePolicy;
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
class FileServiceImplTest {

    @Autowired
    private FileServiceImpl fileService;

    private MockMultipartFile[] files;

    @BeforeEach
    void getMockMultipartFiles() {
        files = createMockMultipartFiles();
    }


    @Test
    @DisplayName("item파일 1개 저장")
    void upload_one_file_to_item() throws Exception {
        //given
        int size = 1;

        MockMultipartFile[] mockMultipartFiles = createMockMultipartFiles(size);

        //when
        FileResultInfoDto fileResultInfoDto = fileService.uploadFiles(FilePolicy.ITEM, mockMultipartFiles);

        //then
        assertThat(fileResultInfoDto.getFileNames()).isNotNull();
        assertThat(fileResultInfoDto.getFileUrls()).isNotNull();
    }

    @Test
    @DisplayName("item파일 2개 저장")
    void upload_two_file_to_item() throws Exception {
        //given
        int size = 2;

        MockMultipartFile[] mockMultipartFiles = createMockMultipartFiles(size);

        //when
        FileResultInfoDto fileResultInfoDto = fileService.uploadFiles(FilePolicy.ITEM, mockMultipartFiles);

        //then
        assertThat(fileResultInfoDto.getFileNames()).isNotNull();
        assertThat(fileResultInfoDto.getFileUrls()).isNotNull();
    }

    @Test
    @DisplayName("item파일 3개 저장")
    void upload_three_file_to_item() throws Exception {
        //given
        int size = 3;

        MockMultipartFile[] mockMultipartFiles = createMockMultipartFiles(size);

        //when
        FileResultInfoDto fileResultInfoDto = fileService.uploadFiles(FilePolicy.ITEM, mockMultipartFiles);

        //then
        assertThat(fileResultInfoDto.getFileNames()).isNotNull();
        assertThat(fileResultInfoDto.getFileUrls()).isNotNull();
    }

    @Test
    @DisplayName("item파일 4개 저장")
    void upload_four_file_to_item() {

        int size = 4;

        MockMultipartFile[] mockMultipartFiles = createMockMultipartFiles(size);

        assertThatThrownBy(() -> fileService.uploadFiles(FilePolicy.ITEM, mockMultipartFiles))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("There are more files than should be received");
    }

    @Test
    @DisplayName("store파일 1개 저장")
    void upload_one_file_to_store() throws Exception {
        //given
        int size = 1;

        MockMultipartFile[] mockMultipartFiles = createMockMultipartFiles(size);

        //when
        FileResultInfoDto fileResultInfoDto = fileService.uploadFiles(FilePolicy.STORE, mockMultipartFiles);

        //then
        assertThat(fileResultInfoDto.getFileNames()).isNotNull();
        assertThat(fileResultInfoDto.getFileUrls()).isNotNull();
    }

    @Test
    @DisplayName("store파일 2개 저장")
    void upload_two_file_to_store() {
        int size = 2;

        MockMultipartFile[] mockMultipartFiles = createMockMultipartFiles(size);

        assertThatThrownBy(() -> fileService.uploadFiles(FilePolicy.STORE, mockMultipartFiles))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("There are more files than should be received");
    }

    @Test
    @DisplayName("store파일 1개 저장")
    void upload_one_file_to_advertisement() throws Exception {
        //given
        int size = 1;

        MockMultipartFile[] mockMultipartFiles = createMockMultipartFiles(size);

        //when
        FileResultInfoDto fileResultInfoDto = fileService.uploadFiles(FilePolicy.ADVERTISEMENT, mockMultipartFiles);

        //then
        assertThat(fileResultInfoDto.getFileNames()).isNotNull();
        assertThat(fileResultInfoDto.getFileUrls()).isNotNull();
    }

    @Test
    @DisplayName("store파일 2개 저장")
    void upload_two_file_to_advertisement() {
        int size = 2;

        MockMultipartFile[] mockMultipartFiles = createMockMultipartFiles(size);

        assertThatThrownBy(() -> fileService.uploadFiles(FilePolicy.ADVERTISEMENT, mockMultipartFiles))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("There are more files than should be received");
    }


    private MockMultipartFile[] createMockMultipartFiles(int size) {
        return Arrays.copyOf(files, size);
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
}
