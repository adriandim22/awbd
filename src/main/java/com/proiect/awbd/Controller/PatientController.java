package com.proiect.awbd.Controller;

import com.proiect.awbd.Model.Patient;
import com.proiect.awbd.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    // Creaza o instanta logger pt logs
    private static final Logger log = LoggerFactory.getLogger(PatientController.class);

    @GetMapping
    public String getAllPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patients";
    }

    @PostMapping
    public String createPatient(@RequestParam String name, @RequestParam String address, @RequestParam String phoneNumber) {
        // Log the creation attempt
        log.info("Creating a new patient with name: {}, address: {}, phone number: {}", name, address, phoneNumber);

        Patient patient = new Patient();
        patient.setName(name);
        patient.setAddress(address);
        patient.setPhoneNumber(phoneNumber);

        try {
            patientService.savePatient(patient);
            // Log successful creation
            log.info("Successfully created patient with ID: {}", patient.getId());
        } catch (Exception e) {
            // Log fail
            log.error("Error creating patient with name: {}, address: {}, phone number: {}. Error: {}", name, address, phoneNumber, e.getMessage(), e);
        }

        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String editPatient(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            model.addAttribute("editPatient", patient);
        }
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patients";
    }

    @PostMapping("/update/{id}")
    public String updatePatient(@PathVariable Long id, @RequestParam String name, @RequestParam String address, @RequestParam String phoneNumber) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            patient.setName(name);
            patient.setAddress(address);
            patient.setPhoneNumber(phoneNumber);
            patientService.savePatient(patient);
        }
        return "redirect:/patients";
    }

    @PostMapping("/{id}/delete")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }
}
