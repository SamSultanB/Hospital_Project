package com.hospital.Final_project.controller;

import com.hospital.Final_project.model.DoctorModel;
import com.hospital.Final_project.model.PatientModel;
import com.hospital.Final_project.service.PatientService;
import com.hospital.Final_project.service.DoctorService;
import com.hospital.Final_project.user.User;
import com.hospital.Final_project.user.UserDTO;
import com.hospital.Final_project.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping(path = "/head-doctor-home")
public class HeadDoctorController {

    private final DoctorService doctorService;
    private final PatientService patientService;
    private  final UserService userService;

    public HeadDoctorController(DoctorService doctorService, PatientService patientService, UserService userService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
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

    @GetMapping("/staff")
    public ResponseEntity<Collection<DoctorModel>> getAllStaff(){
        return ResponseEntity.ok(doctorService.getAllStaff());
    }

    @PostMapping("/staff")
    public ResponseEntity<String> addDoctor(@RequestBody UserDTO userDTO){
        userDTO.setRole("DOCTOR");
        userService.save(userDTO);
        return ResponseEntity.ok("You have successfully added new doctor!");
    }

    @GetMapping("/staff/{id}")
    public ResponseEntity<DoctorModel> getDoctor(@PathVariable("id") Long id){
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @DeleteMapping("/staff/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable("id") Long id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Doctor deleted successfully!");
    }

    @GetMapping("/patients")
    public ResponseEntity<Collection<PatientModel>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/patients/{name}")
    public ResponseEntity<PatientModel> getPatientByName(@PathVariable("name") String name){
        return ResponseEntity.ok(patientService.getPatientByName(name));
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<PatientModel> getPatient(@PathVariable("id") Long id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable("id") Long id){
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully!");
    }

}
