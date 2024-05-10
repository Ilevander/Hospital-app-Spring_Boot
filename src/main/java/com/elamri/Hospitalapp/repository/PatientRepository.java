package com.elamri.Hospitalapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elamri.Hospitalapp.entities.Patient;

//Interface for business Logic and DAO Layer
public interface PatientRepository extends JpaRepository<Patient, Long>{

}
