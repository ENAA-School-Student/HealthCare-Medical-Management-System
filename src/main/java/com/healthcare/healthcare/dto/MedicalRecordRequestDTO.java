package com.healthcare.healthcare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecordRequestDTO {

    @NotBlank(message = "the diagnostic is required")
    private String diagnostic;
    @NotBlank(message = "the observation is required")
    private String observation;
    @NotNull(message = "date of the creation is required")
    private LocalDate dateCreation;
    @NotNull(message = "the id of the patient is required")
    private Long patientId;

}
