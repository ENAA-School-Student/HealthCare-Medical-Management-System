package com.healthcare.healthcare.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorResponseDTO implements Serializable {

    private Long id;
    private String nom;
    private String specialite;
    private String email;
    private String telephone;
}
