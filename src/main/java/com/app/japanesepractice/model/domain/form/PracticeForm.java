package com.app.japanesepractice.model.domain.form;

import com.app.japanesepractice.annotation.NotEnoughChoices;
import com.app.japanesepractice.model.domain.entity.Practice;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PracticeForm {
	private String id;
	@NotBlank(message = "Enter question.")
	private String question;
	@NotEnoughChoices(message = "There must be 4 choices.")
	private String choices;
	@NotBlank(message = "Enter answer.")
	private String answer;
	@NotBlank(message = "Enter level.")
	private String level;

	public PracticeForm() {
	}

	public PracticeForm(Practice practice) {
		this.id = String.valueOf(practice.getId());
		this.question = practice.getQuestion();
		this.choices = practice.getChoices();
		this.answer = practice.getAnswer();
		this.level = practice.getLevel().name();
	}
}
