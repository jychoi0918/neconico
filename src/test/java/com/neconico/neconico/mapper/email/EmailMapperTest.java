package com.neconico.neconico.mapper.email;

import com.neconico.neconico.dto.email.AuthorNumberDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class EmailMapperTest {

    @Autowired
    private EmailMapper emailMapper;


    @BeforeEach
    void insertAuthorNumber() {
        AuthorNumberDto authorNumberDto = new AuthorNumberDto();
        authorNumberDto.setAuthorNumber("7DX2FD");
        authorNumberDto.setCreatedDate(LocalDateTime.now());

        emailMapper.insertAuthorNumber(authorNumberDto);
    }

    @Test
    @DisplayName("인증번호를 DB에 저장")
    void insert_author_number_in_database() {
        List<AuthorNumberDto> authorNumbers = emailMapper.selectAll();

        assertThat(authorNumbers).isNotNull();

    }

    @Test
    @DisplayName("인증번호를 DB에서 삭제")
    void delete_author_number_in_database() {
        //given
        AuthorNumberDto authorNumberDto = new AuthorNumberDto();
        authorNumberDto.setAuthorNumber("D3F45A");
        authorNumberDto.setCreatedDate(LocalDateTime.now());

        emailMapper.insertAuthorNumber(authorNumberDto);

        //when
        emailMapper.deleteAuthorNumber(authorNumberDto.getEmailId());

        List<AuthorNumberDto> authNumbers = emailMapper.selectAll();

        //then
        assertThat(authNumbers)
                .extracting("emailId")
                .doesNotContain(authorNumberDto.getEmailId());
    }

    @Test
    @DisplayName("인증번호가 DB에 저장되어 있는지 확인")
    void check_exist_author_number_in_database() {
        //given
        String authorNumber = "7DX2FD";

        //when
        Long emailId = emailMapper
                .selectByAuthorNumber(authorNumber);

        //then
        assertThat(emailId).isNotNull();
    }
}