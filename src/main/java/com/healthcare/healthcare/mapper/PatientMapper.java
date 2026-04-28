package com.healthcare.healthcare.mapper;

import com.healthcare.healthcare.dto.PatientRequestDTO;
import com.healthcare.healthcare.dto.PatientResponseDTO;
import com.healthcare.healthcare.entity.Patient;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appointments", ignore = true)
    @Mapping(target = "medicalRecord", ignore = true)
    Patient toEntity(PatientRequestDTO dto);

    PatientResponseDTO toDto(Patient patient);

    List<PatientResponseDTO> toDtos(List<Patient>patients);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appointments", ignore = true)
    @Mapping(target = "medicalRecord", ignore = true)
    void updateEntityFromDto(PatientRequestDTO dto, @MappingTarget Patient patient);
}
