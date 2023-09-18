package com.app.japanesepractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.japanesepractice.model.service.EmailSenderService;
import com.app.japanesepractice.model.service.PracticeService;

@Controller
public class PracticeController {

	@Autowired
	PracticeService practiceService;

	@Autowired
	EmailSenderService emailSenderService;

	@GetMapping("/practice")
	public String viewPractice(Model model) {
		return "practice";
	}

	@GetMapping("/practice/practice-test/{level}")
	public String viewPracticeTest(Model model, @PathVariable String level) {
		model.addAttribute("level", level);
		model.addAttribute("practices", practiceService.getPracticesByLevel(level));
		return "practice-test";
	}

	@PostMapping(value = "/practice/practice-test/{level}")
	public String practiceTest(Model model, @PathVariable String level,
			@RequestBody MultiValueMap<String, String> formData) {
		model.addAttribute("level", level);
		model.addAttribute("result", practiceService.calculateResult(formData, level));
		return "practice-test";
	}

}
