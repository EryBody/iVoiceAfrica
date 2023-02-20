package com.ivoiceafrica.ivoiceafrica.flutterwave;

import java.util.List;

public class BankResponse {

	public String status;
    public String message;
    public List<BankResponseData> data;
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
	public List<BankResponseData> getData() {
		return data;
	}
	public void setData(List<BankResponseData> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "BankResponse [status=" + status + ", message=" + message + ", data=" + data + "]";
	}
	
    
}
