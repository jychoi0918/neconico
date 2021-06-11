package com.neconico.neconico.service.email.template;

import lombok.Getter;

@Getter
public enum EmailTemplate {
    DEFAULT("email/email_form.html");

    private String templatePath;

    EmailTemplate(String templatePath) {
        this.templatePath = templatePath;
    }
}
