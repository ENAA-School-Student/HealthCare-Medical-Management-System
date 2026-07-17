package com.healthcare.healthcare.controller;

import com.healthcare.healthcare.dto.AppointmentRequestDTO;
import com.healthcare.healthcare.dto.AppointmentResponseDTO;
import com.healthcare.healthcare.entity.AppointmentStatus;
import com.healthcare.healthcare.entity.User;
import com.healthcare.healthcare.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private  final AppointmentService appointmentService;

    private Long getAuthenticatedUserId() {
        org.springframework.security.core.@Nullable Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getId();
    }

    private String getAuthenticatedUserRole() {
        @Nullable Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getRole().name();
    }

    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
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

    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@Valid @PathVariable Long id , @RequestBody AppointmentRequestDTO dto){
        Long authUserId = getAuthenticatedUserId();
        String authUserRole = getAuthenticatedUserRole();

        if(authUserRole.equals("ADMIN")){
            return ResponseEntity.ok(appointmentService.updateAppointment(id,dto));

        }

        if(authUserRole.equals("PATIENT")){
            if(!authUserId.equals(id)){
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body((AppointmentResponseDTO) Map.of("message","Enter your correct Id"));
            }
            return ResponseEntity.ok(appointmentService.updateAppointment(id,dto));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body((AppointmentResponseDTO) Map.of("message","Enter your correct Id"));
    }

    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
    @GetMapping("/searchp/patient/{patientId}")
    public ResponseEntity<List<AppointmentResponseDTO>> findAppointmentByPatientId(@PathVariable Long patientId) {

        Long authUserId = getAuthenticatedUserId();
        String authUserRole = getAuthenticatedUserRole();

        if(authUserRole.equals("ADMIN")){
            return ResponseEntity.ok(appointmentService.findAppointmentByPatientId(patientId));
        }

        if(authUserRole.equals("PATIENT")){
            if(!authUserId.equals(patientId)){
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body((List<AppointmentResponseDTO>) Map.of("message","Enter your correct Id"));

            }
            return ResponseEntity.ok(appointmentService.findAppointmentByPatientId(patientId));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body((List<AppointmentResponseDTO>) Map.of("message","Enter your correct Id"));

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

    @GetMapping("/count/today")
    public ResponseEntity<Long> countAppointmentsToday() {
        return ResponseEntity.ok(appointmentService.countAppointmentsToday());
    }

    @GetMapping("/recent")
    public ResponseEntity<List<AppointmentResponseDTO>> getTop4RecentAppointments() {
        return ResponseEntity.ok(appointmentService.getTop4RecentAppointments());
    }

    @GetMapping("/week")
    public ResponseEntity<List<Map<String, Object>>> getAppointmentsThisWeek() {
        return ResponseEntity.ok(appointmentService.getAppointmentsThisWeek());
    }
}
