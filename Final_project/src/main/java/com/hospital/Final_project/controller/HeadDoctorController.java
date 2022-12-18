package com.hospital.Final_project.controller;

import com.hospital.Final_project.model.DoctorModel;
import com.hospital.Final_project.service.ClientService;
import com.hospital.Final_project.service.DoctorService;
import com.hospital.Final_project.user.User;
import com.hospital.Final_project.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(path = "/head-doctor-home")
public class HeadDoctorController {

    private final DoctorService doctorService;
    private final ClientService clientService;
    private  final UserService userService;

    public HeadDoctorController(DoctorService doctorService, ClientService clientService, UserService userService) {
        this.doctorService = doctorService;
        this.clientService = clientService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("1. List of doctors 2. List of clients");
    }

    @GetMapping("/my-information")
    public ResponseEntity<DoctorModel> headDoctorInfo(Principal principal){
        DoctorModel doctorModel = userService.findByEmail(principal.getName()).getDoctorModel();
        return ResponseEntity.ok(doctorModel);
    }

    @PostMapping("/my-information")
    public ResponseEntity<String> updateHeadDoctorInfo(@RequestBody DoctorModel doctorModel, Principal principal){
        User user = userService.findByEmail(principal.getName());
        user.setDoctorModel(doctorModel);
        userService.saveInfo(user);
        return ResponseEntity.ok("Your information was updated successfully!");
    }

}
