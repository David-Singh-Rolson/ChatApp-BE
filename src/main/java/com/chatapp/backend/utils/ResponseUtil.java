package com.chatapp.backend.utils;

import com.chatapp.backend.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static <T> ResponseEntity<ApiResponse<T>> success(
            T data, String message, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(new ApiResponse<>(true, status.value(), message, data));
    }

    public static ResponseEntity<ApiResponse<Void>> error(
            String message, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(new ApiResponse<>(false, status.value(), message, null));
    }
}

