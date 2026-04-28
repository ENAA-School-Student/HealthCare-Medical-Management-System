package com.healthcare.healthcare.repository;

import com.healthcare.healthcare.entity.Patient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    boolean existsPatientById(String email);

    boolean existsPatientByEmail(String email);
}
