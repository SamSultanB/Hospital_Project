package com.hospital.Final_project.repository;

import com.hospital.Final_project.model.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientModel, Long> {
    PatientModel findByName(String name);
}
