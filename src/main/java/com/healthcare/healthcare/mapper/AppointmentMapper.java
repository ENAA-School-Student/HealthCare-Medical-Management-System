package com.healthcare.healthcare.mapper;

import com.healthcare.healthcare.dto.AppointmentRequestDTO;
import com.healthcare.healthcare.dto.AppointmentResponseDTO;
import com.healthcare.healthcare.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    @Mapping(source = "patientId", target = "patient.id")
    @Mapping(source = "doctorId",target = "doctor.id")
    Appointment toEntity(AppointmentRequestDTO dto);

    @Mapping(source = "patientId", target = "patient.id")
    @Mapping(source = "doctorId",target = "doctor.id")
    AppointmentResponseDTO toDto(Appointment appointment);

    List<AppointmentResponseDTO> toDtos(List<Appointment> appointments);
}
