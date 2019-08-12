package com.javasample.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Provides access to custom error response.
 *
 * @author Izadi Ali
 * @version 1.0
 * @inheritDoc
 * @since 1.0
 */
public class CustomErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private String status;
    private String error;

    /**
     * Creates a custom error response.
     */
    public CustomErrorResponse() {
    }

    /**
     * Creates a custom error response based on time and error
     *
     * @param timestamp A date containing date and time of an error.
     * @param status    A string containing the status of an error.
     * @param error     A string containing the message of an error.
     * @since 1.0
     */
    public CustomErrorResponse(LocalDateTime timestamp, String status, String error) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
    }

    /**
     * Gets local date and time.
     *
     * @return A LocalDateTime representing the time of an error.
     * @since 1.0
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets local date and time.
     *
     * @param timestamp A date containing date and time of an error.
     * @since 1.0
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets status of an error based on HttpStatus codes.
     *
     * @return A LocalDateTime representing the time of an error.
     * @since 1.0
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status of an error based on HttpStatus codes.
     *
     * @param status A string containing HttpStatus code.
     * @since 1.0
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets error description.
     *
     * @return A string representing description of an error.
     * @since 1.0
     */
    public String getError() {
        return error;
    }

    /**
     * Sets error description.
     *
     * @param error A string containing description of an error.
     * @since 1.0
     */
    public void setError(String error) {
        this.error = error;
    }
}