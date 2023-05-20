package com.ivoiceafrica.ivoiceafrica.auth.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="role_id")
	private Integer id;
	
	@Column(name="name",nullable = false, unique = true)
	@NotEmpty
	private String name;
	
	@Column(name = "created_date", nullable = false, unique = true)
	private String createdDate;
	
	public Role() {
		
	}

	public Role(Integer id, @NotEmpty String name, String createdDate) {
		this.id = id;
		this.name = name;
		this.createdDate = createdDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", createdDate=" + createdDate + "]";
	}
	
}
