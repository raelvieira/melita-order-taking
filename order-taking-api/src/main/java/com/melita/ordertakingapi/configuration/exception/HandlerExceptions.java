package com.melita.ordertakingapi.configuration.exception;

import com.melita.ordertakingapi.configuration.exception.response.Error;
import com.melita.ordertakingapi.configuration.exception.response.Response;
import jakarta.ws.rs.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Locale;

@RestControllerAdvice
public class HandlerExceptions extends ResponseEntityExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(HandlerExceptions.class);

    private final MessageSource messageSource;

    public HandlerExceptions(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        LOG.error("Error {}: {}", ex.getClass(), ex.getMessage());

        final Response<Error> resp = new Response<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        fieldErrors
            .forEach(fieldError -> resp.add(new Error(fieldError.getDefaultMessage(), ex.getLocalizedMessage())));

        return super.handleExceptionInternal(
            ex,
            resp,
            headers,
            HttpStatus.BAD_REQUEST,
            request
        );
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {

        LOG.error("Error {}: {}", ex.getClass(), ex.getMessage());

        final Response<Error> resp = new Response<>();

        final String message = messageSource.getMessage(
            "parameter.invalid",
            null,
            new Locale("en", "UK")
        );

        resp.add(new Error(message, ex.getLocalizedMessage()));

        return super.handleExceptionInternal(
            ex,
            resp,
            headers,
            HttpStatus.BAD_REQUEST,
            request
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<Error> handleBadRequestException(
        BadRequestException ex,
        WebRequest request
    ) {
        LOG.error("Error {}: {}", ex.getClass(), ex.getMessage());

        Error errorResponse = new Error(
            ex.getMessage(),
            request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Error> handleNotFoundException(
        NotFoundException ex,
        WebRequest request
    ) {
        LOG.error("Error {}: {}", ex.getClass(), ex.getMessage());

        Error errorResponse = new Error(
            ex.getMessage(),
            request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Error> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        LOG.error("Error {}: {}", ex.getClass(), ex.getMessage());

        Error errorResponse = new Error(
            ex.getMessage(),
            request.getDescription(false)
        );

        return ResponseEntity
            .badRequest()
            .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleAllException(Exception ex, WebRequest request) {
        LOG.error("Error {}: {}", ex.getClass(), ex.getMessage());

        Error errorResponse = new Error(
            ex.getMessage(),
            request.getDescription(false)
        );

        return ResponseEntity
            .internalServerError()
            .body(errorResponse);
    }
}
