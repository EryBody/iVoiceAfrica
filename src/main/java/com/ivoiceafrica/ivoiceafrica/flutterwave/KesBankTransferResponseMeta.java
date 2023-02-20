package com.ivoiceafrica.ivoiceafrica.flutterwave;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KesBankTransferResponseMeta {
	
	@JsonProperty("Sender")
	public String sender;
	@JsonProperty("SenderCountry")
	public String senderCountry;
	@JsonProperty("MobileNumber")
	public String mobileNumber;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSenderCountry() {
		return senderCountry;
	}

	public void setSenderCountry(String senderCountry) {
		this.senderCountry = senderCountry;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "KesBankTransferResponseMeta [sender=" + sender + ", senderCountry=" + senderCountry + ", mobileNumber="
				+ mobileNumber + "]";
	}

}
