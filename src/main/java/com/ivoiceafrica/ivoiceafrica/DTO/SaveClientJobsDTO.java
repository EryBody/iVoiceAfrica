package com.ivoiceafrica.ivoiceafrica.DTO;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class SaveClientJobsDTO {

	private String[] workOrderId;
	private String[] deliveryId;
	private MultipartFile[] multipartList;
	private String[] deliveryAttachment;

	public SaveClientJobsDTO() {
	}

	public SaveClientJobsDTO(String[] workOrderId, String[] deliveryId, MultipartFile[] multipartList,
			String[] deliveryAttachment) {
		this.workOrderId = workOrderId;
		this.deliveryId = deliveryId;
		this.multipartList = multipartList;
		this.deliveryAttachment = deliveryAttachment;
	}

	public String[] getWorkOrderId() {
		return workOrderId;
	}

	public void setWorkOrderId(String[] workOrderId) {
		this.workOrderId = workOrderId;
	}

	public String[] getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(String[] deliveryId) {
		this.deliveryId = deliveryId;
	}

	public MultipartFile[] getMultipartList() {
		return multipartList;
	}

	public void setMultipartList(MultipartFile[] multipartList) {
		this.multipartList = multipartList;
	}

	public String[] getDeliveryAttachment() {
		return deliveryAttachment;
	}

	public void setDeliveryAttachment(String[] deliveryAttachment) {
		this.deliveryAttachment = deliveryAttachment;
	}

	@Override
	public String toString() {
		return "SaveClientJobsDTO [workOrderId=" + Arrays.toString(workOrderId) + ", deliveryId="
				+ Arrays.toString(deliveryId) + ", multipartList=" + Arrays.toString(multipartList)
				+ ", deliveryAttachment=" + Arrays.toString(deliveryAttachment) + "]";
	}

}
