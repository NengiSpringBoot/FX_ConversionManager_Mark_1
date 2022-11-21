package com.fxconversion.fx_conversionmanager_mark_1.Exceptions;

public class AppRuntimeException extends RuntimeException{

    public AppRuntimeException(String message){super(message);};

    public AppRuntimeException(String message, Throwable cause){
        super(message, cause);
    }
}
