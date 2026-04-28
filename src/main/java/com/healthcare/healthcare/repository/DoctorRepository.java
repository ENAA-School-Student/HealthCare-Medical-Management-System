package com.healthcare.healthcare.repository;

import com.healthcare.healthcare.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor , Long> {
    boolean existsDoctorByEmail(String email);
}
