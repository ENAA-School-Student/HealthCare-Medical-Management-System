create table patient (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    telephone VARCHAR(15),
    dateNaissance DATE

    );

CREATE TABLE doctor(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom  VARCHAR(50) NOT NULL ,
    specialite VARCHAR(50) NOT NULL ,
    email VARCHAR(50) NOT NULL UNIQUE,
    telephone VARCHAR(15)
    );

CREATE TABLE appointment(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dateRendezVous DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL,
    patient_id BIGINT NOT NULL ,
    doctor_id BIGINT NOT NULL ,

    CONSTRAINT FK_appointment_patient FOREIGN KEY (patient_id) REFERENCES patient(id),
    CONSTRAINT FK_appointment_doctor FOREIGN KEY (doctor_id) REFERENCES doctor(id)
);

CREATE TABLE medicalrecord(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    diagnostic VARCHAR(255) not null ,
    observation varchar(255) not null ,
    dateCreation DATE NOT NULL ,
    patient_id BIGINT NOT NULL UNIQUE ,
    CONSTRAINT FK_medicalrecord_patient FOREIGN KEY (patient_id) REFERENCES patient(id)
);