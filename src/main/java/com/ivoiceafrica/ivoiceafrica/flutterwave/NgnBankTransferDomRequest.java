package com.ivoiceafrica.ivoiceafrica.flutterwave;

import java.util.List;

public class NgnBankTransferDomRequest {
	
	public String account_number;
	public String account_bank;
	public String narration;
	public int amount;
	public String reference;
	public String currency;
	public String debit_currency;
	public String beneficiary_name;
	public List<NgnBankTransferDomRequestMeta> meta;

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

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDebit_currency() {
		return debit_currency;
	}

	public void setDebit_currency(String debit_currency) {
		this.debit_currency = debit_currency;
	}

	public String getBeneficiary_name() {
		return beneficiary_name;
	}

	public void setBeneficiary_name(String beneficiary_name) {
		this.beneficiary_name = beneficiary_name;
	}

	public List<NgnBankTransferDomRequestMeta> getMeta() {
		return meta;
	}

	public void setMeta(List<NgnBankTransferDomRequestMeta> meta) {
		this.meta = meta;
	}

	@Override
	public String toString() {
		return "NgnBankTransferDomRequest [account_number=" + account_number + ", account_bank=" + account_bank
				+ ", narration=" + narration + ", amount=" + amount + ", reference=" + reference + ", currency="
				+ currency + ", debit_currency=" + debit_currency + ", beneficiary_name=" + beneficiary_name + ", meta="
				+ meta + "]";
	}

}
