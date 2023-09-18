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

import com.app.japanesepractice.model.domain.entity.Ebook;
import com.app.japanesepractice.model.domain.form.EbookForm;
import com.app.japanesepractice.model.service.EbookService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminEbookController {

	@Autowired
	private EbookService ebookService;

	@GetMapping("/ebook")
	public String viewAdminEbook(Model model) {
		model.addAttribute("levels", Ebook.Level.values());
		model.addAttribute("ebooks", ebookService.getEbooks());
		return "admin/ebook";
	}

	@GetMapping("/ebook/search")
	public String viewSearchAdminEbook(Model model,
			@RequestParam(name = "bookTitle", required = false) String bookTitle,
			@RequestParam(name = "level", required = false) String level) {
		model.addAttribute("levels", Ebook.Level.values());
		model.addAttribute("ebooks", ebookService.getEbooks(bookTitle, level));
		return "admin/ebook";
	}

	@GetMapping("/ebook/register")
	public String viewRegisterAdminEbook(Model model) {
		model.addAttribute("form", new EbookForm());
		model.addAttribute("levels", Ebook.Level.values());
		return "admin/ebook/register";
	}

	@PostMapping("/ebook/register")
	public String registerAdminEbook(Model model, @Valid @ModelAttribute("form") EbookForm ebookForm,
			BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("levels", Ebook.Level.values());
			return "admin/ebook/register";
		}
		ebookService.save(ebookForm);

		return "redirect:/admin/ebook";
	}

	@GetMapping("/ebook/update/{id}")
	public String viewUpdateAdminEbook(Model model, @PathVariable Integer id) {
		model.addAttribute("form", ebookService.findOneById(id));
		model.addAttribute("levels", Ebook.Level.values());
		return "admin/ebook/update";
	}

	@PostMapping("/ebook/update/{id}")
	public String updateAdminEbook(Model model, @PathVariable Integer id,
			@Valid @ModelAttribute("form") EbookForm ebookForm, BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("form", ebookService.findOneById(id));
			model.addAttribute("levels", Ebook.Level.values());
			return "admin/ebook/update";
		}
		ebookService.update(ebookForm);

		return "redirect:/admin/ebook";
	}

	@GetMapping("/ebook/delete/{id}")
	public String deleteAdminEbook(Model model, @PathVariable Integer id) {
		ebookService.delete(id);
		return "redirect:/admin/ebook";
	}

}
