package com.ivoiceafrica.ivoiceafrica.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "banks")
public class Bank {

	@Id
    @GeneratedValue(generator = "bank_id", strategy = GenerationType.AUTO)  
    @Column(name="bank_id")
	private Integer bankId;
	
	@Column(name="bank_code")
	private String bankCode;
	
	@Column(name="bank_name")
	private String bankName;
	
	public Bank() {
		
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Override
	public String toString() {
		return "Bank [bankId=" + bankId + ", bankCode=" + bankCode + ", bankName=" + bankName + "]";
	}
	
}
