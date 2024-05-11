package com.elamri.Hospitalapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elamri.Hospitalapp.entities.Patient;
import com.elamri.Hospitalapp.repository.PatientRepository;

@Controller
public class PatientController {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@GetMapping("/index")
	public String index(Model model , int page , int size) {
		Page<Patient> patients = patientRepository.findAll(PageRequest.of(page, size));
		model.addAttribute("listPatients",patients);
		return "patients";
	}
	
	@GetMapping("/deletePatient")
	public String delete(@RequestParam(name = "id")Long id) {
		patientRepository.deleteById(id); 
		return "redirect:/index";
	}

}  
