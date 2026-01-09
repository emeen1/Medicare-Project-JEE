package com.example.medicare_projectjee;


import com.example.medicare_projectjee.dao.repositories.*;
import com.example.medicare_projectjee.dao.entities.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class MediCareProjectJeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediCareProjectJeeApplication.class, args);
    }

    @Bean
    CommandLineRunner start(PatientRepository patientRepo,
                            MedecinRepository medecinRepo,
                            RendezVousRepository rdvRepo,
                            ConsultationRepository consultationRepo,
                            AppUserRepository userRepo,
                            PasswordEncoder passwordEncoder) {
        return args -> {

            System.out.println("⏳ Initialisation des données de démonstration...");

            // =========================================================
            // 1. CRÉATION DES COMPTES UTILISATEURS (LOGIN / MDP)
            // =========================================================
            // Mot de passe pour tous : "1234"

            // ADMIN
            userRepo.save(new AppUser("admin", passwordEncoder.encode("1234"), "ADMIN"));

            // MÉDECINS (Comptes de connexion)
            userRepo.save(new AppUser("dr.alami", passwordEncoder.encode("1234"), "MEDECIN"));
            userRepo.save(new AppUser("dr.bennani", passwordEncoder.encode("1234"), "MEDECIN"));

            // PATIENTS (Comptes de connexion)
            userRepo.save(new AppUser("karim@gmail.com", passwordEncoder.encode("1234"), "PATIENT"));
            userRepo.save(new AppUser("salma@gmail.com", passwordEncoder.encode("1234"), "PATIENT"));

            System.out.println("✅ Comptes créés : admin, dr.alami, dr.bennani, karim@gmail.com");


            // =========================================================
            // 2. CRÉATION DES MÉDECINS (DONNÉES MÉTIER)
            // =========================================================

            // Médecin 1 : Dr. Alami (Cardiologue) - Lié au compte 'dr.alami'
            Medecin m1 = new Medecin();
            m1.setNom("Dr. Ahmed Alami");
            m1.setEmail("alami@clinique.ma");
            m1.setSpecialite("Cardiologue");
            m1.setUsername("dr.alami"); // Lien Sécurité
            medecinRepo.save(m1);

            // Médecin 2 : Dr. Bennani (Dentiste) - Lié au compte 'dr.bennani'
            Medecin m2 = new Medecin();
            m2.setNom("Dr. Sara Bennani");
            m2.setEmail("bennani@clinique.ma");
            m2.setSpecialite("Chirurgien Dentiste");
            m2.setUsername("dr.bennani"); // Lien Sécurité
            medecinRepo.save(m2);

            // Médecin 3 : Dr. Tazi (Généraliste) - Pas de compte pour l'instant
            Medecin m3 = new Medecin();
            m3.setNom("Dr. Youssef Tazi");
            m3.setEmail("tazi@clinique.ma");
            m3.setSpecialite("Médecin Généraliste");
            m3.setUsername(null);
            medecinRepo.save(m3);


            // =========================================================
            // 3. CRÉATION DES PATIENTS (DONNÉES MÉTIER)
            // =========================================================

            // Patient 1 : Karim - Lié au compte 'karim@gmail.com'
            Patient p1 = new Patient();
            p1.setNom("Karim Idrissi");
            p1.setEmail("karim@gmail.com");
            p1.setUsername("karim@gmail.com"); // Lien Sécurité
            patientRepo.save(p1);

            // Patient 2 : Salma - Liée au compte 'salma@gmail.com'
            Patient p2 = new Patient();
            p2.setNom("Salma Benkirane");
            p2.setEmail("salma@gmail.com");
            p2.setUsername("salma@gmail.com"); // Lien Sécurité
            patientRepo.save(p2);

            // Patient 3 : Omar - Pas de compte (juste dossier administratif)
            Patient p3 = new Patient();
            p3.setNom("Omar Fassi");
            p3.setEmail("omar.fassi@hotmail.com");
            patientRepo.save(p3);


            // =========================================================
            // 4. CRÉATION DES RENDEZ-VOUS (SCÉNARIOS)
            // =========================================================

            // --- SCÉNARIO 1 : RDV PASSÉ & TERMINÉ (Pour la démo PDF) ---
            // Karim a vu Dr. Alami hier
            RendezVous rdvDone = new RendezVous();
            rdvDone.setDate(new Date(System.currentTimeMillis() - 86400000)); // Hier
            rdvDone.setStatus(StatusRDV.DONE);
            rdvDone.setPatient(p1);
            rdvDone.setMedecin(m1);
            rdvRepo.save(rdvDone);

            // Consultation associée (Rapport médical)
            Consultation consult = new Consultation();
            consult.setDateConsultation(new Date());
            consult.setRapport("Le patient présente une hypertension légère (14/9). \n" +
                    "Recommandations : Régime pauvre en sel, activité physique 30min/jour. \n" +
                    "Prescription : Amlodipine 5mg (1 comprimé le matin pendant 1 mois). \n" +
                    "Prochain contrôle dans 4 semaines.");
            consult.setRendezVous(rdvDone);
            consultationRepo.save(consult);


            // --- SCÉNARIO 2 : RDV EN ATTENTE (Pour la démo Validation Médecin) ---
            // Salma veut voir Dr. Alami demain
            RendezVous rdvPending = new RendezVous();
            rdvPending.setDate(new Date(System.currentTimeMillis() + 86400000)); // Demain
            rdvPending.setStatus(StatusRDV.PENDING);
            rdvPending.setPatient(p2);
            rdvPending.setMedecin(m1);
            rdvRepo.save(rdvPending);


            // --- SCÉNARIO 3 : RDV ANNULÉ (Pour montrer l'historique) ---
            // Omar avait un RDV avec Dr. Bennani
            RendezVous rdvCanceled = new RendezVous();
            rdvCanceled.setDate(new Date());
            rdvCanceled.setStatus(StatusRDV.CANCELED);
            rdvCanceled.setPatient(p3);
            rdvCanceled.setMedecin(m2);
            rdvRepo.save(rdvCanceled);


            // --- SCÉNARIO 4 : RDV FUTUR VALIDÉ ---
            // Karim a un détartrage prévu la semaine prochaine
            RendezVous rdvFuture = new RendezVous();
            rdvFuture.setDate(new Date(System.currentTimeMillis() + 604800000)); // Dans 7 jours
            rdvFuture.setStatus(StatusRDV.DONE); // Déjà validé par secrétaire
            rdvFuture.setPatient(p1);
            rdvFuture.setMedecin(m2);
            rdvRepo.save(rdvFuture);

            System.out.println("✅ Données injectées avec succès !");
            System.out.println("👉 Admin : admin / 1234");
            System.out.println("👉 Médecin : dr.alami / 1234");
            System.out.println("👉 Patient : karim@gmail.com / 1234");
        };
    }
}