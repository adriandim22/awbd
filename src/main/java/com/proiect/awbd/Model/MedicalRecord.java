package com.proiect.awbd.Model;

import jakarta.persistence.*;

@Entity
public class MedicalRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String diagnosis;
    private String treatment;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
