package com.guerlak.challenge01.usersapi.resources.exceptions;

import com.guerlak.challenge01.usersapi.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(NotFoundException e, HttpServletRequest req){

        StandardError err = new StandardError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Não encontrado",
                e.getMessage(),
                req.getContextPath()
        );
        return ResponseEntity.status(err.getCode()).body(err);
    }

}
