package com.elamri.Hospitalapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.elamri.Hospitalapp.entities.Patient;
import com.elamri.Hospitalapp.repository.PatientRepository;

@Controller
public class PatientController {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@GetMapping("/index")
	public String index(Model model) {
		List<Patient> patients = patientRepository.findAll();
		model.addAttribute("lisPatients",patients);
		return "patients";
	}
	

}  
