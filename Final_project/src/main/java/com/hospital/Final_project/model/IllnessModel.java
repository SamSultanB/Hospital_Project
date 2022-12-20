package com.hospital.Final_project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "illness")
public class IllnessModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "therapy")
    private String therapy;
    @Column(name = "days")
    private String days;
    @Column(name = "dateOfPublic")
    private LocalDate dateOfPublic;

    public IllnessModel() {
    }

    public IllnessModel(String name, String therapy, String days ,LocalDate dateOfPublic) {
        this.name = name;
        this.therapy = therapy;
        this.days = days;
        this.dateOfPublic = dateOfPublic;
    }
}
