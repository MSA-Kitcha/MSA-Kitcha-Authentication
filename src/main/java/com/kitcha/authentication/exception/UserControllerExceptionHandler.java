package com.kitcha.authentication.exception;

import com.kitcha.authentication.controller.UserController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

import static java.util.Collections.singletonMap;

@ControllerAdvice(basePackageClasses = UserController.class)
public class UserControllerExceptionHandler {

    // @Valid로 인한 검증 에러 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(singletonMap("message", e.getMessage()));
    }

    // DuplicateException 처리
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(singletonMap("message", e.getMessage()));
    }

    // 그 외 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(singletonMap("message", e.getMessage()));
    }
}
