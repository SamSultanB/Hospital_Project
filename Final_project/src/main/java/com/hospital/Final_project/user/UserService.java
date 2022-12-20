package com.hospital.Final_project.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UserService extends UserDetailsService {
    User save(UserDTO userDTO);

    User findByEmail(String email);

    User saveInfo(User user);

}
