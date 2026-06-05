package com.healthcare.healthcare.repository;

import com.healthcare.healthcare.dto.PatientResponseDTO;
import com.healthcare.healthcare.entity.Patient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    boolean existsPatientById(String email);

    boolean existsPatientByEmail(String email);

    @Override
    Page<Patient> findAll(Pageable pageable);

    @Query("SELECT p FROM Patient p WHERE LOWER(p.nom) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Patient> findByNomContaining(@Param("name") String name, Pageable pageable);

}
