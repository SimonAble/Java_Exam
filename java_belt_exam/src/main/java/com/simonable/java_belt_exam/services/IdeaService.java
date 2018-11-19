package com.simonable.java_belt_exam.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.simonable.java_belt_exam.models.Idea;
import com.simonable.java_belt_exam.repositories.IdeaRepository;

@Service
public class IdeaService {
	
	private final IdeaRepository ideaRepository;

	public IdeaService(IdeaRepository ideaRepository) {
		this.ideaRepository = ideaRepository;
	}

//	<<---------------Create--------------->>
	public Idea createIdea(Idea i) {
		return ideaRepository.save(i);
	}
	
//	<<---------------Read--------------->>
	public List<Idea> findAllIdeas() {
		return ideaRepository.findAll();
	}
	
	public Idea findIdeaById(Long id) {
		return ideaRepository.findById(id).get();
	}
	
//	<<---------------Update--------------->>
	public Idea updateIdea(Idea i) {
		return ideaRepository.save(i);
	}
	
//	<<---------------Destroy--------------->>
	public void deleteIdea(Idea i) {
		ideaRepository.delete(i);
	}
}
