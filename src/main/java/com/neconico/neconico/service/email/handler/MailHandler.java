package com.neconico.neconico.service.email.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor
public class MailHandler {
    private final JavaMailSender sender;
    private MimeMessage message;
    private MimeMessageHelper messageHelper;

    @PostConstruct
    public void setMimeMessageAndHelper() throws MessagingException {
        message = sender.createMimeMessage();
        messageHelper = new MimeMessageHelper(message, true,"UTF-8");
    }

    //보내는 사람 이메일
    public void setForm(String formAddress) throws MessagingException {
        messageHelper.setFrom(formAddress);
    }

    //받는 사람 이메일
    public void setTo(String emailAddress) throws MessagingException {
        messageHelper.setTo(emailAddress);
    }

    // 제목
    public void setSubject(String subject) throws MessagingException {
        messageHelper.setSubject(subject);
    }

    // 내용
    public void setText(String text, boolean useHtml) throws MessagingException {
        messageHelper.setText(text, useHtml);
    }

    // 이미지 삽입
    public void setInline(String contentId, String pathToInline) throws MessagingException {
        Resource resource = new ClassPathResource(pathToInline);

        messageHelper.addInline(contentId, resource);
    }

    public void send() throws MailException {
            sender.send(message);
    }
}
