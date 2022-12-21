package com.hospital.Final_project.controller;

import com.hospital.Final_project.model.DoctorModel;
import com.hospital.Final_project.model.IllnessModel;
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
    public ResponseEntity<String> updateDoctorInfo(@RequestBody DoctorModel newDoctorModel, Principal principal){
        User user = userService.findByEmail(principal.getName());
        DoctorModel doctorModel = user.getDoctorModel();
        if(doctorModel == null){
            user.setDoctorModel(newDoctorModel);
        }else{
            doctorModel.setName(newDoctorModel.getName());
            doctorModel.setSurname(newDoctorModel.getSurname());
            doctorModel.setRole(newDoctorModel.getRole());
            doctorModel.setOffice(newDoctorModel.getOffice());
            doctorModel.setTimeTable(newDoctorModel.getTimeTable());
            doctorModel.setSalary(newDoctorModel.getSalary());
            doctorModel.setPhone(newDoctorModel.getPhone());
        }
        userService.saveInfo(user);
        return ResponseEntity.ok("Doctor, your information was successfully updated!");
    }

    @GetMapping("/patients")
    public ResponseEntity<Collection<PatientModel>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("patients/{id}")
    public ResponseEntity<PatientModel> getPatient(@PathVariable("id") Long id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping("/patients/{id}")
    public ResponseEntity<String> diagnosPatient(@PathVariable("id") Long id, @RequestBody IllnessModel illnessModel){
        PatientModel patientModel = patientService.getPatientById(id);
        patientModel.getIllnesses().add(illnessModel);
        patientService.savePatient(patientModel);
        return ResponseEntity.ok("Diagnosed successfully!");
    }


}
