package com.hospital.Final_project.controller;


import com.hospital.Final_project.model.Appointment;
import com.hospital.Final_project.model.ClientModel;
import com.hospital.Final_project.model.DoctorModel;
import com.hospital.Final_project.service.ClientService;
import com.hospital.Final_project.service.DoctorService;
import com.hospital.Final_project.user.User;
import com.hospital.Final_project.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping("/home")
public class ClientController {
    private final DoctorService doctorService;
    private final ClientService clientService;
    private final UserService userService;

    public ClientController(DoctorService doctorService, ClientService clientService, UserService userService) {
        this.doctorService = doctorService;
        this.clientService = clientService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<String> clientHome(Principal principal){
        return ResponseEntity.ok(principal.getName()+" 1. My information 2.History of my illness 3. Therapies 4.Timetable of doctors");
    }

    @GetMapping("/my-information")
    public ResponseEntity<ClientModel> clientInfo(Principal principal){
        String email = principal.getName();
        ClientModel clientModel = userService.findByEmail(email).getClientModel();
        return ResponseEntity.ok(clientModel);
    }

    @PostMapping("/my-information")
    public ResponseEntity<String> updateClientInfo(Principal principal, @RequestBody ClientModel clientModel){
        String email = principal.getName();
        User user = userService.findByEmail(email);
        user.setClientModel(clientModel);
        userService.saveInfo(user);
        return ResponseEntity.ok("Your information was updated successfully");
    }

    @GetMapping("/doctors-timetables")
    public Collection<DoctorModel> getAllDoctorsTimeTable(){
        return doctorService.getAllstuff();
    }

    @GetMapping("/history-of-illness")
    public Collection<Appointment> history(Principal principal){
        User user = userService.findByEmail(principal.getName());
        return user.getClientModel().getAppointments();
    }

    @GetMapping("/doctors")
    public Collection<DoctorModel> allDoctors(){
        return doctorService.getAllstuff();
    }

    @GetMapping("/doctors/{id}")
    public ResponseEntity<DoctorModel> getDoctor(@PathVariable("id") Long id){
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @PostMapping("/doctors/{id}")
    public ResponseEntity<String> getAppointment(@RequestBody Appointment appointment, @PathVariable("id") Long id, Principal principal){
        User user = userService.findByEmail(principal.getName());
        user.getClientModel().getAppointments().add(appointment);
        userService.saveInfo(user);
        DoctorModel doctorModel = doctorService.getDoctorById(id);
        doctorModel.getDoctorAppointments().add(appointment);
        doctorService.saveDoctor(doctorModel);
        return ResponseEntity.ok("You appointed successfully!");
    }

}
