package com.simonable.java_belt_exam.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.simonable.java_belt_exam.models.User;
import com.simonable.java_belt_exam.repositories.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

//	<<---------------Create--------------->>
	public User createUser(User u) {
		String hashed = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
		u.setPassword(hashed);
		return userRepository.save(u);
	}
	
//	<<---------------Read--------------->>
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
//	<<---------------Update--------------->>
	public User updateUser(User u) {
		return userRepository.save(u);
	}
	
//	<<---------------Destroy--------------->>
	public void deleteUser(User u) {
		userRepository.delete(u);
	}

//	<<---------------User Authentication--------------->>	
	
	public boolean authenticateUser(String email, String password) {
		User u = userRepository.findByEmail(email);
		
		if(u == null) {
			return false;
		}
		
		else {
			if(BCrypt.checkpw(password, u.getPassword())) {
				return true;
			}
			else {
				return false;
			}
		}
	}
}
