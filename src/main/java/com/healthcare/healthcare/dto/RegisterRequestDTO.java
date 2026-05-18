package com.healthcare.healthcare.dto;

import com.healthcare.healthcare.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDTO {
    @NotBlank(message = "username is required")
    @Size(min = 5,max = 20 ,message = "username and must be between 5 and 20 characters")
    private String username;
    @NotBlank(message = "email is required")
    @Email(message = "invalid email format")
    private String email;
    @NotBlank(message = "password is required")
    @Size(min = 8,max = 16 ,message = "password  and must be between 8 and 16 characters")
    private String password;
    @NotNull(message = "Role is required")
    private Role role;
}