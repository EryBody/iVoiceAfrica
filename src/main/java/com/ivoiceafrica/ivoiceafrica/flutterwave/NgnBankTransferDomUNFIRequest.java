package com.ivoiceafrica.ivoiceafrica.flutterwave;

import java.util.List;

public class NgnBankTransferDomUNFIRequest {

	public String account_number;
	public String account_bank;
	public int amount;
	public String currency;
	public String narration;
	public String reference;
	public String beneficiary_name;
	public List<NgnBankTransferDomUNFIRequestMeta> meta;

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getAccount_bank() {
		return account_bank;
	}

	public void setAccount_bank(String account_bank) {
		this.account_bank = account_bank;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getBeneficiary_name() {
		return beneficiary_name;
	}

	public void setBeneficiary_name(String beneficiary_name) {
		this.beneficiary_name = beneficiary_name;
	}

	public List<NgnBankTransferDomUNFIRequestMeta> getMeta() {
		return meta;
	}

	public void setMeta(List<NgnBankTransferDomUNFIRequestMeta> meta) {
		this.meta = meta;
	}

	@Override
	public String toString() {
		return "NgnBankTransferDomFcmbRequest [account_number=" + account_number + ", account_bank=" + account_bank
				+ ", amount=" + amount + ", currency=" + currency + ", narration=" + narration + ", reference="
				+ reference + ", beneficiary_name=" + beneficiary_name + ", meta=" + meta + "]";
	}

}
