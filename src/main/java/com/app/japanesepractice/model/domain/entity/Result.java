package com.app.japanesepractice.model.domain.entity;

import java.util.Date;

import com.app.japanesepractice.model.domain.entity.Practice.Level;

import lombok.Data;

@Data
public class Result {
	private int id;
	private int userId;
	private int result;
	private Level level;
	private Date createdAt;

	public Result() {
	}

	public Result(int result, String level) {
		this.result = result;
		this.level = Level.valueOf(level);
	}
}
