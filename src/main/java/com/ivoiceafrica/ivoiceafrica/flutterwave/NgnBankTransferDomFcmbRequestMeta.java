package com.ivoiceafrica.ivoiceafrica.flutterwave;

public class NgnBankTransferDomFcmbRequestMeta {

	public String mobile_number;
	public String email;
	public String beneficiary_country;
	public String beneficiary_occupation;
	public String recipient_address;
	public String sender;
	public String sender_country;
	public String sender_id_number;
	public String sender_id_type;
	public String sender_id_expiry;
	public String sender_mobile_number;
	public String sender_address;
	public String sender_occupation;
	public String sender_beneficiary_relationship;
	public String transfer_purpose;

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
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

	public String getBeneficiary_occupation() {
		return beneficiary_occupation;
	}

	public void setBeneficiary_occupation(String beneficiary_occupation) {
		this.beneficiary_occupation = beneficiary_occupation;
	}

	public String getRecipient_address() {
		return recipient_address;
	}

	public void setRecipient_address(String recipient_address) {
		this.recipient_address = recipient_address;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSender_country() {
		return sender_country;
	}

	public void setSender_country(String sender_country) {
		this.sender_country = sender_country;
	}

	public String getSender_id_number() {
		return sender_id_number;
	}

	public void setSender_id_number(String sender_id_number) {
		this.sender_id_number = sender_id_number;
	}

	public String getSender_id_type() {
		return sender_id_type;
	}

	public void setSender_id_type(String sender_id_type) {
		this.sender_id_type = sender_id_type;
	}

	public String getSender_id_expiry() {
		return sender_id_expiry;
	}

	public void setSender_id_expiry(String sender_id_expiry) {
		this.sender_id_expiry = sender_id_expiry;
	}

	public String getSender_mobile_number() {
		return sender_mobile_number;
	}

	public void setSender_mobile_number(String sender_mobile_number) {
		this.sender_mobile_number = sender_mobile_number;
	}

	public String getSender_address() {
		return sender_address;
	}

	public void setSender_address(String sender_address) {
		this.sender_address = sender_address;
	}

	public String getSender_occupation() {
		return sender_occupation;
	}

	public void setSender_occupation(String sender_occupation) {
		this.sender_occupation = sender_occupation;
	}

	public String getSender_beneficiary_relationship() {
		return sender_beneficiary_relationship;
	}

	public void setSender_beneficiary_relationship(String sender_beneficiary_relationship) {
		this.sender_beneficiary_relationship = sender_beneficiary_relationship;
	}

	public String getTransfer_purpose() {
		return transfer_purpose;
	}

	public void setTransfer_purpose(String transfer_purpose) {
		this.transfer_purpose = transfer_purpose;
	}

	@Override
	public String toString() {
		return "NgnBankTransferDomFcmbRequestMeta [mobile_number=" + mobile_number + ", email=" + email
				+ ", beneficiary_country=" + beneficiary_country + ", beneficiary_occupation=" + beneficiary_occupation
				+ ", recipient_address=" + recipient_address + ", sender=" + sender + ", sender_country="
				+ sender_country + ", sender_id_number=" + sender_id_number
				+ ", sender_id_type=" + sender_id_type + ", sender_id_expiry=" + sender_id_expiry
				+ ", sender_mobile_number=" + sender_mobile_number + ", sender_address=" + sender_address
				+ ", sender_occupation=" + sender_occupation + ", sender_beneficiary_relationship="
				+ sender_beneficiary_relationship + ", transfer_purpose=" + transfer_purpose + "]";
	}

}
