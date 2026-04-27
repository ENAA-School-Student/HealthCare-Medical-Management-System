package com.healthcare.healthcare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientRequestDTO {

    @NotBlank(message = "last name required")
    private String nom;

    @NotBlank(message = "first name required")
    private String prenom;

    @NotBlank(message = "email required")
    @Email(message = "invalid email")
    private String email;

    @NotBlank(message = "phonenumber required")
    @Pattern(regexp = "^[0-9]{10}$", message = "invalid phone number")
    private String telephone;

    @Past(message = "the date should be in the past")
    private LocalDate dateNaissance;
}
