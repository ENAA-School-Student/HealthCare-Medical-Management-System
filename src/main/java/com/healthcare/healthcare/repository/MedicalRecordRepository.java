package com.healthcare.healthcare.repository;

import com.healthcare.healthcare.entity.MedicalRecord;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    boolean existsByPatientId(Long patientId);
}
