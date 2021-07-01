package com.oga.produit.handlers;

import com.oga.produit.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorModel {

    private Integer httpCode;
    private ErrorCode errorCode;
    private String message;
    private List<String> errors = new ArrayList<>();
}
