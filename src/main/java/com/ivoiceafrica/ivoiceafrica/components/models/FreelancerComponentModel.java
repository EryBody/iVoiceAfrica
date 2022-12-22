package com.ivoiceafrica.ivoiceafrica.components.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ivoiceafrica.ivoiceafrica.DTO.WorkAttachmentSourceDestinationDTO;
import com.ivoiceafrica.ivoiceafrica.entity.DeliveryStatus;
import com.ivoiceafrica.ivoiceafrica.entity.ProposalStatus;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderStatus;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryStatusService;
import com.ivoiceafrica.ivoiceafrica.service.LanguageService;
import com.ivoiceafrica.ivoiceafrica.service.ProposalStatusService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderAttachmentService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderStatusService;

@Component("FreelancerComponentModel")
public class FreelancerComponentModel {

	@Autowired
	DeliveryStatusService deliveryStatusService;

	@Autowired
	WorkOrderStatusService workOrderStatusService;

	@Autowired
	ProposalStatusService proposalStatusService;

	@Autowired
	WorkOrderAttachmentService attachmentService;

	@Autowired
	WorkOrderService orderService;

	@Autowired
	LanguageService languageService;
	
	@Autowired
	ClientComponentModel clientModel;

	public String getDeliveryStatus(int deliveryId) {
		Optional<DeliveryStatus> status = deliveryStatusService.findById(deliveryId);
		return status.get().getStatus();
	}

	public String getWorkOrderStatus(int workOrderId) {
		Optional<WorkOrderStatus> status = workOrderStatusService.findById(workOrderId);
		return status.get().getStatus();
	}
	
	public WorkOrder getWorkOrderStatusId(String workOrderId) {
		Optional<WorkOrder> status = orderService.findById(workOrderId);
		return status.get();
	}

	public String getProposalStatus(int proposalStatusId) {
		Optional<ProposalStatus> status = proposalStatusService.findById(proposalStatusId);
		return status.get().getStatus();
	}

	public WorkAttachmentSourceDestinationDTO getWorkAttachmentLanguagesForSourceAndDestination(String workOrderId) {

		WorkAttachmentSourceDestinationDTO workOrderLanguage = new WorkAttachmentSourceDestinationDTO();

		Optional<WorkOrder> opWorkOrder = orderService.findById(workOrderId);

		List<WorkOrderAttachment> deliveryAttachments = new ArrayList<>();

		try {
			deliveryAttachments = attachmentService
					.findWorkOrderAttachmentByWorkOrderWithLimit(opWorkOrder.get().getWorkId(), 3); // 3 is the Size of list of
																									// languages to
																									// display
		} catch (Exception ex) {
			System.out.println("Exception in Freelancer Controller: "+ex.getMessage());
			deliveryAttachments = attachmentService
					.findWorkOrderAttachmentByWorkOrderWithLimit(opWorkOrder.get().getWorkId(), 1); // 1 is the Size of list of
																									// languages to
																									// display
		}

		List<String> sourceList = new ArrayList<>();
		List<String> destinationList = new ArrayList<>();

		for (WorkOrderAttachment attach : deliveryAttachments) {
			sourceList.add(clientModel.translateIdToLanguage(Integer.parseInt(attach.getSource())));
			destinationList.add(clientModel.translateIdToLanguage(Integer.parseInt(attach.getDestination())));
		}

		workOrderLanguage.setSourceLanguage(sourceList);
		workOrderLanguage.setDestinationLanguage(destinationList);

		return workOrderLanguage;
	}
	
	
	public WorkAttachmentSourceDestinationDTO getWorkAttachmentLanguagesForSourceAndDestination(String workOrderId, int size) {

		WorkAttachmentSourceDestinationDTO workOrderLanguage = new WorkAttachmentSourceDestinationDTO();

		Optional<WorkOrder> opWorkOrder = orderService.findById(workOrderId);

		List<WorkOrderAttachment> deliveryAttachments = new ArrayList<>();

		try {
			deliveryAttachments = attachmentService
					.findWorkOrderAttachmentByWorkOrderWithLimit(opWorkOrder.get().getWorkId(), size); // 3 is the Size of list of
																									// languages to
																									// display
		} catch (Exception ex) {
			System.out.println("Exception in Freelancer Controller: "+ex.getMessage());
			deliveryAttachments = attachmentService
					.findWorkOrderAttachmentByWorkOrderWithLimit(opWorkOrder.get().getWorkId(), 1); // 1 is the Size of list of
																									// languages to
																									// display
		}

		List<String> sourceList = new ArrayList<>();
		List<String> destinationList = new ArrayList<>();

		for (WorkOrderAttachment attach : deliveryAttachments) {
			sourceList.add(clientModel.translateIdToLanguage(Integer.parseInt(attach.getSource())));
			destinationList.add(clientModel.translateIdToLanguage(Integer.parseInt(attach.getDestination())));
		}

		workOrderLanguage.setSourceLanguage(sourceList);
		workOrderLanguage.setDestinationLanguage(destinationList);

		return workOrderLanguage;
	}
	
	

}
