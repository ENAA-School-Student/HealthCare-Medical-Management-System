package com.healthcare.healthcare.service;

import com.healthcare.healthcare.dto.AppointmentRequestDTO;
import com.healthcare.healthcare.dto.AppointmentResponseDTO;
import com.healthcare.healthcare.entity.Appointment;
import com.healthcare.healthcare.entity.AppointmentStatus;
import com.healthcare.healthcare.entity.Doctor;
import com.healthcare.healthcare.entity.Patient;
import com.healthcare.healthcare.mapper.AppointmentMapper;
import com.healthcare.healthcare.repository.AppointmentRepository;
import com.healthcare.healthcare.repository.DoctorRepository;
import com.healthcare.healthcare.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @CachePut(value = "appointment", key = "#result.id")
    public AppointmentResponseDTO addAppointment(AppointmentRequestDTO dto){
        if(appointmentRepository.existsByDateRendezVousAndDoctorId(dto.getDateRendezVous(),dto.getDoctorId())){
            throw new RuntimeException("appointment already exist");
        }

        Doctor doctor = doctorRepository.findById(dto.getDoctorId()).orElseThrow(() -> new RuntimeException("doctor with id:" + dto.getDoctorId() + "not found"));
        Patient patient = patientRepository.findById(dto.getPatientId()).orElseThrow(() -> new RuntimeException("patient with id:" + dto.getPatientId() + "not found"));

        Appointment appointment = Appointment.builder()
                .dateRendezVous(dto.getDateRendezVous())
                .status(dto.getStatus())
                .patient(patient)
                .doctor(doctor)
                .build();
        return appointmentMapper.toDto(appointmentRepository.save(appointment));
    }

    @CacheEvict(value = "appointment", key = "#id")
    public void deleteAppointment(Long id){
        if (!appointmentRepository.existsById(id)){
            throw new RuntimeException("appointment with id: " + id + "not found");
        }

        appointmentRepository.deleteById(id);
    }

    @Cacheable(
            value = "appointment",
            key = "#page + '-' + #size + '-' + #sortBy + '-' + #sortDir"
    )
    public Page<AppointmentResponseDTO> listOfAppointments(int page,int size, String sortBy, String sortDir){
        Pageable pageable = PageRequest.of(page,size);
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        return appointmentRepository.findAll(pageable)
                .map(appointmentMapper::toDto);
    }

    @CachePut(value = "appointment", key = "#id")
    public AppointmentResponseDTO updateAppointment(Long id , AppointmentRequestDTO dto){
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("appointment not found"));
         appointmentMapper.updateEntityFromDto(dto,appointment);
        return appointmentMapper.toDto(appointmentRepository.save(appointment));
    }

    public List<AppointmentResponseDTO> findAppointmentByPatientId(Long patientid){
        List<Appointment> appointments = appointmentRepository.findByPatient_Id(patientid);
        return appointmentMapper.toDtos(appointments);
    }

    public List<AppointmentResponseDTO> findAppointmentByDoctorId(Long doctorid){
        List<Appointment> appointments = appointmentRepository.findByDoctor_Id(doctorid);
        return appointmentMapper.toDtos(appointments);
    }

    @Cacheable(
            value = "appointmentStatus",
            key = "#status + '-' + #page + '-' + #size"
    )
    public Page<AppointmentResponseDTO> findAppointmentByStatus(AppointmentStatus status,int page ,int size){
        Pageable pageable = PageRequest.of(page, size);
        return appointmentRepository.findAppointmentsByStatus(status,pageable).map(appointmentMapper::toDto);
    }
}
