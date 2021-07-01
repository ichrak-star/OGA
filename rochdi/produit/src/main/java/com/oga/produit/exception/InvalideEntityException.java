package com.oga.produit.exception;

import lombok.Getter;

import java.util.List;

public class InvalideEntityException extends RuntimeException{

    @Getter
    private  ErrorCode errorCode;
    @Getter
    private  List<String> errors;

    public InvalideEntityException(String messgae){
        super(messgae);
    }

    public InvalideEntityException(String messgae, Throwable cause){
        super(messgae,cause);
    }

    public InvalideEntityException(String messgae, Throwable cause,ErrorCode errorCode){
        super(messgae,cause);
        this.errorCode=errorCode;
    }

    public InvalideEntityException(String messgae,ErrorCode errorCode){
        super(messgae);
        this.errorCode=errorCode;
    }

    public InvalideEntityException(String messgae,ErrorCode errorCode,List<String>errors){
        super(messgae);
        this.errorCode=errorCode;
        this.errors=errors;
    }

}
