package com.healthcare.healthcare.mapper;

import com.healthcare.healthcare.dto.PatientRequestDTO;
import com.healthcare.healthcare.dto.PatientResponseDTO;
import com.healthcare.healthcare.entity.Patient;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    Patient toEntity(PatientRequestDTO dto);

    PatientResponseDTO toDto(Patient patient);

    List<PatientResponseDTO> toDtos(List<Patient>patients);
}
