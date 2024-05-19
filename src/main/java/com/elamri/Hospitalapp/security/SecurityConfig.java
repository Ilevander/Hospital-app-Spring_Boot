package com.elamri.Hospitalapp.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
    	return new JdbcUserDetailsManager(dataSource);
    }
    
//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        return new InMemoryUserDetailsManager(
//                User.withUsername("user1").password(passwordEncoder.encode("user1")).roles("USER").build(),
//                User.withUsername("user2").password(passwordEncoder.encode("user2")).roles("USER").build(),
//                User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("USER","ADMIN").build()
//        );
//    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
            .and()
            .rememberMe()
            .and()
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/webjars/**", "/h2-console/**").permitAll()
                .requestMatchers("/user/**").hasRole("USER")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .exceptionHandling().accessDeniedPage("/notAuthorized");
        return httpSecurity.build();
    }

}
