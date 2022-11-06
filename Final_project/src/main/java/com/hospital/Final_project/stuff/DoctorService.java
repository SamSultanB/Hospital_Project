package com.hospital.Final_project.stuff;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService {

    public List<DoctorModel> getAllstuff();

    DoctorModel saveDoctor(DoctorModel doctorModel);

//    DoctorModel deleteDoctor(DoctorModel doctorModel);

    DoctorModel getDoctorById(Long id);
}
