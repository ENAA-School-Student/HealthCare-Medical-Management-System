package com.healthcare.healthcare.repository;

import com.healthcare.healthcare.entity.Appointment;
import com.healthcare.healthcare.entity.AppointmentStatus;
import jdk.jshell.Snippet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDateRendezVousAndDoctorId(LocalDateTime dateRendezVous, Long doctorId);

    List<Appointment> findByPatient_Id(Long patientid);

    List<Appointment> findByDoctor_Id(Long doctorid);

    @Override
    Page<Appointment> findAll(Pageable pageable);

    Page<Appointment> findAppointmentsByStatus(AppointmentStatus status,Pageable pageable);

    @Query("SELECT COUNT(a) FROM Appointment a WHERE CAST(a.dateRendezVous AS date) = CURRENT_DATE")
    Long countAppointmentsToday();

    @Query("SELECT a FROM Appointment a ORDER BY a.dateRendezVous DESC LIMIT 4")
    List<Appointment> findTop4RecentAppointments();

    @Query("SELECT FUNCTION('DAYNAME', a.dateRendezVous), COUNT(a) " +
            "FROM Appointment a " +
            "WHERE a.dateRendezVous >= :startOfWeek " +
            "AND a.dateRendezVous <= :endOfWeek " +
            "GROUP BY FUNCTION('DAYNAME', a.dateRendezVous)")
    List<Object[]> countAppointmentsPerDayThisWeek(
            @Param("startOfWeek") LocalDateTime startOfWeek,
            @Param("endOfWeek") LocalDateTime endOfWeek
    );
}
