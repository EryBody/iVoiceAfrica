package com.ivoiceafrica.ivoiceafrica.flutterwave;

public class BankTransferResponse {

	public String status;
	public String message;
	public BankTransferDataResponse data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BankTransferDataResponse getData() {
		return data;
	}

	public void setData(BankTransferDataResponse data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "BankTransferResponse [status=" + status + ", message=" + message + ", data=" + data + "]";
	}

}
