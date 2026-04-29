package com.healthcare.healthcare.service;

import com.healthcare.healthcare.dto.AppointmentRequestDTO;
import com.healthcare.healthcare.dto.AppointmentResponseDTO;
import com.healthcare.healthcare.dto.DoctorRequestDTO;
import com.healthcare.healthcare.dto.DoctorResponseDTO;
import com.healthcare.healthcare.entity.Appointment;
import com.healthcare.healthcare.entity.Doctor;
import com.healthcare.healthcare.entity.Patient;
import com.healthcare.healthcare.mapper.AppointmentMapper;
import com.healthcare.healthcare.repository.AppointmentRepository;
import com.healthcare.healthcare.repository.DoctorRepository;
import com.healthcare.healthcare.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

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

    public void deleteAppointment(Long id){
        if (!appointmentRepository.existsById(id)){
            throw new RuntimeException("appointment with id: " + id + "not found");
        }

        appointmentRepository.deleteById(id);
    }

    public List<AppointmentResponseDTO> listOfAppointments(){
        return appointmentMapper.toDtos(appointmentRepository.findAll());
    }

    public AppointmentResponseDTO updateAppointment(Long id , AppointmentRequestDTO dto){
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("appointment not found"));
        appointmentMapper.updateEntityFromDto(dto,appointment);
        return appointmentMapper.toDto(appointmentRepository.save(appointment));
    }

}
