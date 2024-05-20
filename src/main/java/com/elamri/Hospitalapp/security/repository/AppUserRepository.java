package com.elamri.Hospitalapp.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elamri.Hospitalapp.security.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {

	AppUser finByUsername(String username);
}
