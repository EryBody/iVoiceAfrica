package com.ivoiceafrica.ivoiceafrica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerServiceTypePricingDTO;
import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.auth.entity.UserStatus;
import com.ivoiceafrica.ivoiceafrica.components.models.ClientComponentModel;
import com.ivoiceafrica.ivoiceafrica.entity.DeliveryStatus;
import com.ivoiceafrica.ivoiceafrica.entity.FreelancerServicePricing;
import com.ivoiceafrica.ivoiceafrica.entity.Proposal;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceType;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceTypePricing;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrdersDelivery;
import com.ivoiceafrica.ivoiceafrica.service.CustomUserDetailService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryAttachmentService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryStatusService;
import com.ivoiceafrica.ivoiceafrica.service.FreelancerPricingService;
import com.ivoiceafrica.ivoiceafrica.service.ProposalService;
import com.ivoiceafrica.ivoiceafrica.service.ProposalStatusService;
import com.ivoiceafrica.ivoiceafrica.service.STypePricingService;
import com.ivoiceafrica.ivoiceafrica.service.STypeService;
import com.ivoiceafrica.ivoiceafrica.service.UserStatusService;
import com.ivoiceafrica.ivoiceafrica.utility.GetEndDate;

@SpringBootApplication
@ComponentScan({ "com.ivoiceafrica.ivoiceafrica" })
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
		
		
		Optional<DeliveryStatus> deliveryStatus = deliveryStatusService.findById(9); //9 means finished
		String userId = "donpre@gmail.com";
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);
		
		List<WorkOrdersDelivery> finishedDelivery = deliveryService.findWorkOrdersDeliveryByDeliveryStatusAndUserOrderByCreatedDateDesc(deliveryStatus.get(),
				userDetails.get());
		
		System.out.println("===>>> finishedDelivery: "+finishedDelivery.toString());

	}

}
