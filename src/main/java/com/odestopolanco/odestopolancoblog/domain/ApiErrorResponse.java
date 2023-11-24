package com.odestopolanco.odestopolancoblog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class ApiErrorResponse {
    private String message;
    private HttpStatus httpStatus;
    private  ZonedDateTime zonedDateTime;
}
