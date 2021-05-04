package com.neconico.neconico.aop.users;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class UserServiceLogHelper {

    @Around("execution(* com.neconico.neconico.service.users.UserService.joinUser(..))")
    public Object joinUserLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        log.info("{} -> 사용자 비밀번호 암호화", joinPoint.getSignature());
        return proceed;
    }

    @Around("execution(* com.neconico.neconico.service.users.UserService.changeDropUserAuthority(..))")
    public Object changeUserAuthorityLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        String arg = (String) joinPoint.getArgs()[0];
        Object result = joinPoint.proceed();
        log.info("{} -> 사용자 권한 변경 해당아이디:{} {} -> {}", joinPoint.getSignature(), arg, "ROLE_USER", "ROLE_DROP");
        return result;
    }

    @AfterReturning("execution(* com.neconico.neconico.service.users.UserService.changeUserInfo(..)))")
    public void changeUserInfoLogging(JoinPoint joinPoint) {
        log.info("{} -> 사용자 정보 변경", joinPoint.getSignature());
    }
}
