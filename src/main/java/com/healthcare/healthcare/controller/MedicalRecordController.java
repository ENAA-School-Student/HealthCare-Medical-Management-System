package com.healthcare.healthcare.controller;

import com.healthcare.healthcare.dto.MedicalRecordRequestDTO;
import com.healthcare.healthcare.dto.MedicalRecordResponseDTO;
import com.healthcare.healthcare.service.MedicalRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/MedicalRecord")
@RequiredArgsConstructor
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;


    @PostMapping
    public ResponseEntity<MedicalRecordResponseDTO> addMedicalRecord(@Valid @RequestBody MedicalRecordRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecordService.addMedicalRecord(dto));
    }


}
