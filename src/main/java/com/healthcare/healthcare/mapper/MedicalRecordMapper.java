package com.healthcare.healthcare.mapper;

import com.healthcare.healthcare.dto.MedicalRecordRequestDTO;
import com.healthcare.healthcare.dto.MedicalRecordResponseDTO;
import com.healthcare.healthcare.entity.MedicalRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicalRecordMapper {
    @Mapping(source = "patientId", target = "patient.id")
    MedicalRecord toEntity(MedicalRecordRequestDTO dto);

    @Mapping(source = "patientId", target = "patient.id")
    MedicalRecordResponseDTO toDto(MedicalRecord medicalRecord);

    List<MedicalRecordResponseDTO> toDtos(List<MedicalRecord>medicalRecords);
}
