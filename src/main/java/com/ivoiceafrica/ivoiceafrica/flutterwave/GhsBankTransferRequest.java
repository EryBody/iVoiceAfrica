package com.ivoiceafrica.ivoiceafrica.flutterwave;

public class GhsBankTransferRequest {

	public String account_bank;
	public String account_number;
	public int amount;
	public String narration;
	public String currency;
	public String reference;
	public String callback_url;
	public String destination_branch_code;
	public String beneficiary_name;

	public String getAccount_bank() {
		return account_bank;
	}

	public void setAccount_bank(String account_bank) {
		this.account_bank = account_bank;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getCallback_url() {
		return callback_url;
	}

	public void setCallback_url(String callback_url) {
		this.callback_url = callback_url;
	}

	public String getDestination_branch_code() {
		return destination_branch_code;
	}

	public void setDestination_branch_code(String destination_branch_code) {
		this.destination_branch_code = destination_branch_code;
	}

	public String getBeneficiary_name() {
		return beneficiary_name;
	}

	public void setBeneficiary_name(String beneficiary_name) {
		this.beneficiary_name = beneficiary_name;
	}

	@Override
	public String toString() {
		return "GhsBankTransferRequest [account_bank=" + account_bank + ", account_number=" + account_number
				+ ", amount=" + amount + ", narration=" + narration + ", currency=" + currency + ", reference="
				+ reference + ", callback_url=" + callback_url + ", destination_branch_code=" + destination_branch_code
				+ ", beneficiary_name=" + beneficiary_name + "]";
	}

}
