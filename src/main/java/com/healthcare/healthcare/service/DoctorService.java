package com.healthcare.healthcare.service;

import com.healthcare.healthcare.dto.DoctorRequestDTO;
import com.healthcare.healthcare.dto.DoctorResponseDTO;
import com.healthcare.healthcare.dto.PatientRequestDTO;
import com.healthcare.healthcare.dto.PatientResponseDTO;
import com.healthcare.healthcare.entity.Doctor;
import com.healthcare.healthcare.entity.Patient;
import com.healthcare.healthcare.mapper.DoctorMapper;
import com.healthcare.healthcare.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    final DoctorRepository doctorRepository;
    final DoctorMapper doctorMapper;

    public DoctorResponseDTO addDoctor(DoctorRequestDTO dto){
        if(doctorRepository.existsDoctorByEmail(dto.getEmail())){
            throw new RuntimeException("Doctor already exist with Email " + dto.getEmail());
        }

        Doctor doctor = doctorMapper.toEntity(dto);
        return doctorMapper.toDto(doctorRepository.save(doctor));
    }

    public void deleteDoctor(Long id){
        if(!doctorRepository.existsById(id)){
            throw new RuntimeException("doctor with id:" + id + "not found");
        }
        doctorRepository.deleteById(id);
    }

    public Page<DoctorResponseDTO> listOfDoctors(int page , int size, String sortBy, String sortDir){
        Pageable pageable = PageRequest.of(page,size);
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        return doctorRepository.findAll(pageable)
                .map(doctorMapper::toDto);
    }

    public DoctorResponseDTO updateDoctor(Long id ,DoctorRequestDTO dto){
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("patient not found"));
        doctorMapper.updateEntityFromDto(dto,doctor);
        return doctorMapper.toDto(doctorRepository.save(doctor));
    }
}
