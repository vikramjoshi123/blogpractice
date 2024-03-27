package com.practice.practiceProject.utils.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static <T> ResponseEntity<Object> response(String message, T responseObj, HttpStatus httpStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("data", responseObj);
        map.put("status", httpStatus.value());
        return new ResponseEntity<Object>(map, httpStatus);
    }
}