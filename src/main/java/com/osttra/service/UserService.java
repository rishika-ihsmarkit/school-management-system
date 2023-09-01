package com.osttra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osttra.repository.UserRepository;
import com.osttra.to.User;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public void register(User user) {
		
		userRepository.addUser(user);
		
	}

	public User login(String userid, String password) {
		
		User user = userRepository.getUser(userid, password);
		
		if(user==null) {
			
			User wrongUser = userRepository.getUser(userid);
			
			if (wrongUser!=null) {
				if(wrongUser.getAttempts()>=2) {
					userRepository.blockTheUser(wrongUser.getUserid());
				}else {
					userRepository.wrongAttempt(wrongUser);
				}
			}
			
			
			
		}else {
			userRepository.rightAttempt(user);
		}
		
		return user;
		
	}

	public void delete(String userid) {
		
		userRepository.deleteUser(userid);
		
	}

	public void update(User user) {
		
		userRepository.update(user);
	}

	public List<User> allUsers() {

		List<User> userList = userRepository.getAllUser();

		return userList;
	}

	public void blockUser(String userid) {

		userRepository.blockTheUser(userid);

	}

	public void activateUser(String userid) {

		userRepository.activateTheUser(userid);

	}

public User getUser(String userid) {
		
		User user = userRepository.getUser(userid);
		
		return user;
	}
}
