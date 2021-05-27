package com.spring.jasper.model;

public class TableMetaData {
	
	private String colName;
	
	private String fieldName;
	
	private String fieldExpression;
	
	private String uuid;
	
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldExpression() {
		return fieldExpression;
	}
	public void setFieldExpression(String fieldExpression) {
		this.fieldExpression = fieldExpression;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
