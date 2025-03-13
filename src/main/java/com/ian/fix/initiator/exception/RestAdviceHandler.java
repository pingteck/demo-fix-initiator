package com.ian.fix.initiator.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import quickfix.SessionNotFound;

@Slf4j
@RestControllerAdvice
public class RestAdviceHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("Exception: ", e);
        return ResponseEntity.internalServerError()
            .body(e.getMessage());
    }

    @ExceptionHandler(SessionNotFound.class)
    public ResponseEntity<String> handleException(SessionNotFound e) {
        log.error("SessionNotFound: ", e);
        return ResponseEntity.internalServerError()
            .body(e.getMessage());
    }

}
