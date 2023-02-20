package com.ivoiceafrica.ivoiceafrica.components.models;

import org.springframework.beans.factory.annotation.Autowired;

import com.ivoiceafrica.ivoiceafrica.entity.BankDetail;
import com.ivoiceafrica.ivoiceafrica.service.BankDetailService;

public class PaymentComponent {
	
	@Autowired
	BankDetailService bankDetailService;

	public boolean isBankDetailExist(int userId) {

		boolean isBankDetailsExist = false;

		System.out.println("===>>>User ID Freelancer: " + userId);

		BankDetail detail = bankDetailService.findBankDetailsWithUserId(userId);

		if (!detail.getBankId().isEmpty()) {
			isBankDetailsExist = true;
		} else {
			isBankDetailsExist = false;
		}

		System.out.println("===>>> isBankDetailExist(Freelancer): " + isBankDetailsExist);

		return isBankDetailsExist;

	}
}
