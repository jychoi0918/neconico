package com.neconico.neconico.service.email;

import com.neconico.neconico.service.email.template.EmailTemplate;

public interface EmailService {

    @Deprecated
    Long sendAuthorNumberMail(String emailAddress, int length) throws Exception;

    Long sendAuthorMailTemplate(String emailAddress, EmailTemplate emailTemplate, int length) throws Exception;

    Long checkExistNumber(String authorNumber);

    void deleteAuthorNumber(java.lang.Long emailId);


}
