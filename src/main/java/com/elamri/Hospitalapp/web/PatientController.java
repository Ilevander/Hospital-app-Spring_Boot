package com.elamri.Hospitalapp.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
			            @RequestParam(name="size" , defaultValue = "5") int size,
			            @RequestParam(name="keyword" , defaultValue = "" ) String keyword) {
		Page<Patient> pagePatients = patientRepository.findByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(keyword,keyword,PageRequest.of(page, size));
		model.addAttribute("listPatients",pagePatients.getContent());
		model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
		model.addAttribute("currentPage",page);  
		model.addAttribute("keyword",keyword);
		return "patients";
	}
	
	@GetMapping("/deletePatient")
	public String delete(@RequestParam(name = "id") Long id, @RequestParam(name = "keyword") String keyword, @RequestParam(name = "page") int page) {
	    patientRepository.deleteById(id);
	    return "redirect:/index?page=" + page + "&keyword=" + keyword;
	}
	
	@GetMapping("/formPatient")
	public String formPatient(Model model) {
		model.addAttribute("patient",new Patient());
		return "formPatient";
	}
	

}  
