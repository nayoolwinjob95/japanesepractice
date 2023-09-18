package com.app.japanesepractice.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.japanesepractice.model.domain.entity.Ebook;
import com.app.japanesepractice.model.service.EbookService;
import com.app.japanesepractice.model.service.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/ebook")
public class EbookController {

	@Autowired
	private EbookService ebookService;

	@Autowired
	private JwtService jwtService;

	@GetMapping
	public String viewEbook(Model model) {
		model.addAttribute("ebooks", ebookService.getEbooks());
		model.addAttribute("levels", Ebook.Level.values());
		return "ebook";
	}

	@GetMapping("/search")
	public String viewSearchEbook(Model model, @RequestParam(name = "bookTitle", required = false) String bookTitle,
			@RequestParam(name = "level", required = false) String level) {
		model.addAttribute("levels", Ebook.Level.values());
		model.addAttribute("ebooks", ebookService.getEbooks(bookTitle, level));
		return "ebook";
	}

	@GetMapping("/download")
	public String viewDownloadEbook(Model model) {
		return "ebook-download";
	}

	@PostMapping("/download")
	public String downloadEbook(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(name = "token", required = false) String token) {
		if (token != null && jwtService.validateToken(token)) {
			int bookId = Integer.valueOf(jwtService.extractBookId(token));
			File dummy = new File("");
			String absolutePath = dummy.getAbsolutePath();

			File file = new File(absolutePath + "\\target\\classes\\static\\pdf\\ebooks\\" + bookId + ".pdf");
			if (file.exists()) {

				String mimeType = URLConnection.guessContentTypeFromName(file.getName());
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}

				response.setContentType(mimeType);

				response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

				response.setContentLength((int) file.length());

				InputStream inputStream;
				try {
					inputStream = new BufferedInputStream(new FileInputStream(file));
					FileCopyUtils.copy(inputStream, response.getOutputStream());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} else {
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			model.addAttribute("invalid", "Invalid Token.");
			return "ebook-download";
		}
		System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuu");

		return "redirect:/ebook";
	}

}
