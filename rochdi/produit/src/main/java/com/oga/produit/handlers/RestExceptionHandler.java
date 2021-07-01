package com.oga.produit.handlers;


import com.oga.produit.exception.EntityNotFoundException;
import com.oga.produit.exception.InvalideEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorModel> handleException (EntityNotFoundException entityNotFoundException, WebRequest webRequest){

        final HttpStatus notFound = HttpStatus.NOT_FOUND;

       final var errorModel= ErrorModel.builder()
                .errorCode(entityNotFoundException.getErrorCode())
                .httpCode(notFound.value())
                .message(entityNotFoundException.getMessage())
                .build();
         return new ResponseEntity<>(errorModel,notFound);
    }

    @ExceptionHandler(InvalideEntityException.class)
    public ResponseEntity<ErrorModel> handleException(InvalideEntityException invalideEntityException , WebRequest webRequest){
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        final var errorModel= ErrorModel.builder()
                .errorCode(invalideEntityException.getErrorCode())
                .httpCode(badRequest.value())
                .message(invalideEntityException.getMessage())
                .errors(invalideEntityException.getErrors())
                .build();
        return new ResponseEntity<>(errorModel,badRequest);
    }
}
