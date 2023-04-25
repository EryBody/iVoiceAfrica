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

		boolean isBankDetailsExist = false;
		System.out.println("===>>> User ID Freelancer: " + userId);

		BankDetail bank = bankDetailService.findBankDetailsWithUserId(71);

		System.out.println("===>>> isBankDetailExist(Freelancer): " + isBankDetailsExist);

		return bank;

	}
}
