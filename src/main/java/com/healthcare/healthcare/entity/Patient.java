package com.healthcare.healthcare.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column
    private String nom ;

    @Column
    private String premon;

    @Column
    private String email;

    @Column
    private String telephone;

    @Column
    private LocalDate dateNaissance;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    @OneToOne(mappedBy = "patient",cascade = CascadeType.ALL)
    private MedicalRecord medicalRecord;

}
