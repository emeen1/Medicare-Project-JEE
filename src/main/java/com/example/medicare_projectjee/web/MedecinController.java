package com.example.medicare_projectjee.web;

import com.example.medicare_projectjee.dao.entities.AppUser;
import com.example.medicare_projectjee.dao.entities.Medecin;
import com.example.medicare_projectjee.dao.repositories.AppUserRepository;
import com.example.medicare_projectjee.service.IHospitalService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class MedecinController {

    private IHospitalService hospitalService;

    // AJOUTS INDISPENSABLES POUR CRÉER LE COMPTE
    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/medecins")
    public String index(Model model) {
        model.addAttribute("listMedecins", hospitalService.getAllMedecins());
        return "medecins";
    }

    @GetMapping("/admin/formMedecin")
    public String formMedecin(Model model) {
        model.addAttribute("medecin", new Medecin());
        return "formMedecin";
    }

    @PostMapping("/admin/saveMedecin")
    public String save(Medecin medecin) {
        // 1. On vérifie si le compte existe déjà pour éviter une erreur
        if (appUserRepository.findById(medecin.getEmail()).isPresent()) {
            return "redirect:/admin/formMedecin?error=EmailExisteDeja";
        }

        // 2. CRÉATION AUTOMATIQUE DU COMPTE UTILISATEUR (AppUser)
        AppUser user = new AppUser();
        user.setUsername(medecin.getEmail());
        user.setPassword(passwordEncoder.encode("1234")); // Mot de passe crypté
        user.setRole("MEDECIN");

        appUserRepository.save(user); // Sauvegarde le login

        // 3. LIAISON DU MÉDECIN AVEC SON COMPTE
        medecin.setUsername(medecin.getEmail()); // Important pour que le dashboard fonctionne

        // 4. SAUVEGARDE DU MÉDECIN (Métier)
        hospitalService.saveMedecin(medecin);

        return "redirect:/medecins";
    }

    @GetMapping("/admin/deleteMedecin")
    public String delete(Long id) {
        // (Optionnel) Idéalement, il faudrait aussi supprimer le AppUser associé
        hospitalService.deleteMedecin(id);
        return "redirect:/medecins";
    }
}