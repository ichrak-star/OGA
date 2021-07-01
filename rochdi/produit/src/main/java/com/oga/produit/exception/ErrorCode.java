package com.oga.produit.exception;

public enum ErrorCode {


    PRODUIT_NOT_FOUND(1000),
    PRODUIT_NOT_VALID(1001),
    CATEGORY_NOT_FOUND(2000),
    CATEGORY_NOT_VALID(2001);


    private int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
