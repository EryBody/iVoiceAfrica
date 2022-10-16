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
@Table(name = "clients")
public class Client {

	@Id
    @GenericGenerator(name = "client_id", strategy = "com.ivoiceafrica.ivoiceafrica.IdGenerator.ClientGenerator")
    @GeneratedValue(generator = "client_id", strategy = GenerationType.SEQUENCE)  
    @Column(name="client_id")
	private String clientId;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "user_id")
    private User user;
	
	@Column(name = "other_info")
	private String otherInfo;

	public Client() {
		
	}
	
	public Client(String clientId, User user, String otherInfo) {
		this.clientId = clientId;
		this.user = user;
		this.otherInfo = otherInfo;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
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
