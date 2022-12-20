package com.hospital.Final_project.controller;

import com.hospital.Final_project.user.UserDTO;
import com.hospital.Final_project.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/v1")
public class RegistrationController {

    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userRegistration(){
        return "register";
    }
    @PostMapping
    public ResponseEntity<?> userRegistration(@RequestBody UserDTO userDTO){
        userDTO.setRole("CLIENT");
        userService.save(userDTO);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDTO.getEmail(), userDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

}
