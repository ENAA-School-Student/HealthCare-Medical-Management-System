package com.healthcare.healthcare.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorResponseDTO {

    private Long id;
    private String nom;
    private String specialite;
    private String email;
    private String telephone;
}
