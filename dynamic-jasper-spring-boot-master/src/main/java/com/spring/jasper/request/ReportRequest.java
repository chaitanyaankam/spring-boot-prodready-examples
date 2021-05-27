package com.spring.jasper.request;

import java.io.Serializable;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;

public class ReportRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Schema(description = "Report File Name", example = "users_report", required = true)
	@NotBlank
	private String fileName;
	
	@Schema(description = "Page Title", example = "USERS REPORT-0909011", required = true)
	@NotBlank
	private String pageTitle;
	
	@Schema(description = "Excel sheet name", example = "USERS", required = true)
	@NotBlank
	private String sheetName;
	
	private Map<String, String> columnsFieldsMap;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public Map<String, String> getColumnsFieldsMap() {
		return columnsFieldsMap;
	}

	public void setColumnsFieldsMap(Map<String, String> columnsFieldsMap) {
		this.columnsFieldsMap = columnsFieldsMap;
	}
	
}
