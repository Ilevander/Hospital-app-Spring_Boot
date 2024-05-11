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
	public String index(Model model , 
			            @RequestParam( name="page" , defaultValue = "0") int page,
			            @RequestParam(name="size" , defaultValue = "5") int size ) {
		Page<Patient> pagePatients = patientRepository.findAll(PageRequest.of(page, size));
		model.addAttribute("listPatients",pagePatients.getContent());
		model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
		model.addAttribute("currentPage",page);
		return "patients";
	}
	
	@GetMapping("/deletePatient")
	public String delete(@RequestParam(name = "id")Long id) {
		patientRepository.deleteById(id); 
		return "redirect:/index";
	}

}  
