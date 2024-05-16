package com.elamri.Hospitalapp;

import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
	
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
