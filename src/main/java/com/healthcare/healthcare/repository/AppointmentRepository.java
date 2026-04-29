package com.healthcare.healthcare.repository;

import com.healthcare.healthcare.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDateRendezVousAndDoctorId(LocalDateTime dateRendezVous, Long doctorId);
}
