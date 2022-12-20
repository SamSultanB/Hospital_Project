package com.hospital.Final_project.controller;

import com.hospital.Final_project.model.IllnessModel;
import com.hospital.Final_project.model.PatientModel;
import com.hospital.Final_project.model.DoctorModel;
import com.hospital.Final_project.service.PatientService;
import com.hospital.Final_project.service.DoctorService;
import com.hospital.Final_project.user.User;
import com.hospital.Final_project.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping("/home")
public class PatientController {
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final UserService userService;

    public PatientController(DoctorService doctorService, PatientService patientService, UserService userService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<String> clientHome(Principal principal){
        return ResponseEntity.ok(principal.getName()+" 1. My information 2.History of my illness 3. Therapies 4.Timetable of doctors");
    }

    @GetMapping("/my-information")
    public ResponseEntity<PatientModel> clientInfo(Principal principal){
        String email = principal.getName();
        PatientModel patientModel = userService.findByEmail(email).getPatientModel();
        return ResponseEntity.ok(patientModel);
    }

    @PostMapping("/my-information")
    public ResponseEntity<String> updateClientInfo(Principal principal, @RequestBody PatientModel patientModel){
        String email = principal.getName();
        User user = userService.findByEmail(email);
        user.setPatientModel(patientModel);
        userService.saveInfo(user);
        return ResponseEntity.ok("Your information was updated successfully");
    }

    @GetMapping("/doctors-timetables")
    public ResponseEntity<Collection<DoctorModel>> getAllDoctorsTimeTable(){
        return ResponseEntity.ok(doctorService.getAllStaff());
    }

    @GetMapping("/doctors")
    public ResponseEntity<Collection<DoctorModel>> allDoctors(){
        return ResponseEntity.ok(doctorService.getAllStaff());
    }

    @GetMapping("/doctors/{id}")
    public ResponseEntity<DoctorModel> getDoctor(@PathVariable("id") Long id){
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @GetMapping("/illnesses")
    public ResponseEntity<Collection<IllnessModel>> getAllIllnesses(Principal principal){
        PatientModel patientModel = (userService.findByEmail(principal.getName())).getPatientModel();
        return ResponseEntity.ok(patientModel.getIllnesses());
    }

}
