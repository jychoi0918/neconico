package com.neconico.neconico.aop.email;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class EmailServiceLogHelper {

    @AfterThrowing(pointcut = "execution(* com.neconico.neconico.service.email.EmailService.sendAuthorNumberMail(..)) || "
            + "execution(* com.neconico.neconico.service.email.EmailService.sendAuthorMailTemplate(..))", throwing = "e")
    public void sendEmailLogging(JoinPoint joinPoint, Exception e) {
        log.error("{} -> 이메일 전송실패 : {}", joinPoint.getSignature(), e.getMessage());
    }

}
