package com.healthcare.healthcare.service;

import com.healthcare.healthcare.dto.PatientRequestDTO;
import com.healthcare.healthcare.dto.PatientResponseDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PatientServiceTest {
    @Autowired
    private  PatientService patientService;

    @Test
    void addPatientTest(){

        PatientRequestDTO dto = PatientRequestDTO.builder()
                .nom("Errachid")
                .prenom("youssef")
                .email("yousseff@gmail.com")
                .telephone("7875982747")
                .dateNaissance(LocalDate.of(2000,01,16))
                .build();

        PatientResponseDTO responseDTO = patientService.addPatient(dto);

        assertNotNull(responseDTO.getId());
        assertEquals("yousseff@gmail.com",responseDTO.getEmail());
    }

    @Test
    void getAllPatientTest(){
        PatientRequestDTO dto1 = PatientRequestDTO.builder()
                .nom("Errachid1")
                .prenom("youssef1")
                .email("yousseff1@gmail.com")
                .telephone("7875982747")
                .dateNaissance(LocalDate.of(2000,01,16))
                .build();

        PatientRequestDTO dto2 = PatientRequestDTO.builder()
                .nom("Errachid2")
                .prenom("youssef2")
                .email("yousseff2@gmail.com")
                .telephone("7875982747")
                .dateNaissance(LocalDate.of(2000,01,16))
                .build();

        patientService.addPatient(dto1);
        patientService.addPatient(dto2);

        List<PatientResponseDTO> responseDTOList = patientService.listOfPatients();

        assertNotNull(responseDTOList);
        assertFalse(responseDTOList.isEmpty());

    }

}