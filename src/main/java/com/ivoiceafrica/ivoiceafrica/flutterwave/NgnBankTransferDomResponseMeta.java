package com.ivoiceafrica.ivoiceafrica.flutterwave;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NgnBankTransferDomResponseMeta {

	@JsonProperty("FirstName")
	public String firstName;
	@JsonProperty("LastName")
	public String lastName;
	@JsonProperty("EmailAddress")
	public String emailAddress;
	@JsonProperty("BeneficiaryCountry")
	public String beneficiaryCountry;
	@JsonProperty("MobileNumber")
	public String mobileNumber;
	@JsonProperty("Sender")
	public String sender;
	@JsonProperty("MerchantName")
	public String merchantName;
	@JsonProperty("SenderCountry")
	public String senderCountry;
	@JsonProperty("AccountNumber")
	public String accountNumber;
	@JsonProperty("RoutingNumber")
	public String routingNumber;
	@JsonProperty("Address")
	public Object address;
	@JsonProperty("IsCashPickup")
	public boolean isCashPickup;

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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getBeneficiaryCountry() {
		return beneficiaryCountry;
	}

	public void setBeneficiaryCountry(String beneficiaryCountry) {
		this.beneficiaryCountry = beneficiaryCountry;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getSenderCountry() {
		return senderCountry;
	}

	public void setSenderCountry(String senderCountry) {
		this.senderCountry = senderCountry;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getRoutingNumber() {
		return routingNumber;
	}

	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}

	public Object getAddress() {
		return address;
	}

	public void setAddress(Object address) {
		this.address = address;
	}

	public boolean isCashPickup() {
		return isCashPickup;
	}

	public void setCashPickup(boolean isCashPickup) {
		this.isCashPickup = isCashPickup;
	}

	@Override
	public String toString() {
		return "NgnBankTransferDomResponseMeta [firstName=" + firstName + ", lastName=" + lastName + ", emailAddress="
				+ emailAddress + ", beneficiaryCountry=" + beneficiaryCountry + ", mobileNumber=" + mobileNumber
				+ ", sender=" + sender + ", merchantName=" + merchantName + ", senderCountry=" + senderCountry
				+ ", accountNumber=" + accountNumber + ", routingNumber=" + routingNumber + ", address=" + address
				+ ", isCashPickup=" + isCashPickup + "]";
	}

}
