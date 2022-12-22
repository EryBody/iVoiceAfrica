package com.ivoiceafrica.ivoiceafrica.DTO;

public class ProfileDTO {

	private int userId;
	private String firstName;
	private String lastName;
	private String sex;
	private String streetAddress;
	private String townVillage;
	private String country;
	private String mobileNumber;
	private String profilePhoto;
	private String nationality;

	public ProfileDTO() {

	}

	public ProfileDTO(int userId, String firstName, String lastName, String sex, String streetAddress,
			String townVillage, String country, String mobileNumber, String profilePhoto, String nationality) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.streetAddress = streetAddress;
		this.townVillage = townVillage;
		this.country = country;
		this.mobileNumber = mobileNumber;
		this.profilePhoto = profilePhoto;
		this.nationality = nationality;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Override
	public String toString() {
		return "ProfileDTO [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", sex=" + sex
				+ ", streetAddress=" + streetAddress + ", townVillage=" + townVillage + ", country=" + country
				+ ", mobileNumber=" + mobileNumber + ", profilePhoto=" + profilePhoto + ", nationality=" + nationality
				+ "]";
	}

}
