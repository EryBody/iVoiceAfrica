package com.ivoiceafrica.ivoiceafrica.DTO;

public class ClientSignupDTO {

	private String username;
	private String email;
	private String password;
	
	public ClientSignupDTO() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ClientSignupDTO [username=" + username + ", email=" + email + ", password=" + password + "]";
	}
	
}
