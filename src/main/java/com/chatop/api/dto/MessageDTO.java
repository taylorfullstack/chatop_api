package com.chatop.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDTO {
    private Long id;
    private String message;
    private Long user_id;
    private Long rental_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
