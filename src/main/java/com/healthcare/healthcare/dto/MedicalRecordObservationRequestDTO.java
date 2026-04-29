package com.healthcare.healthcare.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecordObservationRequestDTO {
    @NotBlank(message = "the observation is required")
    private String observation;
}
