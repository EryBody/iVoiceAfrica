package com.ivoiceafrica.ivoiceafrica.components.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerProfileLanguageDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerServiceTypePricingDTO;
import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.DeliveryAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.FreelancerServicePricing;
import com.ivoiceafrica.ivoiceafrica.entity.Language;
import com.ivoiceafrica.ivoiceafrica.entity.Proposal;
import com.ivoiceafrica.ivoiceafrica.entity.ProposalStatus;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceLanguages;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceRendered;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceType;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceTypePricing;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrdersDelivery;
import com.ivoiceafrica.ivoiceafrica.service.CustomUserDetailService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryAttachmentService;
import com.ivoiceafrica.ivoiceafrica.service.FreelancerPricingService;
import com.ivoiceafrica.ivoiceafrica.service.LanguageService;
import com.ivoiceafrica.ivoiceafrica.service.ProposalService;
import com.ivoiceafrica.ivoiceafrica.service.ProposalStatusService;
import com.ivoiceafrica.ivoiceafrica.service.SLanguageService;
import com.ivoiceafrica.ivoiceafrica.service.SRenderedService;
import com.ivoiceafrica.ivoiceafrica.service.STypePricingService;
import com.ivoiceafrica.ivoiceafrica.service.STypeService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderAttachmentService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderService;

@Component("ClientComponentModel")
public class ClientComponentModel {

	@Autowired
	WorkOrderAttachmentService attachmentService;

	@Autowired
	WorkOrderService orderService;

	@Autowired
	LanguageService languageService;

	@Autowired
	ProposalService proposalService;

	@Autowired
	ProposalStatusService proposalStatusService;

	@Autowired
	SRenderedService serviceRenderedService;

	@Autowired
	SLanguageService serviceLanguageService;

	@Autowired
	STypePricingService sTypePricing;

	@Autowired
	FreelancerPricingService freelancerPricingService;

	@Autowired
	CustomUserDetailService userService;

	@Autowired
	STypeService sTypeService;

	@Autowired
	DeliveryAttachmentService deliveryAttachmentService;
	
	@Autowired HttpSession session;

	public String getWorkAttachments(String workOrderId) {

		Optional<WorkOrder> order = orderService.findById(workOrderId);

		List<WorkOrderAttachment> workAttachments = attachmentService.findWorkOrderAttachmentByWorkOrder(order.get());

		int workAttachmentSize = workAttachments.size();

		return String.valueOf(workAttachmentSize);
	}

	public String translateIdToLanguage(@Nullable int languageId) {

		Optional<Language> languageName = Optional.empty();
		try {

			languageName = languageService.findById(languageId);

		} catch (Exception ex) {
			System.out.println("===>>> Exception: " + ex.getMessage());
			languageName = Optional.empty();
		}

		return languageName.get().getLanguage();
	}

	public String getFreelancersForBids(String workOrderId) {

		Optional<ProposalStatus> proposalStatus = proposalStatusService.findById(9);// 9 Means Freelancer Request Sent
																					// Status

		Optional<WorkOrder> order = orderService.findById(workOrderId);

		List<Proposal> clientProposals = proposalService
				.findProposalByWorkOrderAndProposalStatusOrderByCreatedDate(order.get(), proposalStatus.get());

		int clientProposalSize = clientProposals.size();

		return String.valueOf(clientProposalSize);
	}

	public ServiceLanguages getFreelancerFirstServiceLanguage(User user) {

		Optional<ServiceRendered> freelancerServiceRendered = serviceRenderedService
				.findFirstServiceRenderedByUser(user);

		Optional<ServiceLanguages> serviceLanguage = serviceLanguageService
				.findFirstServiceLanguageByServiceRendered(freelancerServiceRendered.get());

		return serviceLanguage.get();
	}

	public List<ServiceLanguages> getFirst3FreelancerServiceLanguage(User user) {

		Optional<ServiceRendered> freelancerServiceRendered = serviceRenderedService
				.findFirstServiceRenderedByUser(user);

		List<ServiceLanguages> serviceLanguages = serviceLanguageService
				.findFirst3ServiceLanguageByServiceRendered(freelancerServiceRendered.get());

		return serviceLanguages;
	}

	public List<FreelancerProfileLanguageDTO> get3FreelancerServiceLanguage(ServiceRendered serviceRenderedId) {

		List<FreelancerProfileLanguageDTO> profileDTO = new ArrayList<>();

		List<ServiceLanguages> serviceLanguages = serviceLanguageService
				.findFirst3ServiceLanguageByServiceRendered(serviceRenderedId);

		for (ServiceLanguages serviceLanguage : serviceLanguages) {
			FreelancerProfileLanguageDTO profileLanguage = new FreelancerProfileLanguageDTO();

			profileLanguage.setLanguageId(serviceLanguage.getLanguageId());
			profileLanguage.setLanguageDesc(serviceLanguage.getLanguageDesc());
			profileLanguage.setLanguageUpload(serviceLanguage.getLanguageUpload());
			profileLanguage.setServiceRendered(serviceLanguage.getServiceRendered());

			profileDTO.add(profileLanguage);
		}

		return profileDTO;
	}

	public List<DeliveryAttachment> getListOfDeliveryAttachment(WorkOrdersDelivery workOrderDelivery) {

		List<DeliveryAttachment> deliveryAttachment = deliveryAttachmentService
				.findDeliveryAttachmentByWorkOrderDelivery(workOrderDelivery);

		return deliveryAttachment;
	}
	
	public List<WorkOrderAttachment> getAttachmentForDelivery(String workOrderId) {
		
		Optional<WorkOrder> opWorkOrder = orderService.findById(workOrderId);

		List<WorkOrderAttachment> deliveryAttachment = attachmentService
				.findWorkOrderAttachmentByWorkOrder(opWorkOrder.get());
		
		return deliveryAttachment;
	}
	
	
	public String getAttachmentSizeForDelivery(String workOrderId) {
		
		Optional<WorkOrder> opWorkOrder = orderService.findById(workOrderId);

		List<WorkOrderAttachment> deliveryAttachment = attachmentService
				.findWorkOrderAttachmentByWorkOrder(opWorkOrder.get());
		return String.valueOf(deliveryAttachment.size());
	}

	
	public String getSizeOfDeliveryAttachment(WorkOrdersDelivery workOrderDelivery) {

		List<DeliveryAttachment> deliveryAttachment = deliveryAttachmentService
				.findDeliveryAttachmentByWorkOrderDelivery(workOrderDelivery);
		return String.valueOf(deliveryAttachment.size());
	}

	public String getNoOfServiceRendered(User user) {
		List<ServiceRendered> serviceRendered = serviceRenderedService.findServiceRenderedListByUser(user);

		int serviceRenderedSize = serviceRendered.size();

		return String.valueOf(serviceRenderedSize);
	}
	
	public String checkProfile() {
		
		String userRole = (String)session.getAttribute("userRole");
		String userRoleName = "";
		
		if(userRole.equals("ROLE_ADMIN")) {
			userRoleName = "Administrator";
		}
		else if(userRole.equals("ROLE_SUPERVISOR")) {
			userRoleName = "Supervisor";
		}
		else if(userRole.equals("ROLE_CLIENT")) {
			userRoleName = "Client";
		}
		else if(userRole.equals("ROLE_FREELANCER")) {
			userRoleName = "Freelancer";
		}
		
		return String.valueOf(userRoleName);
	}

	
	public List<FreelancerServiceTypePricingDTO> getFreelancerPricing(ServiceType serviceType, String userId) {

		List<FreelancerServiceTypePricingDTO> pricingsDTO = new ArrayList<>();

		try {

			Optional<User> opUser = userService.findFirstUserByUsername(userId);
			System.out.println("\n===>>> opUser: " + opUser.get());

			List<ServiceTypePricing> servicePricings = sTypePricing
					.findServiceTypePricingListByServiceType(serviceType);

			System.out.println("\n===>>> servicePricings: " + servicePricings.size());

			for (ServiceTypePricing serviceTypePricing : servicePricings) {

				Optional<FreelancerServicePricing> freelancerPricing = freelancerPricingService
						.findFirstFreelancerServicePricingByServiceTypePricingAndUser(serviceTypePricing, opUser.get());

				if (freelancerPricing.isPresent()) {

					FreelancerServiceTypePricingDTO freelancerPricingDTO = new FreelancerServiceTypePricingDTO();

					freelancerPricingDTO.setUser(freelancerPricing.get().getUser());
					freelancerPricingDTO.setServiceTypePricing(freelancerPricing.get().getServiceTypePricing());
					freelancerPricingDTO.setMinPrice(freelancerPricing.get().getMinPrice());
					freelancerPricingDTO.setMaxPrice(freelancerPricing.get().getMaxPrice());

					System.out.println("===>>> freelancerPricingDTO: " + freelancerPricingDTO);

					pricingsDTO.add(freelancerPricingDTO);
				}

			}

		} catch (Exception ex) {
			System.out.println("===>>> Exception in component model: " + ex.getMessage());
//			return pricingsDTO;
		}

		return pricingsDTO;
	}

}
