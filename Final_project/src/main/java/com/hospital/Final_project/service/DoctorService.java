package com.hospital.Final_project.service;

import com.hospital.Final_project.model.DoctorModel;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService {

    List<DoctorModel> getAllstuff();

    void saveDoctor(DoctorModel doctorModel);

//    DoctorModel deleteDoctor(DoctorModel doctorModel);

//    List<DoctorModel> getAllTimeTables();

    DoctorModel getDoctorById(Long id);
}
