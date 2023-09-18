package com.app.japanesepractice.model.domain.entity;

import com.app.japanesepractice.model.domain.form.PracticeForm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Practice {
	private int id;
	private String question;
	private String choices;
	private String answer;
	private Level level;

	public Practice() {
	}

	public Practice(PracticeForm practiceForm) {
		if (practiceForm.getId() != null) {
			this.id = Integer.valueOf(practiceForm.getId());
		}
		this.question = practiceForm.getQuestion();
		this.choices = practiceForm.getChoices();
		this.answer = practiceForm.getAnswer();
		this.level = Level.valueOf(practiceForm.getLevel());
	}

	public enum Level {
		N1, N2, N3, N4, N5
	}
}
