package com.zero.websample.controller;

import com.zero.websample.exception.ErrorCode;
import com.zero.websample.exception.WebSampleException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zero.websample.dto.ErrorResponse;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestController
public class SampleController {

    @GetMapping("/order/{orderId}")
    public String getOrder(@PathVariable String orderId) throws SQLIntegrityConstraintViolationException {
        log.info("Get some Order : {}", orderId);

        if ("500".equals(orderId)) {
            throw new WebSampleException(ErrorCode.TOO_BIG_ID_ERROR,"Order Id is too big.");
        }

        if("3".equals(orderId)){
            throw new WebSampleException(ErrorCode.TOO_SMALL_ID_ERROR,"Order Id is too small.");
        }

        if("4".equals(orderId)){
            throw new SQLIntegrityConstraintViolationException("Duplicated Insertion was tried.");
        }

        return "orderId : " + orderId + ", orderAmount:1000";
    }

    @GetMapping("/order")
    public String postOrder(
            @RequestParam String orderId,
            @RequestParam String orderAmount) {
        log.info("Get some Order Id : {}", orderId);
        log.info("Get some Order Amount : {}", orderAmount);
        return "orderId : " + orderId + ", orderAmount:" + orderAmount;
    }

    @PostMapping("/order")
    public String postOrder2(
            @RequestBody CreateOrderRequest createOrderRequest,
            @RequestHeader String userAccountId) {
        log.info("Post some Order Id : {}", createOrderRequest.getOrderId());
        log.info("Post some Order Amount : {}", createOrderRequest.getOrderAmount());
        log.info("Post some User Account Id : {}", userAccountId);

        return "User Account Id : " + userAccountId + ", orderId : " + createOrderRequest.getOrderId() + ", orderAmount:" + createOrderRequest.getOrderAmount();
    }

    @Data
    public static class CreateOrderRequest {
        private String orderId;
        private Integer orderAmount;
    }


    // Response를 통해 반환하는 이유 : Serialize를 통해 JSON으로 변환해서 반환
    // Serialize : 객체를 JSON으로 변환
    // Deserialize : JSON을 객체로 변환
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalAccessException : {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(ErrorCode.TOO_BIG_ID_ERROR, e.getMessage()));
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(WebSampleException.class)
    public ResponseEntity<ErrorResponse> handleWebSampleException(WebSampleException e) {
        log.error("IllegalAccessException : {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
    }
}
