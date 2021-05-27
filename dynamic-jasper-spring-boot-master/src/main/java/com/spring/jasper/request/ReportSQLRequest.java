package com.spring.jasper.request;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;

public class ReportSQLRequest extends ReportRequest {

	private static final long serialVersionUID = 1L;

	@Schema(description = "Database name", example = "TESTDB", required = true)
	@NotBlank
	private String database;
	
	@Schema(description = "sql query", example = "slect * from table", required = true)
	@NotBlank
	private String sql;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}
	
}
