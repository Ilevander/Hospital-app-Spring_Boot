package com.elamri.Hospitalapp.security.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.elamri.Hospitalapp.security.entities.AppUser;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

	private AccountService accountService;
	//when user enter his username & password , this method is charged
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=  accountService.loadUserByUsername(username);
        if(appUser==null) throw new UsernameNotFoundException(String.format("User %s not found", username));
		
//        List<String> collect = appUser.getRoles().stream().map(u->u.getRole()).collect(Collectors.toList());
        String[] roles = appUser.getRoles().stream().map(u->u.getRole()).toArray(String[]::new);
        UserDetails userDetails = User
				.withUsername(appUser.getUsername())
				.password(appUser.getPassword())
				.roles(roles).build();
        return userDetails;
	}

}
