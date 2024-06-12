package com.zero.websample.dto;

import com.zero.websample.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private ErrorCode errorCode;
    private String message;
}
