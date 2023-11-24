package com.odestopolanco.odestopolancoblog.exceptions;

import com.odestopolanco.odestopolancoblog.domain.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

    private static final String EMAIL_UNIQUE_CONSTRAINT = "Detail: Key (email_unique_constraint)";
    private static final String USERNAME_UNIQUE_CONSTRAINT = "Detail: Key (username_unique_constraint)";


    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse("", HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
        apiErrorResponse.setMessage(e.getMessage());

        if (e.getMessage().contains(EMAIL_UNIQUE_CONSTRAINT)) {
            apiErrorResponse.setMessage("The email is already taken");
        } else if (e.getMessage().contains(USERNAME_UNIQUE_CONSTRAINT)) {
            apiErrorResponse.setMessage("The username is already taken");
        }
        log.error(e.getMessage());

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
