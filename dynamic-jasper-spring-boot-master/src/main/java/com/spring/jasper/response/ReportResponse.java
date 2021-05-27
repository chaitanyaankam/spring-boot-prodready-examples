package com.spring.jasper.response;

public class ReportResponse {
	
	private String requestId;
	
	private String downloadURL;
	
	private long transcationTime;
	
	public long getTranscationTime() {
		return transcationTime;
	}
	public void setTranscationTime(long transcationTime) {
		this.transcationTime = transcationTime;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getDownloadURL() {
		return downloadURL;
	}
	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}
	
}
