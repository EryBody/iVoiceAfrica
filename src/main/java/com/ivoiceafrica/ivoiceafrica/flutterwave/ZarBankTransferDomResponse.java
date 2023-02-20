package com.ivoiceafrica.ivoiceafrica.flutterwave;

public class ZarBankTransferDomResponse {

	public String status;
	public String message;
	public ZarBankTransferDomResponseData data;

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

	public ZarBankTransferDomResponseData getData() {
		return data;
	}

	public void setData(ZarBankTransferDomResponseData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "NgnBankTransferDomResponse [status=" + status + ", message=" + message + ", data=" + data + "]";
	}

}
