package com.nearnstyle.apis.common.exception;
 
public enum ResponseCode {
    SUCCESS(0),
    ERROR(1);

    private final int responseCodeObj;

    ResponseCode(int rCode) {
        this.responseCodeObj = rCode;
    }

    /**
     * Retrieves response code.
     *
     * @return Returns response code.
     */
    public int getResponseCode() {
        return this.responseCodeObj;
    }
}
