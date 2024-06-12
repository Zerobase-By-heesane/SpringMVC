package com.zero.websample.exception;

import com.zero.websample.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    // Response를 통해 반환하는 이유 : Serialize를 통해 JSON으로 변환해서 반환
    // Serialize : 객체를 JSON으로 변환
    // Deserialize : JSON을 객체로 변환
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalAccessException : {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(ErrorCode.TOO_BIG_ID_ERROR, e.getMessage()));
    }

    @ExceptionHandler(WebSampleException.class)
    public ResponseEntity<ErrorResponse> handleWebSampleException(WebSampleException e) {
        log.error("IllegalAccessException : {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("IllegalAccessException : {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage()));
    }
}
