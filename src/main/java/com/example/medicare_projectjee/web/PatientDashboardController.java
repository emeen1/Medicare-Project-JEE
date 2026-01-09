package com.example.medicare_projectjee.web;

import com.example.medicare_projectjee.dao.entities.Patient;
import com.example.medicare_projectjee.dao.repositories.PatientRepository;
import com.example.medicare_projectjee.dao.repositories.RendezVousRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class PatientDashboardController {

    private RendezVousRepository rdvRepository;
    private PatientRepository patientRepository;

    @GetMapping("/patient/dashboard")
    public String dashboard(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("myRDVs", rdvRepository.findByPatientUsername(username));

        Patient patientConnecte = patientRepository.findByUsername(username);
        model.addAttribute("patientInfo", patientConnecte);

        return "patient_dashboard";
    }
}
