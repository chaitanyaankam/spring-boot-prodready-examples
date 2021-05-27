package com.spring.jasper.request;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class ReportDataRequest extends ReportRequest {
	
	private static final long serialVersionUID = 1L;
	
	@Schema(description = "Report Data in JSON Array", required = true)
	private List<?> data;

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}
	
}
