package com.zero.websample.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class SampleController {

    @GetMapping("/order/{orderId}")
    public String getOrder(@PathVariable String orderId) throws IllegalAccessException {
        log.info("Get some Order : {}", orderId);

        if ("500".equals(orderId)) {
            throw new IllegalAccessException("500 is Invalid Order Id");
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
}
