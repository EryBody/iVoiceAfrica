package com.ivoiceafrica.ivoiceafrica.DTO;

public class AddUserDTO {

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String middleName;
	private String gender;
	private String country;
	private String nationality;
	private String address;
	private String postalCode;
	private String phone;
	private String email;
	private String summary;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@Override
	public String toString() {
		return "AddUserDTO [username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", middleName=" + middleName + ", gender=" + gender + ", country="
				+ country + ", nationality=" + nationality + ", address=" + address + ", postalCode=" + postalCode
				+ ", phone=" + phone + ", email=" + email + ", summary=" + summary + "]";
	}
	
	
}
