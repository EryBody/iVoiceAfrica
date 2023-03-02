package com.ivoiceafrica.ivoiceafrica.DTO;

import java.util.List;

public class AdminPageWordCountDTO {

	private List<String> workAttachmentId;
	private List<String> pageCount;
	private List<String> workCount;
	private List<String> timerCount;
	private String workId;
	private String workOrderStatus;

	public List<String> getWorkAttachmentId() {
		return workAttachmentId;
	}

	public void setWorkAttachmentId(List<String> workAttachmentId) {
		this.workAttachmentId = workAttachmentId;
	}

	public List<String> getPageCount() {
		return pageCount;
	}

	public void setPageCount(List<String> pageCount) {
		this.pageCount = pageCount;
	}

	public List<String> getWorkCount() {
		return workCount;
	}

	public void setWorkCount(List<String> workCount) {
		this.workCount = workCount;
	}

	public List<String> getTimerCount() {
		return timerCount;
	}

	public void setTimerCount(List<String> timerCount) {
		this.timerCount = timerCount;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getWorkOrderStatus() {
		return workOrderStatus;
	}

	public void setWorkOrderStatus(String workOrderStatus) {
		this.workOrderStatus = workOrderStatus;
	}

	@Override
	public String toString() {
		return "AdminPageWordCountDTO [workAttachmentId=" + workAttachmentId + ", pageCount=" + pageCount
				+ ", workCount=" + workCount + ", timerCount=" + timerCount + ", workId=" + workId
				+ ", workOrderStatus=" + workOrderStatus + "]";
	}

}
