package com.neconico.neconico.aop.item;

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
public class ItemServiceLogHelper {
    @AfterReturning("execution(* com.neconico.neconico.service.item.ItemService.changeItemInfo(..))")
    public void changeItemLogging(JoinPoint joinPoint) {
        log.info("{} -> 아이템 내용 수정", joinPoint.getSignature());
    }

    @Around("execution(* com.neconico.neconico.service.item.ItemService.removeItem(..))")
    public Object removeItemLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Long arg = (Long) args[0];

        log.info("{} -> 상품 pk: {} 상품 제거 ", joinPoint.getSignature(), arg);
        Object result = joinPoint.proceed();
        log.info("{} -> 상품 pk: {} 제거완료", joinPoint.getSignature(), arg);
        return result;
    }
}
