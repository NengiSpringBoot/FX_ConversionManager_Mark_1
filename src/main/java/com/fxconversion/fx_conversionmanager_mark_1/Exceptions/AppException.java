package com.fxconversion.fx_conversionmanager_mark_1.Exceptions;

import org.springframework.http.HttpStatus;

public class AppException extends Exception{

    private final HttpStatus httpStatus;

    public AppException(String message, HttpStatus status){
        super(message);
        this.httpStatus=status;
    }

    public AppException(String message){
        super(message);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public AppException(Throwable cause){
        super(cause);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public AppException(String message, Throwable cause, HttpStatus status, HttpStatus httpStatus){
        super(message, cause);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus(){return httpStatus;}


}
