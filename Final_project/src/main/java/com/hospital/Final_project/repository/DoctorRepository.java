package com.hospital.Final_project.repository;

import com.hospital.Final_project.model.DoctorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface DoctorRepository extends JpaRepository<DoctorModel, Long> {
//    DoctorModel findByEmail(String email);
//    @Query(value="SELECT role, office, time_table FROM staff", nativeQuery=true)
//    List<DoctorModel> findAllTimeTable();
}
