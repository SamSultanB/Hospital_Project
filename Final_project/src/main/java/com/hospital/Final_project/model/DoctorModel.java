package com.hospital.Final_project.model;

import com.hospital.Final_project.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "staff")
public class DoctorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Collection<Appointment> doctorAppointments;

    public DoctorModel() {
    }

    public DoctorModel(
            String role,
            String office,
            String timeTable,
            String salary,
            String phone
    ) {
        this.role = role;
        this.office = office;
        this.timeTable = timeTable;
        this.salary =salary;
        this.phone = phone;
    }

}
