package com.simonable.java_belt_exam.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simonable.java_belt_exam.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	public List<User> findAll();
	
	public User findByEmail(String email);
}
