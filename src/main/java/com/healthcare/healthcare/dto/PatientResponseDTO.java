package com.healthcare.healthcare.dto;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientResponseDTO implements Serializable {

    private Long id;
    private String nom ;
    private String prenom;
    private String email;
    private String telephone;
    private LocalDate dateNaissance;
}
