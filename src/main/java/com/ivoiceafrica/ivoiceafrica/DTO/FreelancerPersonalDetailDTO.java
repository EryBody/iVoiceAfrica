package com.ivoiceafrica.ivoiceafrica.DTO;

public class FreelancerPersonalDetailDTO {

	private String country;
	private String nationality;
	private String streetAddress;
	private String townVillage;
	private String postalCode;
	private String gender;
	private String mobileNumber;
	private String countryCode;
	
	public FreelancerPersonalDetailDTO() {
		
	}

	public FreelancerPersonalDetailDTO(String country, String nationality, String streetAddress, String townVillage,
			String postalCode, String gender, String mobileNumber, String countryCode) {
		this.country = country;
		this.nationality = nationality;
		this.streetAddress = streetAddress;
		this.townVillage = townVillage;
		this.postalCode = postalCode;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.countryCode = countryCode;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "FreelancerPersonalDetailDTO [country=" + country + ", nationality=" + nationality + ", streetAddress="
				+ streetAddress + ", townVillage=" + townVillage + ", postalCode=" + postalCode + ", gender=" + gender
				+ ", mobileNumber=" + mobileNumber + ", countryCode=" + countryCode + "]";
	}

}
