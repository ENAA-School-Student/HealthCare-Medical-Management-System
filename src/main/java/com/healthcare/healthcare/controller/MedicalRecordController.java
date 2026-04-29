package com.healthcare.healthcare.controller;

import com.healthcare.healthcare.dto.MedicalRecordDiagnosticRequestDTO;
import com.healthcare.healthcare.dto.MedicalRecordRequestDTO;
import com.healthcare.healthcare.dto.MedicalRecordResponseDTO;
import com.healthcare.healthcare.service.MedicalRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/MedicalRecord")
@RequiredArgsConstructor
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;


    @PostMapping
    public ResponseEntity<MedicalRecordResponseDTO> addMedicalRecord(@Valid @RequestBody MedicalRecordRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecordService.addMedicalRecord(dto));
    }

    @PatchMapping("/{id}/diagnostic")
    public ResponseEntity<MedicalRecordResponseDTO> addDiagnostic(@PathVariable Long id, @Valid @RequestBody MedicalRecordDiagnosticRequestDTO dto) {
        return ResponseEntity.ok(medicalRecordService.addDiagnostic(id, dto));
    }

}
