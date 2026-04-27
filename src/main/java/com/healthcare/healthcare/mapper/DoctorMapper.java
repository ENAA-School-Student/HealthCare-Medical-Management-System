package com.healthcare.healthcare.mapper;

import com.healthcare.healthcare.dto.DoctorRequestDTO;
import com.healthcare.healthcare.dto.DoctorResponseDTO;
import com.healthcare.healthcare.entity.Doctor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    Doctor toEntity(DoctorRequestDTO dto);

    DoctorResponseDTO toDto(Doctor patient);

    List<DoctorResponseDTO> toDtos(List<Doctor>doctors);
}
