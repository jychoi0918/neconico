package com.neconico.neconico.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(annotations = Controller.class)
public class ExControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentException(IllegalArgumentException e) {
        log.error("[IllegalArgException message]= {}", e.getMessage());
        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    public String exception(Exception e) {
        log.error("[exception message]= {}", e.getMessage());
        return "error/404";
    }
}
