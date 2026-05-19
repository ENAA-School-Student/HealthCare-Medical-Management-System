package com.healthcare.healthcare.controller;

import com.healthcare.healthcare.dto.AppointmentRequestDTO;
import com.healthcare.healthcare.dto.AppointmentResponseDTO;
import com.healthcare.healthcare.dto.DoctorRequestDTO;
import com.healthcare.healthcare.dto.DoctorResponseDTO;
import com.healthcare.healthcare.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<Page<AppointmentResponseDTO>> listOfAppointments(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10")int size){
        return ResponseEntity.ok(appointmentService.listOfAppointments(page,size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@Valid @PathVariable Long id , @RequestBody AppointmentRequestDTO dto){
        return ResponseEntity.ok(appointmentService.updateAppointment(id,dto));
    }

    @GetMapping("/searchp/patient/{patientId}")
    public ResponseEntity<List<AppointmentResponseDTO>> findAppointmentByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(appointmentService.findAppointmentByPatientId(patientId));
    }

    @GetMapping("/search/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentResponseDTO>> findAppointmentByDoctorId(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.findAppointmentByDoctorId(doctorId));
    }
}
