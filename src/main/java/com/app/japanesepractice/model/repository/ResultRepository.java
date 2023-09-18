package com.app.japanesepractice.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.app.japanesepractice.model.domain.entity.Result;
import com.app.japanesepractice.model.domain.vo.ResultVo;

@Mapper
public interface ResultRepository {

	void save(@Param("result") Result result, @Param("email") String email);

	List<ResultVo> getResults();

	List<ResultVo> getResults(@Param("username") String username, @Param("level") String level);

	void deleteByUserId(int userId);

}
