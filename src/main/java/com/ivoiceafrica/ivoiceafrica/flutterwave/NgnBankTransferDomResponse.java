package com.ivoiceafrica.ivoiceafrica.flutterwave;

public class NgnBankTransferDomResponse {

	public String status;
	public String message;
	public NgnBankTransferDomResponseData data;

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

	public NgnBankTransferDomResponseData getData() {
		return data;
	}

	public void setData(NgnBankTransferDomResponseData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "NgnBankTransferDomResponse [status=" + status + ", message=" + message + ", data=" + data + "]";
	}

}
