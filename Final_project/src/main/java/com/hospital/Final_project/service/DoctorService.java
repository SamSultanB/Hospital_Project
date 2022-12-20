package com.hospital.Final_project.service;

import com.hospital.Final_project.model.DoctorModel;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.Collection;
import java.util.List;

@Service
public interface DoctorService {

    Collection<DoctorModel> getAllStaff();

    void saveDoctor(DoctorModel doctorModel);

    void deleteDoctor(Long id);

//    DoctorModel deleteDoctor(DoctorModel doctorModel);

//    List<DoctorModel> getAllTimeTables();

    DoctorModel getDoctorById(Long id);
}
