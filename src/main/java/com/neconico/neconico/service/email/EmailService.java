package com.neconico.neconico.service.email;

import com.neconico.neconico.dto.email.AuthorNumberDto;

import java.util.Map;

public interface EmailService {

    Long sendAuthorNumberMail(String emailAddress, int length) throws Exception;

    void sendMailTemplate(Map<String, Object> content, String eventName, String templatePath) throws Exception;

    Long checkExistNumber(String authorNumber);

    void deleteAuthorNumber(java.lang.Long emailId);


}
