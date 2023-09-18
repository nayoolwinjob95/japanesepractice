package com.app.japanesepractice.model.domain.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ResultVo {
	private String id;
	private String username;
	private String result;
	private String level;
	private Date createdAt;
}
