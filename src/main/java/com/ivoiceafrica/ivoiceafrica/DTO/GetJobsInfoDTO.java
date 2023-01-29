package com.ivoiceafrica.ivoiceafrica.DTO;

public class GetJobsInfoDTO {

	private String jobId;
	private String clientId;

	public GetJobsInfoDTO() {

	}

	public GetJobsInfoDTO(String jobId, String clientId) {
		this.jobId = jobId;

		this.clientId = clientId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Override
	public String toString() {
		return "GetJobsInfoDTO [jobId=" + jobId + ", clientId=" + clientId + "]";
	}

}
