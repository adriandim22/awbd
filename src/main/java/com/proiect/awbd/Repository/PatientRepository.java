package com.proiect.awbd.Repository;

import com.proiect.awbd.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> { }
