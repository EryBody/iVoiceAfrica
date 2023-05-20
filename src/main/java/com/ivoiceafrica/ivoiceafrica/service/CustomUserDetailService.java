package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
		return result;
	}
	
	
	public Optional<User> findFirstUserByUsername(String username) {
		
		Optional<User> result = userRepository.findFirstUserByUsername(username);
		
		return result;
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
	
	public List<User> findUserByRole(int roleId) {
		return userRepository.findUserByRole(roleId);
	}
	
	public List<User> findUserByRoleAndUsername(int roleId, String username){
    	return userRepository.findUserByRoleAndUsername(roleId, username);
    }
	
	public List<User> findUserByRoleAndFirstname(int roleId, String firstname){
    	return userRepository.findUserByRoleAndFirstname(roleId, firstname);
    }
	
	public List<User> findUserByRoleAndLastname(int roleId,String lastname){
    	return userRepository.findUserByRoleAndLastname(roleId, lastname);
    }
	
	public List<User> findUserByRoleAndPhone(int roleId, String phone){
    	return userRepository.findUserByRoleAndPhone(roleId, phone);
    }
	
	public List<User> findUsersByUsername(String username){
		return userRepository.findUsersByUsername(username);
	}
	
	public List<User> findUsersByFirstName(String firstName){
		return userRepository.findUsersByFirstName(firstName);
	}
	
	public List<User> findUsersByLastName(String lastName){
		return userRepository.findUsersByLastName(lastName);
	}
	
	public List<User> findUsersByPhone(String phone){
		return userRepository.findUsersByPhone(phone);
	}
	
	public int updateProfilePicture(String profilePicture, int userId) {
		return userRepository.updateProfilePicture(profilePicture, userId);
	}
	
	public User findUserDetailsByUsername(String username) {
		return userRepository.findUserDetailsByUsername(username);
	}
	
}
