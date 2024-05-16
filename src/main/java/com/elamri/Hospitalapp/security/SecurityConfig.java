package com.elamri.Hospitalapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
	
	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		return new InMemoryUserDetailsManager(
				//noop to not compare passwords to PasswordEncoder
				User.withUsername("user1").password("{noop}123").roles("USER").build(),
				User.withUsername("user2").password("{noop}123").roles("USER").build(),
				User.withUsername("admin").password("{noop}123").roles("USER","ADMIN").build()
				);
	}
	
	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.formLogin();
		httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
		return httpSecurity.build();
	}

}
