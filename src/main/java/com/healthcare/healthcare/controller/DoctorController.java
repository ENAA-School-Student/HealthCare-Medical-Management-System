package com.healthcare.healthcare.controller;

import com.healthcare.healthcare.dto.DoctorRequestDTO;
import com.healthcare.healthcare.dto.DoctorResponseDTO;
import com.healthcare.healthcare.dto.PatientRequestDTO;
import com.healthcare.healthcare.dto.PatientResponseDTO;
import com.healthcare.healthcare.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorResponseDTO> addDoctor(@Valid @RequestBody DoctorRequestDTO dto){
        return ResponseEntity.ok(doctorService.addDoctor(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<DoctorResponseDTO>> listOfDoctors(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "10")int size,
                                                                 @RequestParam(defaultValue = "nom") String sortBy,
                                                                 @RequestParam(defaultValue = "asc") String sortDir){
        return ResponseEntity.ok(doctorService.listOfDoctors(page,size, sortBy, sortDir));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(@Valid @PathVariable Long id , @RequestBody DoctorRequestDTO dto){
        return ResponseEntity.ok(doctorService.updateDoctor(id,dto));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<DoctorResponseDTO>> searchDoctorBySpecialite(
            @RequestParam String specialite,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(
                doctorService.searchDoctorBySpecialite(specialite, page, size));
    }
}
