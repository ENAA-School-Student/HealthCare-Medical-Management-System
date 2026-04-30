INSERT INTO patient (nom, prenom, email, telephone, dateNaissance) VALUES

 ('El Amrani', 'Youssef', 'youssef.elamrani@gmail.com', '0612345678', '1990-05-12'),

 ('Benali', 'Salma', 'salma.benali@gmail.com', '0623456789', '1985-09-23'),

 ('Alaoui', 'Karim', 'karim.alaoui@gmail.com', '0634567890', '1992-11-02');


INSERT INTO doctor (nom, specialite, email, telephone) VALUES
    ('Haddad', 'Cardiologie', 'haddad@gmail.com', '0654321987'),
    ('Tazi', 'Dermatologie', 'tazi@gmail.com', '0665432198'),
    ('Idrissi', 'Pédiatrie', 'idrissi@gmail.com', '0676543219');


INSERT INTO appointment (dateRendezVous, status, patient_id, doctor_id) VALUES

    ('2026-05-01 10:00:00', 'CONFIRMED', 1, 1),

    ('2026-05-02 14:30:00', 'PENDING', 2, 2),

    ('2026-05-03 09:15:00', 'CANCELLED', 3, 3);


INSERT INTO medicalrecord (diagnostic, observation, dateCreation, patient_id) VALUES

    ('Hypertension', 'Patient avec tension élevée', '2026-04-01', 1),

    ('Allergie cutanée', 'Réaction allergique saisonnière', '2026-04-05', 2),

    ('Fièvre', 'Symptômes grippaux légers', '2026-04-10', 3);