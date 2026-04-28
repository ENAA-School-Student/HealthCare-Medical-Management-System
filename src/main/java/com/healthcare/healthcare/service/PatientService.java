package com.healthcare.healthcare.service;

import com.healthcare.healthcare.dto.PatientRequestDTO;
import com.healthcare.healthcare.dto.PatientResponseDTO;
import com.healthcare.healthcare.entity.Patient;
import com.healthcare.healthcare.mapper.PatientMapper;
import com.healthcare.healthcare.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PatientService {

    final PatientRepository patientRepository;
    final PatientMapper patientMapper;

    public PatientResponseDTO addPatient( PatientRequestDTO dto){
        if(patientRepository.existsPatientById(dto.getEmail())){
            throw new RuntimeException("Patient already exist with Email " + dto.getEmail());
        }

        Patient patient = patientMapper.toEntity(dto);
        return patientMapper.toDto(patientRepository.save(patient));
    }


}
