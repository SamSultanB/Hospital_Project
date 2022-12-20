package com.hospital.Final_project.service;

import com.hospital.Final_project.model.PatientModel;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface PatientService {

    List<PatientModel> getAllPatients();

    void savePatient(PatientModel patientModel);

    PatientModel getPatientById(Long id);

    void deletePatient(Long id);

    PatientModel getPatientByName(String name);


}
