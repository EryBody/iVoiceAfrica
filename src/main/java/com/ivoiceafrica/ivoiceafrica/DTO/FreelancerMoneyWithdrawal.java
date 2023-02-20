package com.ivoiceafrica.ivoiceafrica.DTO;

public class FreelancerMoneyWithdrawal {

	private String username;
	private String countryCode;
	private int amount;
	private String narration;
	private String merchantName;
	private String occupation;
	private String IdentificationType;
	private String IdentificationNumber;
	private String IdentificationExpiry;
	private String city;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getIdentificationType() {
		return IdentificationType;
	}

	public void setIdentificationType(String identificationType) {
		IdentificationType = identificationType;
	}

	public String getIdentificationNumber() {
		return IdentificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		IdentificationNumber = identificationNumber;
	}

	public String getIdentificationExpiry() {
		return IdentificationExpiry;
	}

	public void setIdentificationExpiry(String identificationExpiry) {
		IdentificationExpiry = identificationExpiry;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "FreelancerMoneyWithdrawal [username=" + username + ", countryCode=" + countryCode + ", amount=" + amount
				+ ", narration=" + narration + ", merchantName=" + merchantName + ", occupation=" + occupation
				+ ", IdentificationType=" + IdentificationType + ", IdentificationNumber=" + IdentificationNumber
				+ ", IdentificationExpiry=" + IdentificationExpiry + ", city=" + city + "]";
	}

}
