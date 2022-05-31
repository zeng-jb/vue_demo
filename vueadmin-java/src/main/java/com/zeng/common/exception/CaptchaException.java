package com.zeng.common.exception;


import org.springframework.security.core.AuthenticationException;

public class CaptchaException extends AuthenticationException {

    public CaptchaException(String detail) {
        super(detail);
    }
}
