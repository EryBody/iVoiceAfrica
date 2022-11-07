package com.ivoiceafrica.ivoiceafrica.DTO;

public class ClientPersonalDetailDTO {

	private String firstName;
	private String lastName;
	private String gender;
	private String nationality;
	private String streetAddress;
	private String townVillage;
	private String postalCode;
	private String mobileNumber;
	
	
	public ClientPersonalDetailDTO() {
		
	}
	
	public ClientPersonalDetailDTO(String firstName, String lastName, String gender, String nationality,
			String streetAddress, String townVillage, String postalCode, String mobileNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.nationality = nationality;
		this.streetAddress = streetAddress;
		this.townVillage = townVillage;
		this.postalCode = postalCode;
		this.mobileNumber = mobileNumber;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getTownVillage() {
		return townVillage;
	}

	public void setTownVillage(String townVillage) {
		this.townVillage = townVillage;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "ClientPersonalDetailDTO [firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", nationality=" + nationality + ", streetAddress=" + streetAddress + ", townVillage=" + townVillage
				+ ", postalCode=" + postalCode + ", mobileNumber=" + mobileNumber + "]";
	}
	
}
