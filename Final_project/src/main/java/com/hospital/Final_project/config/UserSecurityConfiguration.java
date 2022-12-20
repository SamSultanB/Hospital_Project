package com.hospital.Final_project.config;

import com.hospital.Final_project.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class UserSecurityConfiguration {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationSuccessHandler roleSuccessHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.csrf().disable().authorizeHttpRequests().antMatchers("/api/v1", "/login").permitAll()
                .antMatchers("/home/**").hasAuthority("CLIENT")
                .antMatchers("/doctor-home/**").hasAuthority("DOCTOR")
                .antMatchers("/head-doctor-home/**").hasAuthority("HEAD-DOCTOR");
        return http.build();
    }

}

//"/head-doctor-home/my-information", "/head-doctor-home/staff",
//        "/head-doctor-home/staff/{id}", "/head-doctor-home/patients", "/head-doctor-home/patients/{id}", "/head-doctor-home/patients/{name}"
//                .anyRequest().authenticated().and().formLogin().loginPage("/login").successHandler(roleSuccessHandler).permitAll()
//                .and().logout().invalidateHttpSession(true).clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login?logout").permitAll();