package com.app.japanesepractice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.japanesepractice.model.service.PracticeService;

@SpringBootTest
class JapanesepracticeApplicationTests {

	@DisplayName("Test for calculating total result mark.")
	@Test
	void givenCorrectAnswerCountAndTotalQuestionCount_callCalculateTestResult_returnTotalMark() {
		PracticeService practiceService = new PracticeService();
		assertEquals(75, practiceService.getResultTotal(6, 8));
	}

	@DisplayName("Test for calculating total result mark with all wrong answers.")
	@Test
	void givenZeroCorrectAnswerCountAndTotalQuestionCount_callCalculateTestResult_returnTotalMark() {
		PracticeService practiceService = new PracticeService();
		assertEquals(0, practiceService.getResultTotal(0, 8));
	}

	@DisplayName("Test for calculating total result mark with zero case.")
	@Test
	void givenZeroCorrectAnswerCountAndZeroTotalQuestionCount_callCalculateTestResult_returnTotalMark() {
		PracticeService practiceService = new PracticeService();
		assertEquals(0, practiceService.getResultTotal(0, 0));
	}

}
