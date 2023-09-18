package com.app.japanesepractice.model.domain.entity;

import java.util.Date;

import com.app.japanesepractice.model.domain.form.EbookForm;

import lombok.Data;

@Data
public class Ebook {
	private int id;
	private String bookTitle;
	private String bookTitleKana;
	private Level level;
	private Date createdAt;
	private Date updatedAt;

	public Ebook() {
	}

	public Ebook(EbookForm ebookForm) {
		if (ebookForm.getId() != null) {
			this.id = Integer.valueOf(ebookForm.getId());
		}
		this.bookTitle = ebookForm.getBookTitle();
		this.bookTitleKana = ebookForm.getBookTitleKana();
		this.level = Level.valueOf(ebookForm.getLevel());
	}

	public enum Level {
		N1, N2, N3, N4, N5
	}
}
