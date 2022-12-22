package com.ivoiceafrica.ivoiceafrica.DTO;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class SaveClientJobsDTO {

	private String[] workOrderId;
	private String[] deliveryId;
	private MultipartFile[] multipartList;

	public SaveClientJobsDTO() {
	}

	public SaveClientJobsDTO(String[] workOrderId, String[] deliveryId, MultipartFile[] multipartList) {
		this.workOrderId = workOrderId;
		this.deliveryId = deliveryId;
		this.multipartList = multipartList;
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

	@Override
	public String toString() {
		return "SaveClientJobsDTO [workOrderId=" + workOrderId + ", deliveryId=" + deliveryId + ", multipartList="
				+ Arrays.toString(multipartList) + "]";
	}

}
