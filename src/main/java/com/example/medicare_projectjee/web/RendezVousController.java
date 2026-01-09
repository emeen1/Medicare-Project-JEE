package com.example.medicare_projectjee.web;

import com.example.medicare_projectjee.dao.entities.Patient;
import com.example.medicare_projectjee.dao.entities.RendezVous;
import com.example.medicare_projectjee.dao.entities.StatusRDV;
import com.example.medicare_projectjee.dao.repositories.PatientRepository;
import com.example.medicare_projectjee.service.IHospitalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.security.Principal;

@Controller
@AllArgsConstructor
public class RendezVousController {

    private IHospitalService hospitalService;
    private PatientRepository patientRepository;

    // Formulaire simplifié
    @GetMapping("/formRDV")
    public String formRDV(Model model) {
        model.addAttribute("rendezVous", new RendezVous());
        // On n'envoie PLUS la liste des patients, le patient ne doit pas voir les autres !
        model.addAttribute("medecins", hospitalService.getAllMedecins());
        return "formRDV";
    }

    // Sauvegarde Intelligente
    @PostMapping("/saveRDV")
    public String save(RendezVous rdv, Principal principal) {

        // 1. Forcer le statut à "EN ATTENTE"
        rdv.setStatus(StatusRDV.PENDING);

        // 2. Trouver qui est connecté (ex: "patient1")
        String username = principal.getName();

        // 3. Récupérer l'objet Patient correspondant en base
        Patient patientConnecte = patientRepository.findByUsername(username);

        // 4. Lier le RDV à ce patient
        rdv.setPatient(patientConnecte);

        // 5. Sauvegarder
        hospitalService.saveRDV(rdv);

        // Rediriger vers le dashboard du patient
        return "redirect:/patient/dashboard";
    }

    // ... garde la méthode index/rendezvous pour l'admin ...
    @GetMapping("/rendezvous")
    public String index(Model model) {
        model.addAttribute("listRDV", hospitalService.getAllRDV());
        return "rendezvous";
    }
}