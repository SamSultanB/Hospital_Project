package com.hospital.Final_project.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hospital.Final_project.model.PatientModel;
import com.hospital.Final_project.model.DoctorModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "users_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    private Collection<Role> userRole;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_doctor_id", referencedColumnName = "id")
    @JsonIgnore
    private DoctorModel doctorModel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_patient_id", referencedColumnName = "id")
    @JsonIgnore
    private PatientModel patientModel;

    public User(){

    }

    public User(String email, String password, Collection<Role> userRole) {
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }
}
