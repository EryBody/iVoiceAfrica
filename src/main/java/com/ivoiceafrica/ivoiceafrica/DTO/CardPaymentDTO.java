package com.ivoiceafrica.ivoiceafrica.DTO;

public class CardPaymentDTO {

	private String nameOnCard;
	private String cardNumber;
	private String cvv;
	private String cardExpiry;
	private String currency;
	private double amount;
	private String email;
	private String pin;
	private String otp;
	private String billingaddress;
	private String billingcity;
	private String billingcountry;
	private String billingstate;
	private String billingzip;

	public CardPaymentDTO() {

	}

	public CardPaymentDTO(String nameOnCard, String cardNumber, String cvv, String cardExpiry, String currency,
			double amount, String email, String pin, String otp) {
		this.nameOnCard = nameOnCard;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.cardExpiry = cardExpiry;
		this.currency = currency;
		this.amount = amount;
		this.email = email;
		this.pin = pin;
		this.otp = otp;
	}

	public CardPaymentDTO(String billingaddress, String billingcity, String billingcountry, String billingstate,
			String billingzip) {
		this.billingaddress = billingaddress;
		this.billingcity = billingcity;
		this.billingcountry = billingcountry;
		this.billingstate = billingstate;
		this.billingzip = billingzip;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getCardExpiry() {
		return cardExpiry;
	}

	public void setCardExpiry(String cardExpiry) {
		this.cardExpiry = cardExpiry;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getBillingaddress() {
		return billingaddress;
	}

	public void setBillingaddress(String billingaddress) {
		this.billingaddress = billingaddress;
	}

	public String getBillingcity() {
		return billingcity;
	}

	public void setBillingcity(String billingcity) {
		this.billingcity = billingcity;
	}

	public String getBillingcountry() {
		return billingcountry;
	}

	public void setBillingcountry(String billingcountry) {
		this.billingcountry = billingcountry;
	}

	public String getBillingstate() {
		return billingstate;
	}

	public void setBillingstate(String billingstate) {
		this.billingstate = billingstate;
	}

	public String getBillingzip() {
		return billingzip;
	}

	public void setBillingzip(String billingzip) {
		this.billingzip = billingzip;
	}

	@Override
	public String toString() {
		return "CardPaymentDTO [nameOnCard=" + nameOnCard + ", cardNumber=" + cardNumber + ", cvv=" + cvv
				+ ", cardExpiry=" + cardExpiry + ", currency=" + currency + ", amount=" + amount + ", email=" + email
				+ ", pin=" + pin + ", otp=" + otp + ", billingaddress=" + billingaddress + ", billingcity="
				+ billingcity + ", billingcountry=" + billingcountry + ", billingstate=" + billingstate
				+ ", billingzip=" + billingzip + "]";
	}

}
