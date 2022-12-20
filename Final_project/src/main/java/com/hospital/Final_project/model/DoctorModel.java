package com.hospital.Final_project.model;

import com.hospital.Final_project.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "staff")
public class DoctorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "office", nullable = false)
    private String office;
    @Column(name = "timeTable")
    private String timeTable;
    @Column(name = "salary")
    private String salary;
    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToOne(mappedBy = "doctorModel")
    private User user;

    public DoctorModel() {
    }

    public DoctorModel(
            String name,
            String surname,
            String role,
            String office,
            String timeTable,
            String salary,
            String phone
    ) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.office = office;
        this.timeTable = timeTable;
        this.salary =salary;
        this.phone = phone;
    }

}
