package com.hospital.Final_project.model;


import com.hospital.Final_project.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "patients")
public class PatientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "dateOFbirth", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfbirth;
    @Column(name = "bloodGroup")
    private String bloodGroup;
    @Column(name = "height")
    private String height;
    @Column(name = "weight")
    private String weight;
    @Column(name = "phone", nullable = false)
    private Integer phone;

    @OneToOne(mappedBy = "patientModel")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Collection<IllnessModel> illnesses;

    public PatientModel(){

    }

    public PatientModel(
            String name,
            String surname,
            LocalDate dateOfbirth,
            String bloodGroup,
            String height,
            String weight,
            Integer phone
    )
    {
        this.name = name;
        this.surname = surname;
        this.dateOfbirth = dateOfbirth;
        this.bloodGroup = bloodGroup;
        this.height = height;
        this.weight = weight;
        this.phone = phone;
    }

}
