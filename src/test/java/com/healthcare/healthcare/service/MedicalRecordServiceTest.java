package com.healthcare.healthcare.service;

import com.healthcare.healthcare.dto.MedicalRecordRequestDTO;
import com.healthcare.healthcare.dto.MedicalRecordResponseDTO;
import com.healthcare.healthcare.entity.Patient;
import com.healthcare.healthcare.repository.MedicalRecordRepository;
import com.healthcare.healthcare.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class MedicalRecordServiceTest {
//    @Autowired
//    MedicalRecordService medicalRecordService;
//    @Autowired
//    PatientRepository patientRepository;
//    @Autowired
//    MedicalRecordRepository medicalRecordRepository;
//
//
//    @Test
//    void addMedicalRecord(){
//
//        Patient patient = Patient.builder()
//                .nom("Errachid")
//                .prenom("yoiussef")
//                .email("errahid@gmail.com")
//                .telephone("5234567678")
//                .dateNaissance(LocalDate.of(2000,01,16))
//                .build();
//        patientRepository.save(patient);
//
//        MedicalRecordRequestDTO medicalRecordRequestDTO = MedicalRecordRequestDTO.builder()
//                .dateCreation(LocalDate.now())
//                .diagnostic("null")
//                .observation("null")
//                .patientId(patient.getId())
//                .build();
//
//        MedicalRecordResponseDTO savedMedicalRecord = medicalRecordService.addMedicalRecord(medicalRecordRequestDTO);
//
//        assertNotNull(savedMedicalRecord);
//        assertTrue(medicalRecordRepository.existsByPatientId(patient.getId()));
//
//    }

}