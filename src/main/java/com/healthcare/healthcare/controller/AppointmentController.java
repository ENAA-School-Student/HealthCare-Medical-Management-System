package com.healthcare.healthcare.controller;

import com.healthcare.healthcare.dto.AppointmentRequestDTO;
import com.healthcare.healthcare.dto.AppointmentResponseDTO;
import com.healthcare.healthcare.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private  final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> addAppointment(@Valid @RequestBody AppointmentRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.addAppointment(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id){
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
