package com.chatapp.backend.exception;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private int status;

    @NonNull
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ApiError(@NonNull Integer status, @NonNull String message) {
        this.status = status;
        this.message = message;
    }

}

