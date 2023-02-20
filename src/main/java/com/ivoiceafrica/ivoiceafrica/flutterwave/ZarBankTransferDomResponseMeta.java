package com.ivoiceafrica.ivoiceafrica.flutterwave;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ZarBankTransferDomResponseMeta {

	@JsonProperty("FirstName")
	public String firstName;
	@JsonProperty("LastName")
	public String lastName;
	@JsonProperty("EmailAddress")
	public String emailAddress;
	@JsonProperty("MobileNumber")
	public String mobileNumber;
	@JsonProperty("Address")
	public String address;
	@JsonProperty("MerchantName")
	public String merchantName;
	@JsonProperty("SenderCountry")
	public String senderCountry;
	@JsonProperty("SenderAddress")
	public String senderAddress;
	@JsonProperty("AccountNumber")
	public String accountNumber;
	@JsonProperty("RoutingNumber")
	public String routingNumber;
	@JsonProperty("Sender")
	public String sender;

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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
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

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	@Override
	public String toString() {
		return "ZarBankTransferDomResponseMeta [firstName=" + firstName + ", lastName=" + lastName + ", emailAddress="
				+ emailAddress + ", mobileNumber=" + mobileNumber + ", address=" + address + ", merchantName="
				+ merchantName + ", senderCountry=" + senderCountry + ", senderAddress=" + senderAddress
				+ ", accountNumber=" + accountNumber + ", routingNumber=" + routingNumber + ", sender=" + sender + "]";
	}

}
