package com.neconico.neconico.service.file;

import com.neconico.neconico.file.policy.FilePolicy;
import com.neconico.neconico.file.process.LocalFileProcess;
import com.neconico.neconico.file.process.S3FileProcess;
import com.neconico.neconico.dto.file.FileResultInfoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

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

    @ParameterizedTest(name = "{index} -> FilePolicy가 {0}일때")
    @DisplayName("Local프로세스 전략을 넣을시 해당 서비스 필드값에 LocalProcess가 잘 참조가 되었는지 확인")
    @EnumSource(FilePolicy.class)
    void confirm_fieldvalue_localprocess_according_to_strategy(FilePolicy filePolicy) {
        //given
        LocalFileProcess localFileProcess = new LocalFileProcess(filePolicy);

        //when
        fileService.setFileProcess(localFileProcess);

        //then
        assertThat(fileService)
                .extracting("fileProcess")
                .isInstanceOf(LocalFileProcess.class);

    }


    @ParameterizedTest(name = "{index} -> FilePolicy가 {0}일때")
    @DisplayName("S3프로세스 전략을 넣을시 해당 서비스 필드값에 S3Process가 잘 참조가 되었는지 확인")
    @EnumSource(FilePolicy.class)
    void confirm_fieldvalue_s3process_according_to_strategy(FilePolicy filePolicy) {
        //given
        S3FileProcess s3FileProcess = new S3FileProcess(filePolicy);

        //when
        fileService.setFileProcess(s3FileProcess);

        //then
        assertThat(fileService)
                .extracting("fileProcess")
                .isInstanceOf(S3FileProcess.class);

    }

    /**
     * Local,S3 process일때 파일저장
     */
    @ParameterizedTest(name = "{index} -> FilePolicy가 {0}이고 파일개수 {1}개 일때")
    @DisplayName("로컬프로세스일때 정책들에 따른 파일저장")
    @CsvSource({"'STORE', 1", "'ITEM', 3", "'ADVERTISEMENT', 1"})
    void upload_file_when_using_local_process(String filePolicy, int fileCount) throws Exception {
        //given
        MockMultipartFile[] files = Arrays.copyOf(this.files, fileCount);

        LocalFileProcess localFileProcess = new LocalFileProcess(FilePolicy.valueOf(filePolicy));

        //when
        fileService.setFileProcess(localFileProcess);

        FileResultInfoDto fileResultInfoDto = fileService.uploadFiles(files);

        String[] fileNames = createFileName(files);

        //then
        assertThat(fileResultInfoDto.getFileNames()).contains(fileNames);

    }

    @ParameterizedTest(name = "{index} -> FilePolicy가 {0}이고 파일개수 {1}개 일때")
    @DisplayName("S3프로세스일때 정책들에 따른 파일저장")
    @CsvSource({"'STORE', 1", "'ITEM', 3", "'ADVERTISEMENT', 1"})
    void upload_file_when_using_s3_process(String filePolicy, int fileCount) throws Exception {
        //given
        MockMultipartFile[] files = Arrays.copyOf(this.files, fileCount);

        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.valueOf(filePolicy));

        //when
        fileService.setFileProcess(s3FileProcess);

        FileResultInfoDto fileResultInfoDto = fileService.uploadFiles(files);

        String[] fileNames = createFileName(files);

        //then
        assertThat(fileResultInfoDto.getFileNames()).contains(fileNames);

    }

    private String[] createFileName(MultipartFile[] files) {
        String[] fileNames = new String[files.length];

        for(int i=0; i<files.length; i++) {
            fileNames[i] = files[i].getOriginalFilename();
        }
        return fileNames;
    }

    /**
     * Local,S3 process일때 파일삭제
     */
    @ParameterizedTest(name = "{index} -> FilePolicy가 {0}이고 파일개수 {1}개 일때")
    @DisplayName("로컬프로세스일때 정책들에 따른 파일저장")
    @CsvSource({"'STORE', 1", "'ITEM', 3", "'ADVERTISEMENT', 1"})
    void delete_file_when_using_local_process(String filePolicy, int fileCount) throws Exception {
        //given
        MockMultipartFile[] files = Arrays.copyOf(this.files, fileCount);

        LocalFileProcess localFileProcess = new LocalFileProcess(FilePolicy.valueOf(filePolicy));

        //when
        fileService.setFileProcess(localFileProcess);

        FileResultInfoDto fileResultInfoDto = fileService.uploadFiles(files);

        boolean result = fileService.canDeleteFiles(fileResultInfoDto.getFileNames());

        //then
        assertThat(result).isTrue();
    }

    @ParameterizedTest(name = "{index} -> FilePolicy가 {0}이고 파일개수 {1}개 일때")
    @DisplayName("S3프로세스일때 정책들에 따른 파일저장")
    @CsvSource({"'STORE', 1", "'ITEM', 3", "'ADVERTISEMENT', 1"})
    void delete_file_when_using_s3_process(String filePolicy, int fileCount) throws Exception {
        //given
        MockMultipartFile[] files = Arrays.copyOf(this.files, fileCount);

        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.valueOf(filePolicy));

        //when
        fileService.setFileProcess(s3FileProcess);

        FileResultInfoDto fileResultInfoDto = fileService.uploadFiles(files);

        boolean result = fileService.canDeleteFiles(fileResultInfoDto.getFileNames());

        //then
        assertThat(result).isTrue();
    }

    /**
     * Local,S3 process일때 파일저장 예외처리
     */
    @ParameterizedTest(name = "{index} -> FilePolicy가 {0}이고 파일개수 {1}개 일때")
    @DisplayName("로컬프로세스일때 정책들에 따른 파일저장시 해당 정책에 맞지않은 파일개수가 들어갔을때 예외처리")
    @CsvSource({"'STORE', 2", "'ITEM', 4", "'ADVERTISEMENT', 2"})
    void exception_handling_when_the_number_of_files_that_do_not_match_the_policy_is_entered_on_local(String filePolicy, int fileCount) {
        //given
        LocalFileProcess localFileProcess = new LocalFileProcess(FilePolicy.valueOf(filePolicy));
        fileService.setFileProcess(localFileProcess);

        //when
        MockMultipartFile[] files = Arrays.copyOf(this.files, fileCount);

        //then
        assertThatThrownBy(() -> fileService.uploadFiles(files))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("There are more files than should be received");
    }

    @ParameterizedTest(name = "{index} -> FilePolicy가 {0}이고 파일개수 {1}개 일때")
    @DisplayName("S3프로세스일때 정책들에 따른 파일저장시 해당 정책에 맞지않은 파일개수가 들어갔을때 예외처리")
    @CsvSource({"'STORE', 2", "'ITEM', 4", "'ADVERTISEMENT', 2"})
    void exception_handling_when_the_number_of_files_that_do_not_match_the_policy_is_entered_on_s3(String filePolicy, int fileCount) {
        //given
        S3FileProcess s3FileProcess = new S3FileProcess(FilePolicy.valueOf(filePolicy));
        fileService.setFileProcess(s3FileProcess);

        //when
        MockMultipartFile[] files = Arrays.copyOf(this.files, fileCount);

        //then
        assertThatThrownBy(() -> fileService.uploadFiles(files))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("There are more files than should be received");
    }

    /**
     * Local,S3 process일때 파일삭제 예외처리
     */
    @ParameterizedTest(name = "{index} -> FilePolicy가 {0}일때")
    @DisplayName("정책에따라 파일이름에 null이 로컬에 입력 된 경우 예외 처리")
    @EnumSource(FilePolicy.class)
    void exception_handling_when_null_is_entered_local_in_the_file_name_according_to_policy(FilePolicy filePolicy) {
        //given
        String fileNames = null;

        //when
        LocalFileProcess localFileProcess = new LocalFileProcess(filePolicy);

        fileService.setFileProcess(localFileProcess);

        //then
        assertThatThrownBy(() -> fileService.canDeleteFiles(fileNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Entered the wrong path");

    }

    @ParameterizedTest(name = "{index} -> FilePolicy가 {0}일때")
    @DisplayName("정책에따라 파일이름에 null이 s3에 입력 된 경우 예외 처리")
    @EnumSource(FilePolicy.class)
    void exception_handling_when_null_is_entered_s3_in_the_file_name_according_to_policy(FilePolicy filePolicy) {
        //given
        String fileNames = null;

        //when
        S3FileProcess s3FileProcess = new S3FileProcess(filePolicy);

        fileService.setFileProcess(s3FileProcess);

        //then
        assertThatThrownBy(() -> fileService.canDeleteFiles(fileNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Entered the wrong path");

    }

    @ParameterizedTest(name = "{index} -> FilePolicy가 {0}일때")
    @DisplayName("정책에따라 파일이름에 공백이 로컬에 입력 된 경우 예외 처리")
    @EnumSource(FilePolicy.class)
    void exception_handling_when_spaces_is_entered_local_in_the_file_name_according_to_policy(FilePolicy filePolicy) {
        //given
        String fileNames = "";

        //when
        LocalFileProcess localFileProcess = new LocalFileProcess(filePolicy);

        fileService.setFileProcess(localFileProcess);

        //then
        assertThatThrownBy(() -> fileService.canDeleteFiles(fileNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Entered the wrong path");

    }

    @ParameterizedTest(name = "{index} -> FilePolicy가 {0}일때")
    @DisplayName("정책에따라 파일이름에 공백이 s3에 입력 된 경우 예외 처리")
    @EnumSource(FilePolicy.class)
    void exception_handling_when_spaces_is_entered_s3_in_the_file_name_according_to_policy(FilePolicy filePolicy) {
        //given
        String fileNames = null;

        //when
        S3FileProcess s3FileProcess = new S3FileProcess(filePolicy);

        fileService.setFileProcess(s3FileProcess);

        //then
        assertThatThrownBy(() -> fileService.canDeleteFiles(fileNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Entered the wrong path");

    }
}