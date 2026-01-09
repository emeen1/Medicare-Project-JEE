package com.example.medicare_projectjee.service;

import com.example.medicare_projectjee.dao.entities.Consultation;
import com.example.medicare_projectjee.dao.entities.Medecin;
import com.example.medicare_projectjee.dao.entities.Patient;
import com.example.medicare_projectjee.dao.entities.RendezVous;
import com.example.medicare_projectjee.dao.repositories.ConsultationRepository;
import com.example.medicare_projectjee.dao.repositories.MedecinRepository;
import com.example.medicare_projectjee.dao.repositories.PatientRepository;
import com.example.medicare_projectjee.dao.repositories.RendezVousRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class HospitalServiceImpl implements IHospitalService {

    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private RendezVousRepository rdvRepository;
    private ConsultationRepository consultationRepository;


    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Medecin> getAllMedecins() {
        return medecinRepository.findAll();
    }
    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }
    @Override
    public void deleteMedecin(Long id) {
        Medecin medecin = medecinRepository.findById(id).orElse(null);
        if (medecin != null) {
            rdvRepository.deleteAll(medecin.getRendezVous());
            medecinRepository.delete(medecin);
        }
    }

    @Override
    public RendezVous saveRDV(RendezVous rdv) {
        //vérifier disponibilité
        return rdvRepository.save(rdv);
    }

    @Override
    public List<RendezVous> getAllRDV() {
        return rdvRepository.findAll();
    }

    @Override
    public void deleteRDV(Long id) {
        rdvRepository.deleteById(id);
    }
    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }
}
