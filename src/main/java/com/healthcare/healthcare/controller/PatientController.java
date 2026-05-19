package com.healthcare.healthcare.controller;

import com.healthcare.healthcare.dto.PatientRequestDTO;
import com.healthcare.healthcare.dto.PatientResponseDTO;
import com.healthcare.healthcare.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PatientResponseDTO>  addPatient(@Valid @RequestBody PatientRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.addPatient(dto));
    }

    @PreAuthorize("hasAnyRole('PATIENT', 'DOCTOR', 'ADMIN')")
    @GetMapping
    public ResponseEntity<Page<PatientResponseDTO>> listOfPatients(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "10")int size,
                                                                   @RequestParam(defaultValue = "nom") String sortBy,
                                                                   @RequestParam(defaultValue = "asc") String sortDir){
        return ResponseEntity.ok(patientService.listOfPatients(page, size, sortBy, sortDir));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> findPatientById(@PathVariable Long id){
        return ResponseEntity.ok(patientService.findPatientById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@Valid @PathVariable Long id , @RequestBody PatientRequestDTO dto){
        return ResponseEntity.ok(patientService.updatePatient(id,dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<Page<PatientResponseDTO>> searchPatients(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(
                patientService.searchPatientsByName(name, page, size));
    }

}
