package com.elamri.Hospitalapp.security;

import com.elamri.Hospitalapp.security.entities.AppRole;
import com.elamri.Hospitalapp.security.entities.AppUser;

public interface AccountService {
     AppUser addNewUser(String username,String password,String email,String confirmPassword);
     AppRole addNewRole(String role);
     void addRoleToUser(String username,String role);
     void removeRoleFromUser(String username,String role);
}
	