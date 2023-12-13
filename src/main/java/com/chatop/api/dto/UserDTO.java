package com.chatop.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
