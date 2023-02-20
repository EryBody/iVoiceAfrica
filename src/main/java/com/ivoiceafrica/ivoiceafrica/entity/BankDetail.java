package com.ivoiceafrica.ivoiceafrica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;

@Entity
@Table(name = "bank_details")
public class BankDetail {

	@Id
	@GeneratedValue(generator = "detail_id", strategy = GenerationType.AUTO)
	@Column(name = "detail_id")
	private Integer detail_id;

	@Column(name = "bank_id")
	private String bankId;

	@ManyToOne // Mapping the column of this table
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "bank_branch_code")
	private String bankBranchCode;

	@Column(name = "bank_branch_name")
	private String bankBranchName;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "currency")
	private String currency;

	@Column(name = "merchant_name")
	private String merchantName;

	@Column(name = "routing_number")
	private String routingNumber;

	@Column(name = "swift_code")
	private String swiftCode;

	@Column(name = "entry_date")
	private String entryDate;

	public BankDetail() {

	}

	public Integer getDetail_id() {
		return detail_id;
	}

	public void setDetail_id(Integer detail_id) {
		this.detail_id = detail_id;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankBranchCode() {
		return bankBranchCode;
	}

	public void setBankBranchCode(String bankBranchCode) {
		this.bankBranchCode = bankBranchCode;
	}

	public String getBankBranchName() {
		return bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "BankDetail [detail_id=" + detail_id + ", bankId=" + bankId + ", bankBranchCode=" + bankBranchCode
				+ ", bankBranchName=" + bankBranchName + ", accountNumber=" + accountNumber + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", middleName=" + middleName + ", currency=" + currency + ", merchantName="
				+ merchantName + ", routingNumber=" + routingNumber + ", swiftCode=" + swiftCode + ", entryDate="
				+ entryDate + "]";
	}

}
