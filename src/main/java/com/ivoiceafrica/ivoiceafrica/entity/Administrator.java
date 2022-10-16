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
@Table(name = "admins")
public class Administrator {

	@Id
    @GenericGenerator(name = "admin_id", strategy = "com.ivoiceafrica.ivoiceafrica.IdGenerator.AdministratorGenerator")
    @GeneratedValue(generator = "admin_id", strategy = GenerationType.SEQUENCE)  
    @Column(name="admin_id")
	private String adminId;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "user_id")
    private User user;
	
	@Column(name = "other_info")
	private String otherInfo;
	
	
	public Administrator() {
		
	}
	

	public Administrator(String adminId, User user, String otherInfo) {
		this.adminId = adminId;
		this.user = user;
		this.otherInfo = otherInfo;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
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
