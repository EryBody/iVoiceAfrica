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
	private String country;
	private String countryCode;
	
	
	public ClientPersonalDetailDTO() {
		
	}

	public ClientPersonalDetailDTO(String firstName, String lastName, String gender, String nationality,
			String streetAddress, String townVillage, String postalCode, String mobileNumber, String country,
			String countryCode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.nationality = nationality;
		this.streetAddress = streetAddress;
		this.townVillage = townVillage;
		this.postalCode = postalCode;
		this.mobileNumber = mobileNumber;
		this.country = country;
		this.countryCode = countryCode;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "ClientPersonalDetailDTO [firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", nationality=" + nationality + ", streetAddress=" + streetAddress + ", townVillage=" + townVillage
				+ ", postalCode=" + postalCode + ", mobileNumber=" + mobileNumber + ", country=" + country
				+ ", countryCode=" + countryCode + "]";
	}

}
