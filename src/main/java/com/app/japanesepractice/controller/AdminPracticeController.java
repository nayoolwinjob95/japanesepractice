package com.app.japanesepractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.japanesepractice.model.domain.entity.Practice;
import com.app.japanesepractice.model.domain.form.PracticeForm;
import com.app.japanesepractice.model.service.PracticeService;
import com.app.japanesepractice.model.service.ResultService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminPracticeController {

	@Autowired
	PracticeService practiceService;

	@Autowired
	ResultService resultService;

	@GetMapping("/practice")
	public String viewAdminPractice(Model model) {
		model.addAttribute("levels", Practice.Level.values());
		model.addAttribute("practices", practiceService.getPractices());
		return "admin/practice";
	}

	@GetMapping("/practice/result")
	public String viewAdminPracticeResult(Model model) {
		model.addAttribute("levels", Practice.Level.values());
		model.addAttribute("results", resultService.getResults());
		return "admin/practice/result";
	}

	@GetMapping("/practice/search")
	public String viewSearchAdminPractice(Model model,
			@RequestParam(name = "question", required = false) String question,
			@RequestParam(name = "level", required = false) String level) {
		model.addAttribute("levels", Practice.Level.values());
		model.addAttribute("practices", practiceService.getPractices(question, level));
		return "admin/practice";
	}

	@GetMapping("/practice/result/search")
	public String viewSearchAdminPracticeResult(Model model,
			@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "level", required = false) String level) {
		model.addAttribute("levels", Practice.Level.values());
		model.addAttribute("results", resultService.getResults(username, level));
		return "admin/practice/result";
	}

	@GetMapping("/practice/register")
	public String viewRegisterAdminPractice(Model model) {
		model.addAttribute("form", new PracticeForm());
		model.addAttribute("levels", Practice.Level.values());
		return "admin/practice/register";
	}

	@PostMapping("/practice/register")
	public String registerAdminPractice(Model model, @Valid @ModelAttribute("form") PracticeForm practiceForm,
			BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("levels", Practice.Level.values());
			return "admin/practice/register";
		}
		practiceService.save(practiceForm);

		return "redirect:/admin/practice";
	}

	@GetMapping("/practice/update/{id}")
	public String viewUpdateAdminPractice(Model model, @PathVariable Integer id) {
		model.addAttribute("form", new PracticeForm(practiceService.findOneById(id)));
		model.addAttribute("levels", Practice.Level.values());
		return "admin/practice/update";
	}

	@PostMapping("/practice/update/{id}")
	public String updateAdminPractice(Model model, @PathVariable Integer id,
			@Valid @ModelAttribute("form") PracticeForm practiceForm, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("levels", Practice.Level.values());
			return "admin/practice/update";
		}
		practiceService.update(practiceForm);

		return "redirect:/admin/practice";
	}

	@GetMapping("/practice/delete/{id}")
	public String deleteAdminPractice(Model model, @PathVariable Integer id) {
		practiceService.delete(id);
		return "redirect:/admin/practice";
	}

}
