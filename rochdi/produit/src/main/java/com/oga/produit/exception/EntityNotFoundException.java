package com.oga.produit.exception;

import lombok.Getter;

public class EntityNotFoundException extends RuntimeException{

    @Getter
    private  ErrorCode errorCode;

    public EntityNotFoundException(String messgae){
        super(messgae);
    }

    public EntityNotFoundException(String messgae, Throwable cause){
        super(messgae,cause);
    }

    public EntityNotFoundException(String messgae, Throwable cause,ErrorCode errorCode){
        super(messgae,cause);
        this.errorCode=errorCode;
    }

    public EntityNotFoundException(String messgae,ErrorCode errorCode){
        super(messgae);
        this.errorCode=errorCode;
    }
}
