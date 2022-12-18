package com.hospital.Final_project.service.serviceImpl;

import com.hospital.Final_project.model.DoctorModel;
import com.hospital.Final_project.repository.DoctorRepository;
import com.hospital.Final_project.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class DoctorServiceimpl implements DoctorService {
    private DoctorRepository doctorRepository;

    private DoctorModel doctorModel;

    public DoctorServiceimpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<DoctorModel> getAllstuff(){
        return doctorRepository.findAll();
    }

    @Override
    public void saveDoctor(DoctorModel doctorModel){
        this.doctorRepository.save(doctorModel);
    }

//    @Override
//    public List<DoctorModel> getAllTimeTables() {
//        return doctorRepository.findAll();
//    }

    @Override
    public DoctorModel getDoctorById(Long id) {
        return doctorRepository.findById(id).get();
    }


}
