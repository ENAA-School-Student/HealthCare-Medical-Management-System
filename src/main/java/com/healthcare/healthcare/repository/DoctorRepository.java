package com.healthcare.healthcare.repository;

import com.healthcare.healthcare.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor , Long> {
    boolean existsDoctorByEmail(String email);

    @Override
    Page<Doctor> findAll(Pageable pageable);
}
