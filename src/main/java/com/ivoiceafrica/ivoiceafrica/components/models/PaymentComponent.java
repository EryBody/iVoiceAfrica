package com.ivoiceafrica.ivoiceafrica.components.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ivoiceafrica.ivoiceafrica.entity.BankDetail;
import com.ivoiceafrica.ivoiceafrica.service.BankDetailService;

@Component("PaymentComponent")
public class PaymentComponent {
	
	@Autowired
	BankDetailService bankDetailService;

	public BankDetail isBankDetailExist(int userId) {

		System.out.println("===>>> User ID Freelancer: " + userId);

		BankDetail bank = bankDetailService.findBankDetailsWithUserId(userId);

		System.out.println("===>>> bank(Freelancer): " + bank);

		return bank;

	}
}
