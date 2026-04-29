package com.healthcare.healthcare.service;

import com.healthcare.healthcare.dto.MedicalRecordRequestDTO;
import com.healthcare.healthcare.dto.MedicalRecordResponseDTO;
import com.healthcare.healthcare.entity.MedicalRecord;
import com.healthcare.healthcare.entity.Patient;
import com.healthcare.healthcare.mapper.MedicalRecordMapper;
import com.healthcare.healthcare.repository.MedicalRecordRepository;
import com.healthcare.healthcare.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final MedicalRecordMapper medicalRecordMapper;
    private  final PatientRepository patientRepository;

    public MedicalRecordResponseDTO addMedicalRecord(MedicalRecordRequestDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + dto.getPatientId()));

        if (medicalRecordRepository.existsByPatientId(dto.getPatientId())) {
            throw new RuntimeException("Medical record already exists for patient with id: " + dto.getPatientId());
        }

        MedicalRecord medicalRecord = MedicalRecord.builder()
                .diagnostic(dto.getDiagnostic())
                .observation(dto.getObservation())
                .dateCreation(dto.getDateCreation())
                .patient(patient)
                .build();

        return medicalRecordMapper.toDto(medicalRecordRepository.save(medicalRecord));
    }
}
