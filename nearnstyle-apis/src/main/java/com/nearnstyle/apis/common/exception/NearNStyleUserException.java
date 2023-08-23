package com.nearnstyle.apis.common.exception;
 
public class NearNStyleUserException extends RuntimeException {
    NearNStyleResponseEntity agdRes;

    public NearNStyleUserException(String message, Exception exception) {
        super(message, exception);
        this.agdRes = new NearNStyleResponseEntity(message);
    }

    public NearNStyleUserException(String message, int errorCode) {
        super(message);
        this.agdRes = new NearNStyleResponseEntity(message, errorCode);
    }

    public NearNStyleUserException(String message, int errorCode, Object data) {
        super(message);
        this.agdRes = new NearNStyleResponseEntity(message, errorCode, data);
    }

    /**
     * Retrieves imtecho user exception response.
     *
     * @return Returns imtecho user exception response.
     */
    public NearNStyleResponseEntity getResponse() {
        return agdRes;
    }
}
