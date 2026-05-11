package com.healthcare.healthcare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDTO {
    @Email(message = "invalid email format")
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "email is required")
    @Size(min = 8,max = 16 ,message = "password  and must be between 8 and 16 characters")
    private String password;
}