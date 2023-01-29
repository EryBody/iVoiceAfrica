package com.ivoiceafrica.ivoiceafrica.DTO;

public class JobStatusDTO {

	private int jobStatusId;
	private String jobStatusName;
	
	public int getJobStatusId() {
		return jobStatusId;
	}
	public void setJobStatusId(int jobStatusId) {
		this.jobStatusId = jobStatusId;
	}
	public String getJobStatusName() {
		return jobStatusName;
	}
	public void setJobStatusName(String jobStatusName) {
		this.jobStatusName = jobStatusName;
	}
	@Override
	public String toString() {
		return "JobStatusDTO [jobStatusId=" + jobStatusId + ", jobStatusName=" + jobStatusName + "]";
	}
	
}
