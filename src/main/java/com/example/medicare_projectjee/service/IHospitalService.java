package com.example.medicare_projectjee.service;

import com.example.medicare_projectjee.dao.entities.*;

import java.util.List;

public interface IHospitalService {
    // Gestion Patients
    Patient savePatient(Patient patient);
    List<Patient> getAllPatients();
    void deletePatient(Long id);

    // Gestion Médecins
    List<Medecin> getAllMedecins();
    Medecin saveMedecin(Medecin medecin);
    void deleteMedecin(Long id);

    // Gestion Rendez-Vous
    RendezVous saveRDV(RendezVous rdv);
    List<RendezVous> getAllRDV();
    void deleteRDV(Long id);
    Consultation saveConsultation(Consultation consultation);
}
