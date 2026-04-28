package com.healthcare.healthcare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorRequestDTO {
  @NotBlank(message = "the name is required ")
    private String nom;

  @NotBlank(message = "specialite is required")
    private String specialite;


    @NotBlank(message = "email required")
    @Email(message = "invalid email")
    private String email;

    @NotBlank(message = "phonenumber required")
    @Pattern(regexp = "^[0-9]{10}$", message = "invalid phone number")
    private String telephone;

}
