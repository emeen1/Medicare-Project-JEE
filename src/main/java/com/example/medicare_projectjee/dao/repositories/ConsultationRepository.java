package com.example.medicare_projectjee.dao.repositories;

import com.example.medicare_projectjee.dao.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
