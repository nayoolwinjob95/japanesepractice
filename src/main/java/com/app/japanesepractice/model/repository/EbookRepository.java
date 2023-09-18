package com.app.japanesepractice.model.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.app.japanesepractice.model.domain.entity.Ebook;

@Mapper
public interface EbookRepository {

	void save(Ebook ebook);

	Optional<Integer> getLastId();

	List<Ebook> getEbooks();

	List<Ebook> getEbooks(@Param("bookTitle") String bookTitle, @Param("level") String level);

	Ebook findOneById(Integer id);

	void update(Ebook ebook);

	void delete(Integer id);
	
	Optional<Integer> getCurrentIdSeq();

	Optional<Ebook> getEbookWithId(int id);

}
