package com.healthcare.healthcare.controller;

import com.healthcare.healthcare.dto.MedicalRecordDiagnosticRequestDTO;
import com.healthcare.healthcare.dto.MedicalRecordObservationRequestDTO;
import com.healthcare.healthcare.dto.MedicalRecordRequestDTO;
import com.healthcare.healthcare.dto.MedicalRecordResponseDTO;
import com.healthcare.healthcare.service.MedicalRecordService;
import com.healthcare.healthcare.service.PdfService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicalrecord")
@RequiredArgsConstructor
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;
    private final PdfService pdfService;


    @PreAuthorize("hasRole('DOCTOR')")
    @PostMapping
    public ResponseEntity<MedicalRecordResponseDTO> addMedicalRecord(@Valid @RequestBody MedicalRecordRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecordService.addMedicalRecord(dto));
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PatchMapping("/diagnostic/{id}")
    public ResponseEntity<MedicalRecordResponseDTO> addDiagnostic(@PathVariable Long id, @Valid @RequestBody MedicalRecordDiagnosticRequestDTO dto) {
        return ResponseEntity.ok(medicalRecordService.addDiagnostic(id, dto));
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PatchMapping("/observation/{id}")
    public ResponseEntity<MedicalRecordResponseDTO> addObservation(@PathVariable Long id, @Valid @RequestBody MedicalRecordObservationRequestDTO dto) {
        return ResponseEntity.ok(medicalRecordService.addObservation(id, dto));
    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'PATIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecordResponseDTO> findMedicalRecordById(@PathVariable Long id){
        return ResponseEntity.ok(medicalRecordService.findMedicalRecordById(id));
    }

    @GetMapping("/patient/{patientId}/download")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isPatientOwner(authentication, #patientId)")
    public ResponseEntity<byte[]> downloadMedicalRecord(@PathVariable Long patientId) {

        byte[] pdfBytes = pdfService.generateMedicalRecordPdf(patientId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment",
                "medical-record-patient-" + patientId + ".pdf");
        headers.setContentLength(pdfBytes.length);

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}
