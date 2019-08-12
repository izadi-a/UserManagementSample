package com.javasample.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Provides access to custom response entity exception handler.
 *
 * @author Izadi Ali
 * @version 1.0
 * @inheritDoc
 * @since 1.0
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Provides handling for standard Spring MVC exceptions.
     *
     * @param ex      the target exception
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     * @since 1.0
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity(customErrorResponse, INTERNAL_SERVER_ERROR);
    }

    /**
     * Provides handling for standard Spring MVC runtime exceptions.
     *
     * @param e the target runtimeException
     * @return a {@code ResponseEntity} instance
     * @since 1.0
     */
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
        return error(INTERNAL_SERVER_ERROR, e);
    }

    /**
     * Provides handling for UserNotFoundException.
     *
     * @param ex      the target exception
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     * @since 1.0
     */
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<CustomErrorResponse> customHandleNotFound(Exception ex, WebRequest request) {
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setStatus(String.valueOf(HttpStatus.NOT_FOUND.value()));

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    /**
     * Provides ResponseEntity object.
     *
     * @param e      the target exception
     * @param status the current Http status
     * @return a {@code ResponseEntity} instance
     * @since 1.0
     */
    private ResponseEntity<String> error(HttpStatus status, Exception e) {
        return ResponseEntity.status(status).body(e.getMessage());
    }

    /**
     * or Validating Path Variables and Request Parameters.
     *
     * @param response the current Http servlet response
     * @since 1.0
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all fields errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }
}