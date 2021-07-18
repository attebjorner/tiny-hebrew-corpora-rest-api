package org.gosha.kalosha.controller;

import org.gosha.kalosha.exception_handing.NoSentencesFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Map;

@ControllerAdvice
public class ExceptionController
{
    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleException(NoSentencesFoundException e)
    {
        return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleUnknownError(NoHandlerFoundException e)
    {
        return new ResponseEntity<>(Map.of("error", "page not found"), HttpStatus.NOT_FOUND);
    }
}

