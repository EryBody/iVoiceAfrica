package com.ivoiceafrica.ivoiceafrica.flutterwave;

public class ZarBankTransferRequestMeta {
	public String first_name;
	public String last_name;
	public String email;
	public String mobile_number;
	public String recipient_address;

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

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getRecipient_address() {
		return recipient_address;
	}

	public void setRecipient_address(String recipient_address) {
		this.recipient_address = recipient_address;
	}

	@Override
	public String toString() {
		return "ZarBankTransferRequestMeta [first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", mobile_number=" + mobile_number + ", recipient_address=" + recipient_address + "]";
	}

}
