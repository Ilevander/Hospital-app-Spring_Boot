package com.elamri.Hospitalapp.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elamri.Hospitalapp.security.entities.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, String>{

}
