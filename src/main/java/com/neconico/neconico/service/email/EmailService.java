package com.neconico.neconico.service.email;

public interface EmailService {

    @Deprecated
    Long sendAuthorNumberMail(String emailAddress, int length) throws Exception;

    Long sendAuthorMailTemplate(String emailAddress, String templatePath, int length) throws Exception;

    Long checkExistNumber(String authorNumber);

    void deleteAuthorNumber(java.lang.Long emailId);


}
