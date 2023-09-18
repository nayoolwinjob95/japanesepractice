package com.app.japanesepractice.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.japanesepractice.model.domain.entity.Result;
import com.app.japanesepractice.model.domain.vo.ResultVo;
import com.app.japanesepractice.model.repository.ResultRepository;

@Service
public class ResultService {

	@Autowired
	private ResultRepository resultRepository;

	public void save(int total, String level, String email) {
		Result result = new Result(total, level);
		resultRepository.save(result, email);
	}

	public List<ResultVo> getResults() {
		return resultRepository.getResults();
	}

	public List<ResultVo> getResults(String username, String level) {
		return resultRepository.getResults(username, level);
	}
	
	public void deleteByUserId(int userId) {
		resultRepository.deleteByUserId(userId);
	}

}
