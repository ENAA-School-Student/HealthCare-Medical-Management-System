package com.healthcare.healthcare.service;

import com.healthcare.healthcare.dto.PatientRequestDTO;
import com.healthcare.healthcare.dto.PatientResponseDTO;
import com.healthcare.healthcare.entity.Patient;
import com.healthcare.healthcare.mapper.PatientMapper;
import com.healthcare.healthcare.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;



@RequiredArgsConstructor
@Service
public class PatientService {

    final PatientRepository patientRepository;
    final PatientMapper patientMapper;

    @CachePut(value = "patient", key = "#result.id")

    public PatientResponseDTO addPatient( PatientRequestDTO dto){
        if(patientRepository.existsPatientByEmail(dto.getEmail())){
            throw new RuntimeException("Patient already exist with Email " + dto.getEmail());
        }

        Patient patient = patientMapper.toEntity(dto);
        return patientMapper.toDto(patientRepository.save(patient));
    }

    @Cacheable(
            value = "patients",
            key = "#page + '-' + #size + '-' + #sortBy + '-' + #sortDir"
    )
    public Page<PatientResponseDTO> listOfPatients(int page, int size, String sortBy, String sortDir){

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page,size,sort);
        return patientRepository.findAll(pageable)
                .map(patientMapper::toDto);
    }

    @CacheEvict(value = "patient",key = "#id")
    public void deletePatient(Long id){
        if(!patientRepository.existsById(id)){
            throw new RuntimeException("patient with id:" + id + "not found");
        }
        patientRepository.deleteById(id);
    }

    public PatientResponseDTO findPatientById(Long id){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("patient not found"));
        return patientMapper.toDto(patient);
    }

    @CachePut(value = "patient",key = "#id")
    public PatientResponseDTO updatePatient(Long id ,PatientRequestDTO dto){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("patient not found"));
        patientMapper.updateEntityFromDto(dto,patient);
        return patientMapper.toDto(patientRepository.save(patient));
    }

    public Page<PatientResponseDTO> searchPatientsByName(
            String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return patientRepository.findByNomContaining(name, pageable)
                .map(patientMapper::toDto);
    }
}
