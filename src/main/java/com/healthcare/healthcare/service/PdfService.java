package com.healthcare.healthcare.service;

import com.healthcare.healthcare.entity.MedicalRecord;
import com.healthcare.healthcare.repository.MedicalRecordRepository;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class PdfService {

    private final MedicalRecordRepository medicalRecordRepository;

    public byte[] generateMedicalRecordPdf(Long patientId) {

        MedicalRecord record = medicalRecordRepository.findByPatientId(patientId);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        document.add(new Paragraph("Medical Record"));
        document.add(new Paragraph("-----------------------------"));

        document.add(new Paragraph("Patient Name: "
                + record.getPatient().getNom()
                + " "
                + record.getPatient().getPrenom()));

        document.add(new Paragraph("Email: "
                + record.getPatient().getEmail()));

        document.add(new Paragraph("Phone: "
                + record.getPatient().getTelephone()));

        document.add(new Paragraph("Birth Date: "
                + record.getPatient().getDateNaissance()));

        document.add(new Paragraph(" "));

        document.add(new Paragraph("Record ID: "
                + record.getId()));

        document.add(new Paragraph("Creation Date: "
                + record.getDateCreation()));

        document.add(new Paragraph("Diagnostic: "
                + record.getDiagnostic()));

        document.add(new Paragraph("Observation: "
                + record.getObservation()));

        document.close();

        return outputStream.toByteArray();
    }
}