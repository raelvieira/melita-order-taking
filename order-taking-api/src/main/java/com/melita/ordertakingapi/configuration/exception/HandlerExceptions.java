package com.melita.ordertakingapi.configuration.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@RestControllerAdvice
public class HandlerExceptions extends ResponseEntityExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(HandlerExceptions.class);

    private static final String MDC_KEY = "StackTrace";
    private static final String DATA_RESPONSE_TITLE = "Attention";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        DataResponse dataResponse = new DataResponse(
                DATA_RESPONSE_TITLE,
                "The arguments sent are not valid!"
        );

        MDC.clear();
        MDC.put(MDC_KEY, Arrays.toString(ex.getStackTrace()));
        LOG.error("MethodArgumentNotValidException: {}", ex.getMessage());
        MDC.clear();

        return super.handleExceptionInternal(ex, dataResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        DataResponse dataResponse = new DataResponse(
                DATA_RESPONSE_TITLE,
                "Parameters are invalid!"
        );

        MDC.clear();
        MDC.put(MDC_KEY, Arrays.toString(ex.getStackTrace()));
        LOG.error("HttpMessageNotReadableException: {}", ex.getMessage());
        MDC.clear();

        return super.handleExceptionInternal(ex, dataResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<DataResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        DataResponse dataResponse = new DataResponse(
                DATA_RESPONSE_TITLE,
                "The arguments are not appropriate for the request!"
        );

        MDC.clear();
        MDC.put(MDC_KEY, Arrays.toString(ex.getStackTrace()));
        LOG.error("IllegalArgumentException: {}", ex.getMessage());
        MDC.clear();

        return ResponseEntity
                .badRequest()
                .body(dataResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DataResponse> handleAllException(Exception ex) {
        DataResponse dataResponse = new DataResponse(
                DATA_RESPONSE_TITLE,
                "There was an internal error with your request!"
        );

        MDC.clear();
        MDC.put(MDC_KEY, Arrays.toString(ex.getStackTrace()));
        LOG.error("Exception class is {} message: {}", ex.getClass(), ex.getMessage());
        MDC.clear();

        return ResponseEntity
                .internalServerError()
                .body(dataResponse);
    }
}
