package com.ivoiceafrica.ivoiceafrica.flutterwave;

public class BankBranchResponseData {
	public int id;
	public String branch_code;
	public String branch_name;
	public String swift_code;
	public String bic;
	public int bank_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBranch_code() {
		return branch_code;
	}

	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getSwift_code() {
		return swift_code;
	}

	public void setSwift_code(String swift_code) {
		this.swift_code = swift_code;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public int getBank_id() {
		return bank_id;
	}

	public void setBank_id(int bank_id) {
		this.bank_id = bank_id;
	}

	@Override
	public String toString() {
		return "BankBranchResponseData [id=" + id + ", branch_code=" + branch_code + ", branch_name=" + branch_name
				+ ", swift_code=" + swift_code + ", bic=" + bic + ", bank_id=" + bank_id + "]";
	}

}
