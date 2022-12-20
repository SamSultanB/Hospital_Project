package com.hospital.Final_project.controller;

import com.hospital.Final_project.model.DoctorModel;
import com.hospital.Final_project.model.PatientModel;
import com.hospital.Final_project.service.PatientService;
import com.hospital.Final_project.service.DoctorService;
import com.hospital.Final_project.user.User;
import com.hospital.Final_project.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping("/doctor-home")
public class DoctorController {

    private final UserService userService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public DoctorController(UserService userService, DoctorService doctorService, PatientService patientService) {
        this.userService = userService;
        this.doctorService = doctorService;
        this.patientService = patientService;

    }

    @GetMapping
    public String doctorHomePage(Model model){
        return "Doctor home page";
    }

    @GetMapping("/my-information")
    public ResponseEntity<DoctorModel> doctorInfo(Principal principal){
        DoctorModel doctorModel = (userService.findByEmail(principal.getName())).getDoctorModel();
        return ResponseEntity.ok(doctorModel);
    }

    @PostMapping("/my-information")
    public ResponseEntity<String> updateDoctorInfo(@RequestParam DoctorModel doctorModel, Principal principal){
        User user = userService.findByEmail(principal.getName());
        user.setDoctorModel(doctorModel);
        userService.saveInfo(user);
        return ResponseEntity.ok("Doctor, your information was successfully updated!");
    }

    @GetMapping("/patients")
    public ResponseEntity<Collection<PatientModel>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }




}
