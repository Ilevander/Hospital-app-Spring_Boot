package com.elamri.Hospitalapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.elamri.Hospitalapp.repository.PatientRepository;

@Controller
public class PatientController {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@GetMapping("/index")
	public String index() {
		return "patients";
	}
	

}  
