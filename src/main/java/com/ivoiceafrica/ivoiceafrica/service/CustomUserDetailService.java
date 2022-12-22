package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.DTO.ProfileDTO;
import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> user = userRepository.findUserByUsername(username);
		user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		System.out.println("===>>>User Information: "+user);
		
		return user.map(CustomUserDetail::new).get();
	}
	
	public Optional<User> findUserByUsername(String username){
		return userRepository.findUserByUsername(username);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public void save(User theRole) {
		userRepository.save(theRole);
	}

	public void deleteById(int theId) {
		userRepository.deleteById(theId);
	}

	public List<User> findAllByOrderByCreatedDateDesc() {
		return userRepository.findAllByOrderByCreatedDateDesc();
	}

	
	public Optional<User> findById(int theId) {
		
		Optional<User> result = userRepository.findById(theId);
		
		if(result.isPresent()) {
			return result;
		}else {
			//we didn't fing the Role
			throw new RuntimeException("Did not find employee id - "+theId);
		}
	}
	
	
	public Optional<User> findFirstUserByUsername(String username) {
		
		Optional<User> result = userRepository.findFirstUserByUsername(username);
		
		if(result.isPresent()) {
			return result;
		}else {
			//we didn't fing the Role
			throw new RuntimeException("Did not find employee id - "+username);
		}
	}
	
	public int updateUserStatus(int userStatus, int userId){
		return userRepository.updateUserStatus(userStatus, userId);
	}
	
	public int updateUserInfo(ProfileDTO profileDTO){
		return userRepository.updateUserInfo(profileDTO);
	}
	
	public int updatePassword(String hashPassword, int userId){
		return userRepository.updatePassword(hashPassword, userId);
	}
	
	public Optional<User> findFirstUserByUsernameAndUpassword(String userName, String password){
		return userRepository.findFirstUserByUsernameAndUpassword(userName, password);
	}
	
}
