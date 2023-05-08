package com.ivoiceafrica.ivoiceafrica.DTO;

public class FilterBidsRequestDTO {

	private String workOrderId;
	private String filterType;
	
	public String getWorkOrderId() {
		return workOrderId;
	}
	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}
	public String getFilterType() {
		return filterType;
	}
	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}
	@Override
	public String toString() {
		return "FilterBidsRequestDTO [workOrderId=" + workOrderId + ", filterType=" + filterType + "]";
	}
	
}
