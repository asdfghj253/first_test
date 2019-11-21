package com.myperson.config;

import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;

/**
 * Created by Administrator on 2019/4/18 0018.
 */
@ControllerAdvice
public class CtrlExceptionHandler {
    private static Logger logger= LoggerFactory.getLogger(CtrlExceptionHandler.class);

    @ResponseStatus(value= HttpStatus.FORBIDDEN)
    @ExceptionHandler(UnauthenticatedException.class)
    public String handleException (UnauthenticatedException e){
        return "403";
    }

    @ResponseStatus(value= HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthenticationException.class)
    public String handleException2 (AuthenticationException e){
        return "403";
    }
}
