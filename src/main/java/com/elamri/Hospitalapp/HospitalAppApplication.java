package com.elamri.Hospitalapp;

import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.elamri.Hospitalapp.entities.Patient;
import com.elamri.Hospitalapp.repository.PatientRepository;

@SpringBootApplication
public class HospitalAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalAppApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner start(PatientRepository patientRepository) {
		return args ->{
			
			//NoArgsContructor
			Patient p1 = new Patient();
			p1.setFirstName("Ilyass");
			p1.setLastName("EL AMRI");
			p1.setScore(120);
			p1.setSick(false);
			p1.setDateOfBirth(new Date());
			patientRepository.save(p1);
			
			//AllArgsContructor
			Patient p2 = new Patient(null, "Yassine" ,"LOUIS", new Date(), 1500, false);
			
			
			//Builder design pattern annotation of Lombok
			Patient p3 = Patient.builder()
					.firstName("Rime")
					.lastName("EL AMRI")
					.dateOfBirth(new Date())
					.build();
			
			//Saving All Instance : 
			patientRepository.save(p1);
			patientRepository.save(p2);
			patientRepository.save(p3);
			
			//Printing in Console:
			List<Patient> patients = patientRepository.findAll();
			patients.forEach(p->{
				System.out.println(p.toString());
			});
			
			
		};
	}
	
	@Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager, PasswordEncoder passwordEncoder) {
        return args -> {
            UserDetails user1 = User.withUsername("user11")
                    .password(passwordEncoder.encode("user11"))
                    .roles("USER")
                    .build();

            UserDetails user2 = User.withUsername("user22")
                    .password(passwordEncoder.encode("user22"))
                    .roles("USER")
                    .build();

            UserDetails admin = User.withUsername("admin2")
                    .password(passwordEncoder.encode("admin2"))
                    .roles("USER", "ADMIN")
                    .build();

            if (!jdbcUserDetailsManager.userExists(user1.getUsername())) {
                jdbcUserDetailsManager.createUser(user1);
            }
            if (!jdbcUserDetailsManager.userExists(user2.getUsername())) {
                jdbcUserDetailsManager.createUser(user2);
            }
            if (!jdbcUserDetailsManager.userExists(admin.getUsername())) {
                jdbcUserDetailsManager.createUser(admin);
            }
        };
    }  
	
	
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
