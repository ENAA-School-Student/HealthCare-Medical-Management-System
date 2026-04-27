package com.healthcare.healthcare.dto;

import com.healthcare.healthcare.entity.AppointmentStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequestDTO {
    @NotBlank(message = "Appointment date is required")
    @Future(message = "date should be at the future")
    private LocalDateTime dateRendezVous;
    @NotBlank(message = "status is required")
    private AppointmentStatus status;
    @NotBlank(message = "patient id is required")
    private Long patientId;
    @NotBlank(message = "doctor id is required")
    private Long doctorId;
}
