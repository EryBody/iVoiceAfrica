package com.ivoiceafrica.ivoiceafrica.auth.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users_status")
public class UserStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_status_id")
	private Integer userStatusId;
	
	@Column(name="user_status",nullable = false, unique = true)
	@NotEmpty
	private String userStatus;
	
	// Mapping to the other user_status table
	@OneToMany(mappedBy = "userStatus",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
    private Set<User> user;
	
	public UserStatus() {
		
	}

	public UserStatus(Integer userStatusId, @NotEmpty String userStatus, Set<User> user) {
		this.userStatusId = userStatusId;
		this.userStatus = userStatus;
		this.user = user;
	}

	public Integer getUserStatusId() {
		return userStatusId;
	}

	public void setUserStatusId(Integer userStatusId) {
		this.userStatusId = userStatusId;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserStatus [userStatusId=" + userStatusId + ", userStatus=" + userStatus+"]";
	}

	
	
}
