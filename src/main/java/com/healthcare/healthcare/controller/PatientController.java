package com.healthcare.healthcare.controller;

import com.healthcare.healthcare.dto.PatientRequestDTO;
import com.healthcare.healthcare.dto.PatientResponseDTO;
import com.healthcare.healthcare.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDTO>  addPatient(@Valid @RequestBody PatientRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.addPatient(dto));
    }

    @GetMapping
    public ResponseEntity<Page<PatientResponseDTO>> listOfPatients(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "10")int size,
                                                                   @RequestParam(defaultValue = "nom") String sortBy,
                                                                   @RequestParam(defaultValue = "asc") String sortDir){
        return ResponseEntity.ok(patientService.listOfPatients(page, size, sortBy, sortDir));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> findPatientById(@PathVariable Long id){
        return ResponseEntity.ok(patientService.findPatientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@Valid @PathVariable Long id , @RequestBody PatientRequestDTO dto){
        return ResponseEntity.ok(patientService.updatePatient(id,dto));
    }

}
