package com.melita.apigateway.config.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerExceptions {
    private static final Logger LOG = LoggerFactory.getLogger(HandlerExceptions.class);

    @ExceptionHandler(AuthenticationServiceException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseEntity<?> handleIllegalAuthenticationServiceException(AuthenticationServiceException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build();
    }

    @ExceptionHandler(Exception.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseEntity<?> handleAllException(Exception ex) {
        return ResponseEntity
                .internalServerError()
                .build();
    }
}
