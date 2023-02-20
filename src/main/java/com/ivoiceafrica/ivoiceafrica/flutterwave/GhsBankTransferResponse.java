package com.ivoiceafrica.ivoiceafrica.flutterwave;

public class GhsBankTransferResponse {

	public String status;
	public String message;
	public GhsBankTransferDataResponse data;

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

	public GhsBankTransferDataResponse getData() {
		return data;
	}

	public void setData(GhsBankTransferDataResponse data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "BankTransferResponse [status=" + status + ", message=" + message + ", data=" + data + "]";
	}

}
