package com.neconico.neconico.service.email;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class DefaultEmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    @DisplayName("해당 유저에게 6자리 인증번호 전송")
    void email_send() throws Exception{
        //given
        String emailAddress = "xorals9448@gmail.com";

        //when
        Long emailId = emailService.sendAuthorNumberMail(emailAddress, 6);

        //then
        assertThat(emailId).isNotNull();
    }

}