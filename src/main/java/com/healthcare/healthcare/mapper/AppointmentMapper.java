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
    @Mapping(source = "doctorId",  target = "doctor.id")
    @Mapping(target = "id",        ignore = true)
    Appointment toEntity(AppointmentRequestDTO dto);

    @Mapping(source = "patient.id",        target = "patientId")
    @Mapping(source = "patient.nom",       target = "patientNom")
    @Mapping(source = "patient.prenom",    target = "patientPrenom")
    @Mapping(source = "doctor.id",         target = "doctorId")
    @Mapping(source = "doctor.nom",        target = "doctorNom")
    @Mapping(source = "doctor.specialite", target = "doctorSpecialite")
    AppointmentResponseDTO toDto(Appointment appointment);

    List<AppointmentResponseDTO> toDtos(List<Appointment> appointments);
}
