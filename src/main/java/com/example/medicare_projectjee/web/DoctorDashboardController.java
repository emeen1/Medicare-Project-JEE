package com.example.medicare_projectjee.web;

import com.example.medicare_projectjee.dao.entities.Medecin;
import com.example.medicare_projectjee.dao.entities.RendezVous;
import com.example.medicare_projectjee.dao.entities.StatusRDV;
import com.example.medicare_projectjee.dao.repositories.MedecinRepository;
import com.example.medicare_projectjee.dao.repositories.RendezVousRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class DoctorDashboardController {

    private RendezVousRepository rdvRepository;
    private MedecinRepository medecinRepository; // <--- AJOUTER l'injection

    @GetMapping("/medecin/dashboard")
    public String dashboard(Model model, Principal principal) {
        String username = principal.getName();

        // 1. Récupérer les RDV
        List<RendezVous> myRDVs = rdvRepository.findByMedecinUsername(username);
        model.addAttribute("listRDV", myRDVs);

        // 2. RECUPERER LE MÉDECIN CONNECTÉ (Pour afficher son nom)
        Medecin medecinConnecte = medecinRepository.findByUsername(username);
        model.addAttribute("doctorInfo", medecinConnecte); // On l'envoie à la vue sous le nom "doctorInfo"

        return "medecin_dashboard";
    }

    // Action pour changer le statut (Confirmer/Annuler)
    @GetMapping("/medecin/changeStatus")
    public String changeStatus(Long id, String newStatus) {
        RendezVous rdv = rdvRepository.findById(id).get();
        rdv.setStatus(StatusRDV.valueOf(newStatus)); // Convertit String en Enum
        rdvRepository.save(rdv);
        return "redirect:/medecin/dashboard";
    }
}
