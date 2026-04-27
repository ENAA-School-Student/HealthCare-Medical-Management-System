package com.healthcare.healthcare.dto;

import com.healthcare.healthcare.entity.AppointmentStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentResponseDTO {
    private Long id;
    private LocalDateTime dateRendezVous;
    private AppointmentStatus status;
    private Long patientId;
    private String patientNom;
    private String patientPrenom;
    private Long doctorId;
    private String doctorNom;
    private String doctorSpecialite;
}
