package com.example.security.user.item.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ItemEntity {
    
    private Long itemIdx;
    private String itemName;
    private LocalDateTime itemRegistTime;

}
