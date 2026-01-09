package com.example.medicare_projectjee.web;

import com.example.medicare_projectjee.dao.repositories.MedecinRepository;
import com.example.medicare_projectjee.dao.repositories.PatientRepository;
import com.example.medicare_projectjee.dao.repositories.RendezVousRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AdminController {

    private PatientRepository patientRepo;
    private MedecinRepository medecinRepo;
    private RendezVousRepository rdvRepo;

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        // On envoie des statistiques pour faire joli
        model.addAttribute("nbPatients", patientRepo.count());
        model.addAttribute("nbMedecins", medecinRepo.count());
        model.addAttribute("nbRDV", rdvRepo.count());
        return "admin_dashboard";
    }
}
