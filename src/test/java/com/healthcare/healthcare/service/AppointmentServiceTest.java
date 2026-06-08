package com.healthcare.healthcare.service;

import com.healthcare.healthcare.dto.AppointmentRequestDTO;
import com.healthcare.healthcare.dto.AppointmentResponseDTO;
import com.healthcare.healthcare.entity.Appointment;
import com.healthcare.healthcare.entity.AppointmentStatus;
import com.healthcare.healthcare.entity.Doctor;
import com.healthcare.healthcare.entity.Patient;
import com.healthcare.healthcare.repository.AppointmentRepository;
import com.healthcare.healthcare.repository.DoctorRepository;
import com.healthcare.healthcare.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class AppointmentServiceTest {
    @Autowired
   private AppointmentService appointmentService;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    void updateAppointmentTest(){

        Doctor doctor = Doctor.builder()
                .nom("youssef")
                .email("errachid@gmail.com")
                .specialite("null")
                .telephone("78753455")
                .build();
        doctorRepository.save(doctor);
        Patient patient = Patient.builder()
                .nom("Errachid")
                .prenom("yoiussef")
                .email("errahid@gmail.com")
                .telephone("5234567678")
                .dateNaissance(LocalDate.of(2000,01,16))
                .build();
        patientRepository.save(patient);

        Appointment appointment = Appointment.builder()
                .dateRendezVous(LocalDateTime.of(2026,2,3,23,3))
                .status(AppointmentStatus.SCHEDULED)
                .doctor(doctor)
                .patient(patient)
                .build();
        Appointment savedAppointment = appointmentRepository.save(appointment);

        AppointmentRequestDTO updateAppointment = AppointmentRequestDTO.builder()
                .dateRendezVous(LocalDateTime.of(2026,7,20,12,2))
                .status(AppointmentStatus.COMPLETED)
                .doctorId(doctor.getId())
                .patientId(patient.getId())
                .build();

        AppointmentResponseDTO responseDTO = appointmentService.updateAppointment(savedAppointment.getId(),updateAppointment);

        assertNotNull(responseDTO);
        assertEquals(LocalDateTime.of(2026,7,20,12,2),responseDTO.getDateRendezVous());
    }

    @Test
    void findAppointmentByPatientIdTest(){
        Doctor doctor = Doctor.builder()
                .nom("youssef")
                .email("errachid@gmail.com")
                .specialite("null")
                .telephone("78753455")
                .build();
        doctorRepository.save(doctor);
        Patient patient = Patient.builder()
                .nom("Errachid")
                .prenom("yoiussef")
                .email("errahid@gmail.com")
                .telephone("5234567678")
                .dateNaissance(LocalDate.of(2000,01,16))
                .build();
        patientRepository.save(patient);

        AppointmentRequestDTO appointment = AppointmentRequestDTO.builder()
                .dateRendezVous(LocalDateTime.of(2026,2,3,23,3))
                .status(AppointmentStatus.SCHEDULED)
                .doctorId(doctor.getId())
                .patientId(patient.getId())
                .build();
        AppointmentResponseDTO savedAppointment = appointmentService.addAppointment(appointment);

        List<AppointmentResponseDTO> appointments = appointmentService.findAppointmentByPatientId(patient.getId());
    }

}