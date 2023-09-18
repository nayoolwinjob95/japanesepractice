package com.app.japanesepractice.model.domain.form;

import org.springframework.web.multipart.MultipartFile;

import com.app.japanesepractice.model.domain.entity.Ebook;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EbookForm {
	private String id;
	@NotBlank(message = "Enter book title.")
	private String bookTitle;
	@NotBlank(message = "Enter book title kana.")
	private String bookTitleKana;
	@NotBlank(message = "Choose level.")
	private String level;
	private MultipartFile bookImage;
	private MultipartFile bookPdf;

	public EbookForm() {
	}

	public EbookForm(Ebook ebook) {
		this.id = String.valueOf(ebook.getId());
		this.bookTitle = ebook.getBookTitle();
		this.bookTitleKana = ebook.getBookTitleKana();
		this.level = ebook.getLevel().name();
	}
}
