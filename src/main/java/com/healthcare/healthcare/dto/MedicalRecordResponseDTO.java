package com.healthcare.healthcare.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecordResponseDTO implements Serializable {

    private Long id ;
    private String diagnostic;
    private String observation;
    private LocalDate dateCreation;
    private Long patientId;
    private String patientprenom;
    private String patientNom;
}
