package com.healthcare.healthcare.mapper;

import com.healthcare.healthcare.dto.MedicalRecordDiagnosticRequestDTO;
import com.healthcare.healthcare.dto.MedicalRecordRequestDTO;
import com.healthcare.healthcare.dto.MedicalRecordResponseDTO;
import com.healthcare.healthcare.entity.MedicalRecord;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicalRecordMapper {
    @Mapping(source = "patientId", target = "patient.id")
    MedicalRecord toEntity(MedicalRecordRequestDTO dto);

    @Mapping(source = "patient.nom",       target = "patientNom")
    @Mapping(source = "patient.prenom",    target = "patientprenom")
    @Mapping(source = "patient.id", target = "patientId")
    MedicalRecordResponseDTO toDto(MedicalRecord medicalRecord);

    List<MedicalRecordResponseDTO> toDtos(List<MedicalRecord>medicalRecords);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id",          ignore = true)
    @Mapping(target = "observation", ignore = true)
    @Mapping(target = "dateCreation",ignore = true)
    @Mapping(target = "patient",     ignore = true)
    void updateEntityFromDto(MedicalRecordDiagnosticRequestDTO dto, @MappingTarget MedicalRecord medicalRecord);

}
