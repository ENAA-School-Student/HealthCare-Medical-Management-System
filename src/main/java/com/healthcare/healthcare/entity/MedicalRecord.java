package com.healthcare.healthcare.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "medicalrecord")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column
    private  String diagnostic ;

    @Column
    private String observation ;

    @Column
    private LocalDate dateCreation;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private  Patient patient;


}
