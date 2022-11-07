package com.ivoiceafrica.ivoiceafrica.DTO;

public class ClientSignupDTO {

	private String email;
	private String password;
	
	public ClientSignupDTO() {
		
	}
	
	public ClientSignupDTO(String email, String password) {
		this.email = email;
		this.password = password;
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
		return "ClientSignupDTO [email=" + email + ", password=" + password + "]";
	}
	
}
