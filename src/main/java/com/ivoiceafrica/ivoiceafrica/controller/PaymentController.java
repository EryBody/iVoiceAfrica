package com.ivoiceafrica.ivoiceafrica.controller;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ivoiceafrica.ivoiceafrica.DTO.AddBankDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.CardPaymentDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerMoneyWithdrawal;
import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.components.models.PaymentComponent;
import com.ivoiceafrica.ivoiceafrica.entity.Bank;
import com.ivoiceafrica.ivoiceafrica.entity.BankDetail;
import com.ivoiceafrica.ivoiceafrica.entity.Proposal;
import com.ivoiceafrica.ivoiceafrica.entity.WorkEscrowTransaction;
import com.ivoiceafrica.ivoiceafrica.entity.WorkFreelancerPayment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkPaymentStatus;
import com.ivoiceafrica.ivoiceafrica.entity.WorkPayments;
import com.ivoiceafrica.ivoiceafrica.entity.WorkTransactions;
import com.ivoiceafrica.ivoiceafrica.flutterwave.BankBranchResponse;
import com.ivoiceafrica.ivoiceafrica.flutterwave.BankBranchResponseData;
import com.ivoiceafrica.ivoiceafrica.flutterwave.BankResponse;
import com.ivoiceafrica.ivoiceafrica.flutterwave.BankResponseData;
import com.ivoiceafrica.ivoiceafrica.flutterwave.BankTransferResponse;
import com.ivoiceafrica.ivoiceafrica.flutterwave.GhsBankTransferRequest;
import com.ivoiceafrica.ivoiceafrica.flutterwave.GhsBankTransferResponse;
import com.ivoiceafrica.ivoiceafrica.flutterwave.KesBankTransferRequest;
import com.ivoiceafrica.ivoiceafrica.flutterwave.KesBankTransferRequestMeta;
import com.ivoiceafrica.ivoiceafrica.flutterwave.KesBankTransferResponse;
import com.ivoiceafrica.ivoiceafrica.flutterwave.NgnBankTransferDomFcmbRequest;
import com.ivoiceafrica.ivoiceafrica.flutterwave.NgnBankTransferDomFcmbRequestMeta;
import com.ivoiceafrica.ivoiceafrica.flutterwave.NgnBankTransferDomRequest;
import com.ivoiceafrica.ivoiceafrica.flutterwave.NgnBankTransferDomRequestMeta;
import com.ivoiceafrica.ivoiceafrica.flutterwave.NgnBankTransferDomResponse;
import com.ivoiceafrica.ivoiceafrica.flutterwave.NgnBankTransferDomUNFIRequest;
import com.ivoiceafrica.ivoiceafrica.flutterwave.NgnBankTransferDomUNFIRequestMeta;
import com.ivoiceafrica.ivoiceafrica.flutterwave.NgnBankTransferRequest;
import com.ivoiceafrica.ivoiceafrica.flutterwave.Root;
import com.ivoiceafrica.ivoiceafrica.flutterwave.ZarBankTransferDomResponse;
import com.ivoiceafrica.ivoiceafrica.flutterwave.ZarBankTransferRequest;
import com.ivoiceafrica.ivoiceafrica.flutterwave.ZarBankTransferRequestMeta;
import com.ivoiceafrica.ivoiceafrica.service.BankDetailService;
import com.ivoiceafrica.ivoiceafrica.service.CustomUserDetailService;
import com.ivoiceafrica.ivoiceafrica.service.FlutterwaveService;
import com.ivoiceafrica.ivoiceafrica.service.ProposalService;
import com.ivoiceafrica.ivoiceafrica.service.WorkEscrowTransactionService;
import com.ivoiceafrica.ivoiceafrica.service.WorkFreelancerPaymentService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderService;
import com.ivoiceafrica.ivoiceafrica.service.WorkPaymentService;
import com.ivoiceafrica.ivoiceafrica.service.WorkPaymentStatusService;
import com.ivoiceafrica.ivoiceafrica.service.WorkTransactionService;
import com.ivoiceafrica.ivoiceafrica.utility.RandomIdGenerator;

@Controller
public class PaymentController {

	Gson gson = new Gson();

	final static String PBFPubKey = "FLWPUBK_TEST-c080d8aa9590b4bdac7ab337dcb84f8f-X";
	final static String redirectUrlDemo = "http://localhost:8080/client-dashboard";
	final static String redirectUrlLive = "http://20.172.167.140:9000/client-dashboard";
	final static String redirectUrl = redirectUrlDemo;
	final static String encryptionKey = "FLWSECK_TEST6a46b266e750";
	final static String PBFSecretKey = "FLWSECK_TEST-28bcc703fd128945e499d8e9a0fa2fa3-X";

	@Autowired
	CustomUserDetailService userService;

	@Autowired
	HttpSession session;

	@Autowired
	WorkTransactionService workTransactionService;

	@Autowired
	WorkEscrowTransactionService workEscrowTransactionService;

	@Autowired
	ProposalService proposalService;

	@Autowired
	WorkOrderService workOrderService;

	@Autowired
	WorkPaymentStatusService workPaymentStatusService;

	@Autowired
	WorkPaymentService workPaymentService;

	@Autowired
	WorkFreelancerPaymentService workFreelancerPaymentService;

	@Autowired
	FlutterwaveService flutterwaveService;

	@Autowired
	BankDetailService bankDetailService;

	@GetMapping("/select-payment-option/{freelancer}/{proposalId}")
	public String selectPaymentOption(@PathVariable(value = "freelancer") String freelancer,
			@PathVariable(value = "proposalId") String proposalId, Model model) {

		System.out.println("===>>> freelancer: "+freelancer);
		System.out.println("===>>> proposalId: "+proposalId);
		
		Optional<Proposal> proposalOp = proposalService.findById(proposalId);
		System.out.println("===>>> proposalOp: " + proposalOp);

		model.addAttribute("freelancerId", freelancer);
		model.addAttribute("proposalOp", proposalOp.get());
		return "dashboards/payments/selectPaymentOption";
	}

	@GetMapping("/get-client-payment-details")
	@ResponseBody
	public String clientPaymentDetails(@RequestParam String inputParameter, @RequestParam String freelancerId,
			@RequestParam String workId, Model model) {

		System.out.println("===>>> inputParameter: " + inputParameter);
		System.out.println("===>>> freelancerId: " + freelancerId);

		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);

		Optional<User> freelancerDetails = userService.findFirstUserByUsername(freelancerId);

		Optional<WorkOrder> workOrder = workOrderService.findById(workId);

		String createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

		ObjectMapper om = new ObjectMapper();

		try {

			Root root = om.readValue(inputParameter, Root.class);
			System.out.println("===>>> root: " + root);

			WorkEscrowTransaction escrow = new WorkEscrowTransaction();

//			escrow.setTransId();
			escrow.setClientId(userDetails.get());
			escrow.setFreelancerId(freelancerDetails.get());
			escrow.setWorkOrder(workOrder.get());
			escrow.setAmount(Double.parseDouble(root.getAmount()));
			escrow.setCurrency(root.getCurrency());
			escrow.setIsReleased(false);// 1 == true, 0 == false from database
			escrow.setReleasedDate(createdDate); // released date will change when admin pays freelancer
			escrow.setEntryDate(createdDate);

			workEscrowTransactionService.save(escrow);

			System.out.println("===>>> Saved Work Escrow");

			WorkTransactions trans = new WorkTransactions();
//			trans.setTransId();
			trans.setUser(freelancerDetails.get());
			trans.setWorkOrder(workOrder.get());
			trans.setAmount(Double.parseDouble(root.getAmount()));
			trans.setCurrency(root.getCurrency());
			trans.setIsInFlow(true);// 1 == true, 0 == false from database
			trans.setEntryDate(createdDate);

			workTransactionService.save(trans);

			System.out.println("===>>> Saved Work Transactions");

			Optional<WorkPaymentStatus> workStatus = workPaymentStatusService.findById(4);// 4 means Escrow

			WorkPayments workPayment = new WorkPayments();
//			workPayment.setTransId(0);
			workPayment.setClientId(userDetails.get());
			workPayment.setWorkOrder(workOrder.get());
			workPayment.setAmount(Double.parseDouble(root.getAmount()));
			workPayment.setCurrency(root.getCurrency());
			workPayment.setPaymentStatus(workStatus.get());
			workPayment.setPaymentGateway("Flutterwave Gateway");
			workPayment.setEntryDate(createdDate);

			workPaymentService.save(workPayment);

			System.out.println("===>>> Saved Work Payments");

			WorkFreelancerPayment freelancerPayment = new WorkFreelancerPayment();
//			freelancerPayment.setTransId(0);
			freelancerPayment.setWorkOrder(workOrder.get());
			freelancerPayment.setFreelancerId(freelancerDetails.get());
			freelancerPayment.setAmount(Double.parseDouble(root.getAmount()));
			freelancerPayment.setPaymentStatus(workStatus.get());
			freelancerPayment.setCurrency(root.getCurrency());
			freelancerPayment.setPaymentGateway("Flutterwave Gateway");
			freelancerPayment.setEntryDate(createdDate);

			workFreelancerPaymentService.save(freelancerPayment);

			System.out.println("===>>> Saved Work Freelancer Payments");

		} catch (JsonMappingException e) {
			e.printStackTrace();
			System.out.println("===>>> Exception: " + e.getMessage());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			System.out.println("===>>> Exception: " + e.getMessage());
		}

		String financialType = "";

		return financialType;
	}

	@GetMapping("/bank-details-form")
	public String bankDetails() {
		return "dashboards/payments/bankDetails";
	}

	@GetMapping("/client-payment-details")
	public String clientPaymentForm() {
		return "dashboards/payments/clientPaymentForm";
	}

	@PostMapping("/freelancer-withdrawal-money")
	public String freelancerWithdrawMoney(@ModelAttribute("FreelancerMoneyWithdrawal") FreelancerMoneyWithdrawal fmw,
			BindingResult bindingResultModel, Model model, RedirectAttributes attributes) throws UnknownHostException {

		System.out.println("===>>> FreelancerMoneyWithdrawal: " + fmw);

		String userId = (String) session.getAttribute("userId");
		Optional<User> user = userService.findFirstUserByUsername(userId);

		System.out.println("===>>> User ID Freelancer: " + userId);

		BankDetail detail = bankDetailService.findBankDetailsWithUserId(user.get().getUserId());

		System.out.println("===>>> Detail: " + detail);

		try {
			if (!detail.getBankId().isEmpty()) {

				if (fmw.getCountryCode().equals("NGN")) {
					NgnBankTransferRequest request = new NgnBankTransferRequest();

					request.setAccount_bank(detail.getBankId());
					request.setAccount_number(detail.getAccountNumber());
					request.setAmount(fmw.getAmount());
					request.setNarration(fmw.getNarration());
					request.setCurrency("NGN");
					request.setReference(RandomIdGenerator.generateId(15));
					request.setCallback_url(redirectUrl);
					request.setDebit_currency("NGN");

					try {

						BankTransferResponse response = flutterwaveService.ngnBankTransfer(request);

						if (response.getStatus().equals("success")) {

							model.addAttribute("message", "Operation Successful, Transfer Queued Successfully.");
						} else {
							model.addAttribute("message", "Transaction was not successful, Try again.");
						}

					} catch (Exception e) {
						e.printStackTrace();
						model.addAttribute("message", "Something went wrong. Message: " + e.getMessage());
						System.out.println("===>>> Exception: " + e.getMessage());
					}

				} else if (fmw.getCountryCode().equals("NGN_USD")) {

					String beneficiaryName = detail.getFirstName() + " " + detail.getLastName() + " "
							+ detail.getMiddleName();
					NgnBankTransferDomRequest request = new NgnBankTransferDomRequest();

					String mobileNumberCode = "+234";

					String mobileNumber = mobileNumberCode + user.get().getPhone().substring(1);

					List<NgnBankTransferDomRequestMeta> metas = new ArrayList<>();

					NgnBankTransferDomRequestMeta req = new NgnBankTransferDomRequestMeta();

					req.setFirst_name(detail.getFirstName());
					req.setLast_name(detail.getLastName());
					req.setEmail(user.get().getEmailAddress());
					req.setBeneficiary_country("NG");
					req.setMobile_number(mobileNumber);
					req.setSender(beneficiaryName);
					req.setMerchant_name("Flutterwave");

					metas.add(req);

					request.setAccount_number(detail.getAccountNumber());
					request.setAccount_bank(detail.getBankId());
					request.setNarration(fmw.getNarration());
					request.setAmount(fmw.getAmount());
					request.setReference(RandomIdGenerator.generateId(15));
					request.setCurrency("USD");
					request.setDebit_currency("USD");
					request.setBeneficiary_name(beneficiaryName);
					request.setMeta(metas);

					try {

						NgnBankTransferDomResponse response = flutterwaveService.ngnDomBankTransfer(request);

						if (response.getStatus().equals("success")) {
							
							model.addAttribute("message", "Operation Successful, Transfer Queued Successfully.");
						} else {
							model.addAttribute("message", "Transaction was not successful, Try again.");
						}

					} catch (Exception e) {
						e.printStackTrace();
						model.addAttribute("message", "Something went wrong. Message: " + e.getMessage());
						System.out.println("===>>> Exception: " + e.getMessage());
					}

				} else if (fmw.getCountryCode().equals("FCMB_NGN_USD")) {

					String beneficiaryName = detail.getFirstName() + " " + detail.getLastName() + " "
							+ detail.getMiddleName();

					String mobileNumberCode = "+234";

					String mobileNumber = mobileNumberCode + user.get().getPhone().substring(1);

					List<NgnBankTransferDomFcmbRequestMeta> metas = new ArrayList<>();

					NgnBankTransferDomFcmbRequestMeta req = new NgnBankTransferDomFcmbRequestMeta();
					req.setEmail(user.get().getEmailAddress());
					req.setBeneficiary_country("NG");
					req.setMobile_number(mobileNumber);
					req.setSender(beneficiaryName);
					req.setBeneficiary_occupation(fmw.getOccupation());
					req.setRecipient_address(user.get().getAddress());
					req.setSender_country(user.get().getCountry());
					req.setSender_id_number(fmw.getIdentificationNumber());
					req.setSender_id_type(fmw.getIdentificationType());
					req.setSender_id_expiry(fmw.getIdentificationExpiry());
					req.setSender_mobile_number(mobileNumber);
					req.setSender_address(user.get().getAddress());
					req.setSender_occupation(fmw.getOccupation());
					req.setSender_beneficiary_relationship("My Account");
					req.setTransfer_purpose(fmw.getNarration());

					metas.add(req);

					NgnBankTransferDomFcmbRequest request = new NgnBankTransferDomFcmbRequest();
					request.setAccount_number(detail.getAccountNumber());
					request.setAccount_bank(detail.getBankId());
					request.setNarration(fmw.getNarration());
					request.setAmount(fmw.getAmount());
					request.setReference(RandomIdGenerator.generateId(15));
					request.setCurrency("USD");
					request.setBeneficiary_name(beneficiaryName);
					request.setMeta(metas);

					try {

						NgnBankTransferDomResponse response = flutterwaveService.ngnDomFcmbBankTransfer(request);

						if (response.getStatus().equals("success")) {

							model.addAttribute("message", "Operation Successful, Transfer Queued Successfully.");
						} else {
							model.addAttribute("message", "Transaction was not successful, Try again.");
						}

					} catch (Exception e) {
						e.printStackTrace();
						model.addAttribute("message", "Something went wrong. Message: " + e.getMessage());
						System.out.println("===>>> Exception: " + e.getMessage());
					}

				} else if (fmw.getCountryCode().equals("UNFI_NGN_USD")) {

					String beneficiaryName = detail.getFirstName() + " " + detail.getLastName() + " "
							+ detail.getMiddleName();

					String mobileNumberCode = "+234";

					String mobileNumber = mobileNumberCode + user.get().getPhone().substring(1);

					List<NgnBankTransferDomUNFIRequestMeta> metas = new ArrayList<>();

					NgnBankTransferDomUNFIRequestMeta req = new NgnBankTransferDomUNFIRequestMeta();

					req.setEmail(user.get().getEmailAddress());
					req.setBeneficiary_country("NG");
					req.setMobile_number(mobileNumber);
					req.setSender(beneficiaryName);
					req.setBeneficiary_occupation(fmw.getOccupation());
					req.setRecipient_address(user.get().getAddress());
					req.setSender_country(user.get().getCountry());
					req.setSender_city(fmw.getCity());
					req.setSender_id_number(fmw.getIdentificationNumber());
					req.setSender_id_type(fmw.getIdentificationType());
					req.setSender_id_expiry(fmw.getIdentificationExpiry());
					req.setSender_mobile_number(mobileNumber);
					req.setSender_address(user.get().getAddress());
					req.setSender_occupation(fmw.getOccupation());
					req.setSender_beneficiary_relationship("My Account");
					req.setTransfer_purpose(fmw.getNarration());

					metas.add(req);

					NgnBankTransferDomUNFIRequest request = new NgnBankTransferDomUNFIRequest();
					request.setAccount_number(detail.getAccountNumber());
					request.setAccount_bank(detail.getBankId());
					request.setNarration(fmw.getNarration());
					request.setAmount(fmw.getAmount());
					request.setReference(RandomIdGenerator.generateId(15));
					request.setCurrency("USD");
					request.setBeneficiary_name(beneficiaryName);
					request.setMeta(metas);

					try {

						NgnBankTransferDomResponse response = flutterwaveService.ngnDomUnfiBankTransfer(request);

						if (response.getStatus().equals("success")) {

							model.addAttribute("message", "Operation Successful, Transfer Queued Successfully.");
						} else {
							model.addAttribute("message", "Transaction was not successful, Try again.");
						}

					} catch (Exception e) {
						e.printStackTrace();
						model.addAttribute("message", "Something went wrong. Message: " + e.getMessage());
						System.out.println("===>>> Exception: " + e.getMessage());
					}

				} else if (fmw.getCountryCode().equals("GHS") || fmw.getCountryCode().equals("UGX")
						|| fmw.getCountryCode().equals("ZMN") || fmw.getCountryCode().equals("TZS")) {

					String beneficiaryName = detail.getFirstName() + " " + detail.getLastName() + " "
							+ detail.getMiddleName();

					String mobileNumberCode = "+234";

					String mobileNumber = mobileNumberCode + user.get().getPhone().substring(1);

					GhsBankTransferRequest request = new GhsBankTransferRequest();

					request.setAccount_bank(detail.getBankId());
					request.setAccount_number(detail.getAccountNumber());
					request.setAmount(fmw.getAmount());
					request.setNarration(fmw.getNarration());
					request.setCurrency("GHS");
					request.setReference(RandomIdGenerator.generateId(15));
					request.setCallback_url(redirectUrl);
					request.setDestination_branch_code(detail.getBankBranchCode());
					request.setBeneficiary_name(beneficiaryName);

					try {
						GhsBankTransferResponse response = flutterwaveService.GhsBankTransfer(request);

						if (response.getStatus().equals("success")) {

							model.addAttribute("message", "Operation Successful, Transfer Queued Successfully.");
						} else {
							model.addAttribute("message", "Transaction was not successful, Try again.");
						}

					} catch (Exception e) {
						e.printStackTrace();
						model.addAttribute("message", "Something went wrong. Message: " + e.getMessage());
						System.out.println("===>>> Exception: " + e.getMessage());
					}

				} else if (fmw.getCountryCode().equals("KES")) {

					String beneficiaryName = detail.getFirstName() + " " + detail.getLastName() + " "
							+ detail.getMiddleName();

					String mobileNumberCode = "+234";

					String mobileNumber = mobileNumberCode + user.get().getPhone().substring(1);

					List<KesBankTransferRequestMeta> metas = new ArrayList<>();

					KesBankTransferRequestMeta req = new KesBankTransferRequestMeta();
					req.setSender(beneficiaryName);
					req.setSender_country("NG");
					req.setMobile_number(mobileNumber);

					metas.add(req);

					KesBankTransferRequest request = new KesBankTransferRequest();
					request.setAccount_bank(detail.getBankId());
					request.setAccount_number(detail.getAccountNumber());
					request.setAmount(fmw.getAmount());
					request.setNarration(fmw.getNarration());
					request.setCurrency("KES");
					request.setReference(RandomIdGenerator.generateId(15));
					request.setCallback_url(redirectUrl);
					request.setDebit_currency("USD");
					request.setBeneficiary_name(beneficiaryName);
					request.setMeta(metas);

					try {
						KesBankTransferResponse response = flutterwaveService.kesBankTransfer(request);

						if (response.getStatus().equals("success")) {

							model.addAttribute("message", "Operation Successful, Transfer Queued Successfully.");
						} else {
							model.addAttribute("message", "Transaction was not successful, Try again.");
						}

					} catch (Exception e) {
						e.printStackTrace();
						model.addAttribute("message", "Something went wrong. Message: " + e.getMessage());
						System.out.println("===>>> Exception: " + e.getMessage());
					}

				} else if (fmw.getCountryCode().equals("ZAR")) {

					String beneficiaryName = detail.getFirstName() + " " + detail.getLastName() + " "
							+ detail.getMiddleName();

					String mobileNumberCode = "+234";

					String mobileNumber = mobileNumberCode + user.get().getPhone().substring(1);

					List<ZarBankTransferRequestMeta> metas = new ArrayList<>();

					ZarBankTransferRequestMeta req = new ZarBankTransferRequestMeta();
					req.setFirst_name(user.get().getFirstName());
					req.setLast_name(user.get().getLastName());
					req.setEmail(user.get().getEmailAddress());
					req.setMobile_number(mobileNumber);
					req.setRecipient_address(user.get().getAddress());

					metas.add(req);

					ZarBankTransferRequest request = new ZarBankTransferRequest();
					request.setAccount_bank(detail.getBankId());
					request.setAccount_number(detail.getAccountNumber());
					request.setAmount(fmw.getAmount());
					request.setNarration(fmw.getNarration());
					request.setCurrency("ZAR");
					request.setReference(RandomIdGenerator.generateId(15));
					request.setCallback_url(redirectUrl);
					request.setDebit_currency("NGN");
					request.setBeneficiary_name(beneficiaryName);
					request.setMeta(metas);

					try {
						ZarBankTransferDomResponse response = flutterwaveService.zarBankTransfer(request);

						if (response.getStatus().equals("success")) {

							model.addAttribute("message", "Operation Successful, Transfer Queued Successfully.");
						} else {
							model.addAttribute("message", "Transaction was not successful, Try again.");
						}

					} catch (Exception e) {
						e.printStackTrace();
						model.addAttribute("message", "Something went wrong. Message: " + e.getMessage());
						System.out.println("===>>> Exception: " + e.getMessage());
					}

				} else {
					model.addAttribute("message", "Country does not exist");
				}

			} else {
				model.addAttribute("message", "Bank Detail does not exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Something went wrong " + e.getMessage());
			System.out.println("===>>> Exception: " + e.getMessage());
		}

		return "dashboards/payments/clientPaymentForm";
	}
	
	

	@GetMapping("/get-banks/{country}")
	public @ResponseBody List<Bank> getBanks(@PathVariable(name = "country") String country, Model model) {

		BankResponse response = flutterwaveService.getBank(country);
		List<BankResponseData> data = response.getData();

		List<Bank> banks = new ArrayList<>();

		for (BankResponseData bank : data) {
			Bank b = new Bank();
			b.setBankCode(bank.getCode());
			b.setBankName(bank.getName());

			banks.add(b);
		}

		return banks;
	}

	@GetMapping("/get-bank-branches/{bank}")
	public @ResponseBody List<BankBranchResponseData> getBankBranches(@PathVariable(name = "bank") String bank,
			Model model) {

		BankBranchResponse response = flutterwaveService.getBankBranch(bank);
		List<BankBranchResponseData> data = response.getData();
		return data;
	}

	@PostMapping("/add-freelancer-bank")
	public String addFreelancerBank(@ModelAttribute("AddBankDTO") AddBankDTO abt, BindingResult bindingResultModel,
			Model model, RedirectAttributes attributes) throws UnknownHostException {

		System.out.println("===>>> addBankDTO: " + abt);

		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);

		String[] branchCodeAndName = {};

		String branchCode = "";
		String branchName = "";
		String currency = "";

		switch (abt.getCountry()) {
		case "NG":
			currency = "NGN";
			break;
		case "GH":
			currency = "GHS";
			break;
		case "UG":
			currency = "UGX";
			break;
		case "ZM":
			currency = "ZMW";
			break;
		case "TZ":
			currency = "TZS";
			break;
		case "KE":
			currency = "KES";
			break;
		case "ZA":
			currency = "ZAR";
			break;
		default:
			break;
		}

		System.out.println("===>>> GetBankBranchCodeAndName: " + abt.getBankBranchCodeAndName());

		if (!abt.getBankBranchCodeAndName().isEmpty()) {
			branchCodeAndName = abt.getBankBranchCodeAndName().split("-", 2);

			branchCode = branchCodeAndName[0];
			branchName = branchCodeAndName[1];
		}

		String createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

		BankDetail detail = new BankDetail();
//		detail.setDetail_id(1);
		detail.setUser(userDetails.get());
		detail.setBankId(abt.getBankCode());
		detail.setBankBranchCode(branchCode);
		detail.setBankBranchName(branchName);
		detail.setAccountNumber(abt.getAccountNumber());
		detail.setFirstName(abt.getFirstName());
		detail.setLastName(abt.getLastName());
		detail.setMiddleName(abt.getMiddleName());
		detail.setCurrency(currency);
		detail.setMerchantName(abt.getMerchantName());
		detail.setRoutingNumber(abt.getRoutingNumber());
		detail.setSwiftCode(abt.getSwiftCode());
		detail.setEntryDate(createdDate);

		boolean isBankDetailsExist = new PaymentComponent().isBankDetailExist(userDetails.get().getUserId());

		if (!isBankDetailsExist) {
			bankDetailService.save(detail);
		} else {
			model.addAttribute("message", "Bank Detail already exist");
		}

		return "redirect:/freelancer-finances";

	}

}
