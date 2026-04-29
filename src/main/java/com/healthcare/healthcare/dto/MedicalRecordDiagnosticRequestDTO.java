package com.healthcare.healthcare.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecordDiagnosticRequestDTO {
    @NotBlank(message = "the diagnostic is required")
    private String diagnostic;
}
