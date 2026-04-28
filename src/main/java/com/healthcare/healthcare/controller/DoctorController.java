package com.healthcare.healthcare.controller;

import com.healthcare.healthcare.dto.DoctorRequestDTO;
import com.healthcare.healthcare.dto.DoctorResponseDTO;
import com.healthcare.healthcare.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorResponseDTO> addDoctor(@Valid @RequestBody DoctorRequestDTO dto){
        return ResponseEntity.ok(doctorService.addDoctor(dto));
    }
}
