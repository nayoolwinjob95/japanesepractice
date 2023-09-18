package com.app.japanesepractice.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.app.japanesepractice.model.domain.entity.Practice;

@Mapper
public interface PracticeRepository {

	void save(Practice practice);

	List<Practice> getPractices();

	List<Practice> getPractices(@Param("question") String question, @Param("level") String level);

	void delete(Integer id);

	Practice findOneById(Integer id);

	void update(Practice practice);

}
