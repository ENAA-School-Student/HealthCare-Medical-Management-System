package com.healthcare.healthcare.service;

import com.healthcare.healthcare.entity.Doctor;
import com.healthcare.healthcare.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class DoctorServiceTest {
//    @Autowired
//    DoctorService doctorService;
//    @Autowired
//    DoctorRepository doctorRepository;
//
//    @Test
//    void deleteDoctorTest(){
//
//        Doctor doctor = Doctor.builder()
//                .nom("youssef")
//                .email("errachid@gmail.com")
//                .specialite("null")
//                .telephone("78753455")
//                .build();
//        doctorRepository.save(doctor);
//
//        doctorService.deleteDoctor(doctor.getId());
//        assertFalse(doctorRepository.existsById(doctor.getId()));
//    }
}