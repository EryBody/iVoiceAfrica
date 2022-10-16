package com.ivoiceafrica.ivoiceafrica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;

@Entity
@Table(name = "freelancers")
public class Freelancer {

	@Id
    @GenericGenerator(name = "freelancer_id", strategy = "com.ivoiceafrica.ivoiceafrica.IdGenerator.FreelancerGenerator")
    @GeneratedValue(generator = "freelancer_id", strategy = GenerationType.SEQUENCE)  
    @Column(name="freelancer_id")
	private String freelancerId;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "user_id")
    private User user;
	
	@Column(name = "other_info")
	private String otherInfo;
	
	public Freelancer() {
		
	}
	

	public Freelancer(String freelancerId, User user, String otherInfo) {
		this.freelancerId = freelancerId;
		this.user = user;
		this.otherInfo = otherInfo;
	}

	public String getFreelancerId() {
		return freelancerId;
	}

	public void setFreelancerId(String freelancerId) {
		this.freelancerId = freelancerId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	
	
}
