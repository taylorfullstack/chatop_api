package com.chatop.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RentalRequestDTO {

    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Surface cannot be null")
    @Positive(message = "Surface must be greater than 0")
    private Integer surface;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be greater than 0")
    private Integer price;

    private MultipartFile picture;

    @Size(min = 10, message = "Description must be at least 10 characters")
    private String description;

    private Long owner_id;

}