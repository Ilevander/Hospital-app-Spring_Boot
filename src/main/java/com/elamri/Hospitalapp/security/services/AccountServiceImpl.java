package com.elamri.Hospitalapp.security.services;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elamri.Hospitalapp.security.entities.AppRole;
import com.elamri.Hospitalapp.security.entities.AppUser;
import com.elamri.Hospitalapp.security.repository.AppRoleRepository;
import com.elamri.Hospitalapp.security.repository.AppUserRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{

	//DI By Constructor (@AllArgsContructor) 
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;
    
	@Override
	public AppUser addNewUser(String username, String password, String email, String confirmPassword) {
		AppUser appUser = appUserRepository.findByUsername(username);
		if(appUser!=null) throw new RuntimeException("This User Already Exist");
		if(!password.equals(confirmPassword)) throw new RuntimeException("Password Not match");
		appUser = AppUser.builder()
				.userId(UUID.randomUUID().toString())
				.username(username)
				.password(passwordEncoder.encode(password))
				.email(email)
				.build();
		AppUser savedAppUser = appUserRepository.save(appUser);
		return savedAppUser;
	}

	@Override
	public AppRole addNewRole(String role) {
		AppRole appRole = appRoleRepository.findById(role).orElse(null);
		if(appRole!=null) throw new RuntimeException("This role Already Exist !");
		appRole=AppRole.builder()
				.role(role)
				.build();
		return appRoleRepository.save(appRole);
	}

	@Override
	public void addRoleToUser(String username, String role) {
		AppUser appUser = appUserRepository.findByUsername(username);
		AppRole appRole = appRoleRepository.findById(role).get();
		appUser.getRoles().add(appRole);
	}

	@Override
	public void removeRoleFromUser(String username, String role) {
		AppUser appUser = appUserRepository.findByUsername(username);
		AppRole appRole = appRoleRepository.findById(role).get();
		appUser.getRoles().remove(appRole);
	}

	@Override
	public AppUser loadUserByUsername(String username) {
        
		return appUserRepository.findByUsername(username);
	}

}
