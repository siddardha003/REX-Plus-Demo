package com.rex.boilerplate.springboot.exceptions;

import com.rex.boilerplate.springboot.controller.BeneficiaryOrgViewController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice(basePackageClasses = BeneficiaryOrgViewController.class)
public class BeneficiaryOrgViewControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ApiExceptionResponse> handleNotFoundException(NotFoundException exception) {
        final ApiExceptionResponse response = new ApiExceptionResponse(
                exception.getMessage(),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiExceptionResponse> handleGeneralException(Exception exception) {
        final ApiExceptionResponse response = new ApiExceptionResponse(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                LocalDateTime.now()
        );

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
