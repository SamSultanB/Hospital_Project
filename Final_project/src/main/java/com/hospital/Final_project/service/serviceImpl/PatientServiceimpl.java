package com.hospital.Final_project.service.serviceImpl;

import com.hospital.Final_project.model.PatientModel;
import com.hospital.Final_project.repository.PatientRepository;
import com.hospital.Final_project.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceimpl implements PatientService {

    private PatientRepository patientRepository;

    public PatientServiceimpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<PatientModel> getAllPatients(){
        return patientRepository.findAll();
    }

    @Override
    public void savePatient(PatientModel patientModel){
        this.patientRepository.save(patientModel);
    }

    @Override
    public PatientModel getPatientById(Long id) {
        return patientRepository.findById(id).get();
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public PatientModel getPatientByName(String name) {
        return patientRepository.findByName(name);
    }


}
