package com.simonable.java_belt_exam.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simonable.java_belt_exam.models.Idea;

@Repository
public interface IdeaRepository extends CrudRepository<Idea, Long>{
	public List<Idea> findAll();
}
