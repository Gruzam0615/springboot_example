package com.gruzam0615.jpaboard.util;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomResponseEntity {

    String message;
    Object data;
    LocalDateTime timeStamp;

    public CustomResponseEntity(String message, LocalDateTime timeStamp) {
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public CustomResponseEntity(String message, Object data, LocalDateTime timeStamp) {
        this.message = message;
        this.data = data;
        this.timeStamp = timeStamp;
    }
    
}