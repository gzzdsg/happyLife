package com.gzzdsg.happylife.exception;

/**
 * 业务异常
 *
 * @author: damei
 */
public class CustomException extends RuntimeException{

    public static final int CUSTOM_ERR = 10001;

    private int code;

    public CustomException(String message, int code) {
        super(message);
        this.code = code;
    }

    public CustomException(int code) {
        super();
        this.code = code;
    }

    public CustomException(String message) {
        super(message);
        this.code = CUSTOM_ERR;
    }

    public CustomException() {
        super();
        this.code = CUSTOM_ERR;
    }

}
