package com.example.medicare_projectjee.dao.repositories;

import com.example.medicare_projectjee.dao.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findByMedecinUsername(String username);
    List<RendezVous> findByPatientUsername(String username);

}
