package com.nearnstyle.apis.common.security;

public class UserNotFoundException extends RuntimeException {

    private final String oAuth2ErrorCode;

    public UserNotFoundException(String oAuth2ErrorCode, String msg, Throwable t) {
        super(msg, t);
        this.oAuth2ErrorCode = oAuth2ErrorCode;
    }

    public UserNotFoundException(String oAuth2ErrorCode, String msg) {
        super(msg);
        this.oAuth2ErrorCode = oAuth2ErrorCode;
    }

    public int getHttpErrorCode() {
        return 404;
    }

    public String getOAuth2ErrorCode() {
        return oAuth2ErrorCode;
    }
}

