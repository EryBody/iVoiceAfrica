package com.ivoiceafrica.ivoiceafrica.flutterwave;

public class NgnBankTransferDomRequestMeta {
	public String first_name;
	public String last_name;
	public String email;
	public String beneficiary_country;
	public String mobile_number;
	public String sender;
	public String merchant_name;

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBeneficiary_country() {
		return beneficiary_country;
	}

	public void setBeneficiary_country(String beneficiary_country) {
		this.beneficiary_country = beneficiary_country;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getMerchant_name() {
		return merchant_name;
	}

	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}

	@Override
	public String toString() {
		return "NgnBankTransferDomRequestMeta [first_name=" + first_name + ", last_name=" + last_name + ", email="
				+ email + ", beneficiary_country=" + beneficiary_country + ", mobile_number=" + mobile_number
				+ ", sender=" + sender + ", merchant_name=" + merchant_name + "]";
	}

}
