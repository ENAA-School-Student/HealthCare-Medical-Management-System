package com.healthcare.healthcare.service;

import com.healthcare.healthcare.dto.PatientRequestDTO;
import com.healthcare.healthcare.dto.PatientResponseDTO;
import com.healthcare.healthcare.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientServiceIT {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void shouldAddNewPatient_YoussefErrachid() {

        PatientRequestDTO dto = new PatientRequestDTO();

        dto.setNom("Errachid");
        dto.setPrenom("Youssef");
        dto.setEmail("youssef.errachid@test.com");
        dto.setTelephone("0600000000");
        dto.setDateNaissance(LocalDate.of(1995, 5, 10));

        PatientResponseDTO saved = patientService.addPatient(dto);

        assertNotNull(saved);
        assertNotNull(saved.getId());

        assertEquals("Youssef", saved.getPrenom());
        assertEquals("Errachid", saved.getNom());
        assertEquals("youssef.errachid@test.com", saved.getEmail());

        assertTrue(patientRepository.existsPatientByEmail("youssef.errachid@test.com"));
    }
}