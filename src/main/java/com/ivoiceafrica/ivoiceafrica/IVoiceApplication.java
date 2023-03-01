package com.ivoiceafrica.ivoiceafrica;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.ivoiceafrica.ivoiceafrica.components.models.ClientComponentModel;
import com.ivoiceafrica.ivoiceafrica.repository.UserRepository;
import com.ivoiceafrica.ivoiceafrica.service.BankDetailService;
import com.ivoiceafrica.ivoiceafrica.service.CustomUserDetailService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryAttachmentService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryStatusService;
import com.ivoiceafrica.ivoiceafrica.service.FlutterwaveService;
import com.ivoiceafrica.ivoiceafrica.service.FreelancerPricingService;
import com.ivoiceafrica.ivoiceafrica.service.ProposalService;
import com.ivoiceafrica.ivoiceafrica.service.ProposalStatusService;
import com.ivoiceafrica.ivoiceafrica.service.STypePricingService;
import com.ivoiceafrica.ivoiceafrica.service.STypeService;
import com.ivoiceafrica.ivoiceafrica.service.UserStatusService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderAttachmentService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderService;


@SpringBootApplication
@ComponentScan({ "com.ivoiceafrica.ivoiceafrica"})
public class IVoiceApplication implements CommandLineRunner {

	@Autowired
	UserStatusService userStatusService;

	@Autowired
	ClientComponentModel clientComponentModel;

	@Autowired
	STypeService service;

	@Autowired
	FreelancerPricingService freelancerPricingService;

	@Autowired
	STypePricingService pricingService;
	
	@Autowired
	DeliveryService deliveryService;

	@Autowired
	DeliveryStatusService deliveryStatusService;

	@Autowired
	DeliveryAttachmentService deliveryAttachmentService;

	@Autowired
	ProposalService proposalService;

	@Autowired
	ProposalStatusService proposalStatusService;

	@Autowired
	CustomUserDetailService userService;
	
	@Autowired
	WorkOrderService workOrderService;
	
	@Autowired
	WorkOrderAttachmentService workOrderAttachmentService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FlutterwaveService flwService;
	
	@Autowired
	BankDetailService bankDetailService;


	public static void main(String[] args) {
		SpringApplication.run(IVoiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		Optional<UserStatus> userStatus = userStatusService.findById(2);
//		System.out.println("===>>> userStatus: "+userStatus);

//		List<FreelancerServiceTypePricingDTO> pricingsDTO = new ArrayList<>();
//
//		Optional<ServiceType> serviceType = service.findById("ST102");
//		Optional<User> opUser = userService.findFirstUserByUsername("donpre@gmail.com");
//		System.out.println("===>>> serviceType: " + serviceType.get());
//
//		List<ServiceTypePricing> servicePricings = pricingService
//				.findServiceTypePricingListByServiceType(serviceType.get());
//		System.out.println("===>>> servicePricings: " + servicePricings);
//
//		for (ServiceTypePricing serviceTypePricing : servicePricings) {
//
//			Optional<FreelancerServicePricing> freelancerPricing = freelancerPricingService
//					.findFirstFreelancerServicePricingByServiceTypePricingAndUser(serviceTypePricing, opUser.get());
//
//			System.out.println("===>>> freelancerPricing: " + freelancerPricing.get());
//
//			FreelancerServiceTypePricingDTO freelancerPricingDTO = new FreelancerServiceTypePricingDTO();
//
//			freelancerPricingDTO.setUser(freelancerPricing.get().getUser());
//			freelancerPricingDTO.setServiceTypePricing(freelancerPricing.get().getServiceTypePricing());
//			freelancerPricingDTO.setMinPrice(freelancerPricing.get().getMinPrice());
//			freelancerPricingDTO.setMaxPrice(freelancerPricing.get().getMaxPrice());
//
//			pricingsDTO.add(freelancerPricingDTO);
//		}
//
//		System.out.println("===>>> pricingsDTO: " + pricingsDTO);
		
//		String sd = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//		Date startDate;
//		try {
//			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(sd);
//			Date endDate = GetEndDate.calculateNextSettleDate(startDate, 2);
//			
//			String ed = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
//			
//			System.out.println("EndDate: "+endDate);
//			System.out.println("==>> ED: "+ed);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//
//		String currentProposalDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//		
//		List<WorkOrdersDelivery> overdueDelivery = deliveryService.findOverdueWorkOrdersDelivery("2022-12-10",
//				33);
//		
//		System.out.println("===>>> overdueDelivery: "+overdueDelivery);
//		
//		Optional<User> userDetails = userService.findFirstUserByUsername("donpre@gmail.com");
//
//		List<WorkOrdersDelivery> inProgressDelivery = deliveryService
//				.findWorkOrdersDeliveryByDeliveryStatusAndUserOrderByCreatedDateDesc(
//						deliveryStatusService.findById(5).get(), userDetails.get());
//		
//		System.out.println("===>>> inProgressDelivery: "+inProgressDelivery);
//		
//		int limitOfRecord = 5;
//		List<Proposal> proposalByLimit = proposalService.findProposalByLimitAndStatusAndCurrentDate(
//				userDetails.get().getUserId(), proposalStatusService.findById(11).get().getProposalStatusId(),
//				currentProposalDate, limitOfRecord);
//		
//		
//		System.out.println("===>>> proposalByLimits: "+proposalByLimit);
		
		
//		Optional<DeliveryStatus> deliveryStatus = deliveryStatusService.findById(9); //9 means finished
//		String userId = "donpre@gmail.com";
//		Optional<User> userDetails = userService.findFirstUserByUsername(userId);
//		
//		List<WorkOrdersDelivery> finishedDelivery = deliveryService.findWorkOrdersDeliveryByDeliveryStatusAndUserOrderByCreatedDateDesc(deliveryStatus.get(),
//				userDetails.get());
//		
//		System.out.println("===>>> finishedDelivery: "+finishedDelivery.toString());
		
		
		
//		Optional<WorkOrder> workOrderInfo = workOrderService.findFirstWorkOrderByWorkId("j1u102");
//		System.out.println("===>>> workOrderInfo: "+workOrderInfo);
//		
//		List<WorkOrderAttachment> workAttachments = workOrderAttachmentService
//				.findWorkOrderAttachmentByWorkOrder(workOrderInfo.get());
//		
//		System.out.println("===>>> workAttachments: "+workAttachments);
//
//		Optional<WorkOrderAttachment> workAttachmentDetails = workOrderAttachmentService
//				.findFirstWorkOrderAttachmentByWorkOrder(workOrderInfo.get());
//		
//		System.out.println("===>>> workAttachmentDetails: "+workAttachmentDetails);
		
//		String userId = "donpre@gmail.com";
//		Optional<User> user = userService.findFirstUserByUsername(userId);
//
//	
//		// Delivery Status, Work Delivery and Delivery Attachment
//		Optional<DeliveryStatus> opDeliveryStatus = deliveryStatusService.findById(5);// 5 means in Progress
//		List<WorkOrdersDelivery> workOrderDelivery = deliveryService
//				.findWorkOrdersDeliveryByUserAndDeliveryStatusOrderByCreatedDateDesc(user.get(), opDeliveryStatus.get());
//		
//		System.out.println("===>>> workOrderDelivery: "+workOrderDelivery);
		
//		List<User> users = userRepository.findUserByRoleAndUsername(3, "chima@gmail.com");
//		System.out.println("====>>> users: "+users);
		
		
//		BankResponse response = flwService.getBank("NG");
//		System.out.println("===>>>RES: "+response);
		
//		BankBranchResponse bankBranch= flwService.getBankBranch("280");
//		System.out.println("===>>> bankBranch: "+bankBranch);
		
//		NgnBankTransferRequest req = new NgnBankTransferRequest();
//		req.setAccount_bank("044");
//		req.setAccount_number("0690000040");
//		req.setAmount(200);
//		req.setNarration("Payment for things");
//		req.setCurrency("NGN");
//		req.setReference("jh678b3kolfs");
//		req.setCallback_url("https://webhook.site/b3e505b0-fe02-430e-a538-22bbbce8ce0d");
//		req.setDebit_currency("NGN");
//		
//		BankTransferResponse res = flwService.ngnBankTransfer(req);
//		System.out.println("===>>> res: "+res);
		
//		BankResponse res = flwService.getBank("NG");
//		
//		//Add Banks from flutterwave
//		Bank bank = new Bank();
//		bank.setBankId(0);
//		bank.setBankCode("");
//		bank.setBankName("");
		
//		String language = clientComponentModel.translateIdToLanguage(2);
//		System.out.println("===>>> Language: "+language);

	}

}
