package com.healthcare.healthcare.controller;

import com.healthcare.healthcare.dto.PatientRequestDTO;
import com.healthcare.healthcare.dto.PatientResponseDTO;
import com.healthcare.healthcare.service.PatientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    public ResponseEntity<List<PatientResponseDTO>> listOfPatients(){
        return ResponseEntity.ok(patientService.listOfPatients());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@Valid @PathVariable Long id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

}
