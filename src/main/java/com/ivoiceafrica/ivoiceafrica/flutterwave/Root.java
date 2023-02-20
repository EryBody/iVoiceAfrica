package com.ivoiceafrica.ivoiceafrica.flutterwave;

public class Root {
	public String status;
	public Customer customer;
	public int transaction_id;
	public String tx_ref;
	public String flw_ref;
	public String currency;
	public String amount;
	public int charged_amount;
	public String charge_response_code;
	public String charge_response_message;
	public String created_at;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getTx_ref() {
		return tx_ref;
	}
	public void setTx_ref(String tx_ref) {
		this.tx_ref = tx_ref;
	}
	public String getFlw_ref() {
		return flw_ref;
	}
	public void setFlw_ref(String flw_ref) {
		this.flw_ref = flw_ref;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public int getCharged_amount() {
		return charged_amount;
	}
	public void setCharged_amount(int charged_amount) {
		this.charged_amount = charged_amount;
	}
	public String getCharge_response_code() {
		return charge_response_code;
	}
	public void setCharge_response_code(String charge_response_code) {
		this.charge_response_code = charge_response_code;
	}
	public String getCharge_response_message() {
		return charge_response_message;
	}
	public void setCharge_response_message(String charge_response_message) {
		this.charge_response_message = charge_response_message;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	@Override
	public String toString() {
		return "Root [status=" + status + ", customer=" + customer + ", transaction_id=" + transaction_id + ", tx_ref="
				+ tx_ref + ", flw_ref=" + flw_ref + ", currency=" + currency + ", amount=" + amount
				+ ", charged_amount=" + charged_amount + ", charge_response_code=" + charge_response_code
				+ ", charge_response_message=" + charge_response_message + ", created_at=" + created_at + "]";
	}
	
	
}
