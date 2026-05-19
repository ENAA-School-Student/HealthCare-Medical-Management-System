package com.healthcare.healthcare.controller;

import com.healthcare.healthcare.dto.AppointmentRequestDTO;
import com.healthcare.healthcare.dto.AppointmentResponseDTO;
import com.healthcare.healthcare.entity.AppointmentStatus;
import com.healthcare.healthcare.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private  final AppointmentService appointmentService;

    @PreAuthorize("hasRole('PATIENT')")
    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> addAppointment(@Valid @RequestBody AppointmentRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.addAppointment(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id){
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'PATIENT')")
    @GetMapping
    public ResponseEntity<Page<AppointmentResponseDTO>> listOfAppointments(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10")int size,
                                                                           @RequestParam(defaultValue = "nom") String sortBy,
                                                                           @RequestParam(defaultValue = "asc") String sortDir){
        return ResponseEntity.ok(appointmentService.listOfAppointments(page,size, sortBy, sortDir));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@Valid @PathVariable Long id , @RequestBody AppointmentRequestDTO dto){
        return ResponseEntity.ok(appointmentService.updateAppointment(id,dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/searchp/patient/{patientId}")
    public ResponseEntity<List<AppointmentResponseDTO>> findAppointmentByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(appointmentService.findAppointmentByPatientId(patientId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentResponseDTO>> findAppointmentByDoctorId(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.findAppointmentByDoctorId(doctorId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<Page<AppointmentResponseDTO>> searchAppointmentByStatus(
            @RequestParam AppointmentStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(
                appointmentService.findAppointmentByStatus(status, page, size));
    }
}
