package com.app.japanesepractice.model.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import com.app.japanesepractice.model.domain.entity.Practice;
import com.app.japanesepractice.model.domain.form.PracticeForm;
import com.app.japanesepractice.model.repository.PracticeRepository;

import jakarta.validation.Valid;

@Service
public class PracticeService {

	@Autowired
	private PracticeRepository practiceRepository;

	@Autowired
	private ResultService resultService;

	@Transactional
	public void save(PracticeForm practiceForm) {
		Practice practice = new Practice(practiceForm);
		practiceRepository.save(practice);
	}

	public List<Practice> getPractices() {
		return practiceRepository.getPractices();
	}

	public List<Practice> getPractices(String question, String level) {
		return practiceRepository.getPractices(question, level);
	}

	public void delete(Integer id) {
		practiceRepository.delete(id);
	}

	public Practice findOneById(Integer id) {
		return practiceRepository.findOneById(id);
	}

	@Transactional
	public void update(@Valid PracticeForm practiceForm) {
		Practice practice = new Practice(practiceForm);
		practiceRepository.update(practice);
	}

	public List<Practice> getPracticesByLevel(String level) {
		List<Practice> practices = practiceRepository.getPractices(null, level);
		Collections.shuffle(practices);

		if (practices.size() > 10) {
			practices = practices.subList(0, 10);
		}

		List<String> choices;
		for (Practice practice : practices) {
			choices = Arrays.asList(practice.getChoices().split(","));
			Collections.shuffle(choices);
			practice.setChoices(String.join(",", choices));
		}

		return practices;
	}

	@Transactional
	@PreAuthorize("authenticated()")
	public int calculateResult(MultiValueMap<String, String> formData, String level) {
		Practice practice;
		double correct = 0;

		for (String key : formData.keySet()) {
			if (!key.equals("_csrf")) {
				practice = practiceRepository.findOneById(Integer.valueOf(key));
				if (practice.getAnswer().equals(formData.get(key).toString().replaceAll("[\\[\\]]", ""))) {
					correct += 1;
				}
			}
		}

		double questionCount = formData.size() - 1;
		int total = this.getResultTotal(correct, questionCount);

		var email = SecurityContextHolder.getContext().getAuthentication().getName();
		resultService.save(total, level, email);

		return total;
	}

	public int getResultTotal(double correct, double questionCount) {
		return (int) ((correct / questionCount) * 100);
	}

}
