package com.bilyoner.challenge.advice;


import com.bilyoner.challenge.exception.NotFoundEntityException;
import com.bilyoner.challenge.exception.UnprocessableEntityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
public class MainControllerAdvice {

    @ExceptionHandler(UnprocessableEntityException.class)
    public final ResponseEntity<Exception> handleUnprocsesableEntityException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }


    @ExceptionHandler(NotFoundEntityException.class)
    public final ResponseEntity<Exception> handleNotFoundEntityException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}