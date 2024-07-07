package com.proiect.awbd.Repository;

import com.proiect.awbd.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> { }

