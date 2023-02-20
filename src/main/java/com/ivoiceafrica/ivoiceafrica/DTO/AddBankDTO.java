package com.ivoiceafrica.ivoiceafrica.DTO;

public class AddBankDTO {

	private String country;
	private String bankCode;
	private String bankBranchCodeAndName;
	private String accountNumber;
	private String firstName;
	private String lastName;
	private String middleName;
	private String merchantName;
	private String routingNumber;
	private String swiftCode;
	
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankBranchCodeAndName() {
		return bankBranchCodeAndName;
	}
	public void setBankBranchCodeAndName(String bankBranchCodeAndName) {
		this.bankBranchCodeAndName = bankBranchCodeAndName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getRoutingNumber() {
		return routingNumber;
	}
	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}
	public String getSwiftCode() {
		return swiftCode;
	}
	
	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}
	
	@Override
	public String toString() {
		return "AddBankDTO [country=" + country + ", bankCode=" + bankCode + ", bankBranchCodeAndName="
				+ bankBranchCodeAndName + ", accountNumber=" + accountNumber + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", middleName=" + middleName + ", merchantName=" + merchantName
				+ ", routingNumber=" + routingNumber + ", swiftCode=" + swiftCode + "]";
	}
	
}
