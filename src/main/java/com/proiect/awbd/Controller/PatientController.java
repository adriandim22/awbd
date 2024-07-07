package com.proiect.awbd.Controller;

import com.proiect.awbd.Model.Patient;
import com.proiect.awbd.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public String getAllPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patients"; // This returns the Thymeleaf template "patients.html"
    }

    @PostMapping
    public String createPatient(@RequestParam String name, @RequestParam String address, @RequestParam String phoneNumber) {
        Patient patient = new Patient();
        patient.setName(name);
        patient.setAddress(address);
        patient.setPhoneNumber(phoneNumber);
        patientService.savePatient(patient);
        return "redirect:/patients";
    }

    @PostMapping("/{id}")
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

    @GetMapping("/{id}/delete")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }
}
