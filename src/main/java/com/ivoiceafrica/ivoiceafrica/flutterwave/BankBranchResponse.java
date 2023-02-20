package com.ivoiceafrica.ivoiceafrica.flutterwave;

import java.util.List;

public class BankBranchResponse {

	public String status;
	public String message;
	public List<BankBranchResponseData> data;

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

	public List<BankBranchResponseData> getData() {
		return data;
	}

	public void setData(List<BankBranchResponseData> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "BankBranchResponse [status=" + status + ", message=" + message + ", data=" + data + "]";
	}

}
