package com.example.medicare_projectjee.web;

import org.springframework.web.bind.annotation.RequestParam;
import com.example.medicare_projectjee.dao.entities.AppUser;
import com.example.medicare_projectjee.dao.entities.Patient;
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
public class PatientController {

    private IHospitalService hospitalService;

    // AJOUTS INDISPENSABLES pour créer le compte de connexion
    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/patients")
    public String index(Model model) {
        model.addAttribute("listPatients", hospitalService.getAllPatients());
        return "patients";
    }

    @GetMapping("/admin/formPatient")
    public String formPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "formPatient";
    }

    @PostMapping("/admin/savePatient")
    public String save(Patient patient) {

        // 1. On vérifie si un compte avec cet email existe déjà
        if (appUserRepository.findByUsername(patient.getEmail()) != null) {
            // Redirige avec un message d'erreur pour l'afficher dans le formulaire
            return "redirect:/admin/formPatient?error=Email déjà utilisé";
        }

        // 2. CRÉATION AUTOMATIQUE DU COMPTE DE CONNEXION (AppUser)
        AppUser newUser = new AppUser();
        newUser.setUsername(patient.getEmail());
        newUser.setPassword(passwordEncoder.encode("1234")); // Crypter le mot de passe
        newUser.setRole("PATIENT");
        appUserRepository.save(newUser); // On enregistre le compte

        // 3. LIAISON : On associe le patient à son nom d'utilisateur
        patient.setUsername(patient.getEmail());

        // 4. SAUVEGARDE : On enregistre l'entité Patient
        hospitalService.savePatient(patient);

        return "redirect:/patients";
    }

    @GetMapping("/admin/deletePatient")
    public String delete(Long id, @RequestParam(name = "username", defaultValue = "") String username) {
        // Idéalement, on supprime aussi le compte associé
        if (!username.isEmpty()) {
            appUserRepository.deleteById(username);
        }
        hospitalService.deletePatient(id);
        return "redirect:/patients";
    }
}
