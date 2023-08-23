package com.nearnstyle.apis.common.exception;
 
public class NearNStyleResponseEntity {
    protected String message;
    protected Object data;
    protected int errorcode;

    public NearNStyleResponseEntity(String message, int errorcode) {
        this.message = message;
        this.errorcode = errorcode;
    }

    public NearNStyleResponseEntity(String message, int errorcode, Object data) {
        this.message = message;
        this.data = data;
        this.errorcode = errorcode;
    }

    NearNStyleResponseEntity(String message) {
        this.message = message;
        this.errorcode = ResponseCode.ERROR.getResponseCode();
    }

    /**
     * Retrieve message details.
     *
     * @return Returns message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Retrieve data.
     *
     * @return Returns data.
     */
    public Object getData() {
        return data;
    }

    /**
     * Retrieves error code.
     *
     * @return Returns error code.
     */
    public int getErrorcode() {
        return errorcode;
    }
}
