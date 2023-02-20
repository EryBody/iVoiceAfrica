package com.ivoiceafrica.ivoiceafrica.flutterwave;

public class KesBankTransferResponse {
	public String status;
	public String message;
	public KesBankTransferResponseData data;

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

	public KesBankTransferResponseData getData() {
		return data;
	}

	public void setData(KesBankTransferResponseData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "KesBankTransferResponse [status=" + status + ", message=" + message + ", data=" + data + "]";
	}

}
