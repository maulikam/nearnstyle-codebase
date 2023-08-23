package com.nearnstyle.apis.common.security;

import org.springframework.security.oauth2.common.exceptions.ClientAuthenticationException;

 
public class AuthenticationException extends ClientAuthenticationException {

    private final String oAuth2ErrorCode;

    public AuthenticationException(String oAuth2ErrorCode, String msg, Throwable t) {
        super(msg, t);
        this.oAuth2ErrorCode = oAuth2ErrorCode;
    }

    public AuthenticationException(String oAuth2ErrorCode, String msg) {
        super(msg);
        this.oAuth2ErrorCode = oAuth2ErrorCode;
    }

    @Override
    public int getHttpErrorCode() {
        return 401;
    }

    @Override
    public String getOAuth2ErrorCode() {
        return oAuth2ErrorCode;
    }

}
