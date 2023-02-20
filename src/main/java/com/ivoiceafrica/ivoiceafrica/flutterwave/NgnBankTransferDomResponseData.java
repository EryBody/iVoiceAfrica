package com.ivoiceafrica.ivoiceafrica.flutterwave;

import java.util.List;

public class NgnBankTransferDomResponseData {

	public int id;
	public String account_number;
	public String bank_code;
	public String full_name;
	public String created_at;
	public String currency;
	public String debit_currency;
	public int amount;
	public int fee;
	public String status;
	public String reference;
	public List<NgnBankTransferDomResponseMeta> meta;
	public String narration;
	public String complete_message;
	public int requires_approval;
	public int is_approved;
	public String bank_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getBank_code() {
		return bank_code;
	}

	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public List<NgnBankTransferDomResponseMeta> getMeta() {
		return meta;
	}

	public void setMeta(List<NgnBankTransferDomResponseMeta> meta) {
		this.meta = meta;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getComplete_message() {
		return complete_message;
	}

	public void setComplete_message(String complete_message) {
		this.complete_message = complete_message;
	}

	public int getRequires_approval() {
		return requires_approval;
	}

	public void setRequires_approval(int requires_approval) {
		this.requires_approval = requires_approval;
	}

	public int getIs_approved() {
		return is_approved;
	}

	public void setIs_approved(int is_approved) {
		this.is_approved = is_approved;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	@Override
	public String toString() {
		return "NgnBankTransferDomResponseData [id=" + id + ", account_number=" + account_number + ", bank_code="
				+ bank_code + ", full_name=" + full_name + ", created_at=" + created_at + ", currency=" + currency
				+ ", debit_currency=" + debit_currency + ", amount=" + amount + ", fee=" + fee + ", status=" + status
				+ ", reference=" + reference + ", meta=" + meta + ", narration=" + narration + ", complete_message="
				+ complete_message + ", requires_approval=" + requires_approval + ", is_approved=" + is_approved
				+ ", bank_name=" + bank_name + "]";
	}

}
