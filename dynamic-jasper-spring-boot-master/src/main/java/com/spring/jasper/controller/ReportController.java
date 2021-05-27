package com.spring.jasper.controller;

import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jasper.request.ReportDataRequest;
import com.spring.jasper.request.ReportSQLRequest;
import com.spring.jasper.util.TemplateCompiler;
import com.spring.jasper.util.VelocityUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/report")
@Tag(name = "Report", description = "Report API")
public class ReportController {
	
	@Autowired
	private VelocityUtil velocityUtil;
	
	@Autowired
	private TemplateCompiler templateCompiler;
	
	private static Logger logger = LoggerFactory.getLogger(ReportController.class);

	
	@Operation(summary = "Data Report", description = "Generate Report from Data in the Request", tags = { "Report" })
	@ApiResponses(value = {
	  @ApiResponse(responseCode = "200", description = "successful operation")
	})
	@PostMapping("/data")
	public void getReportFromData(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description="Report Request, Can not be null", required=true, 
			content=@Content(mediaType =MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReportDataRequest.class)))
			@Valid @RequestBody ReportDataRequest reportRequest, HttpServletResponse response) throws Exception {
		try {
			logger.info("Reading information form the request");
			InputStream intStream = velocityUtil.processTemplate(reportRequest);
			templateCompiler.compileReport(intStream, response, reportRequest);
			logger.info("Request completed");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Operation(summary = "SQL Report", description = "Generate Report from SQL in the Request", tags = { "Report" })
	@ApiResponses(value = {
	  @ApiResponse(responseCode = "200", description = "successful operation")
	})
	@PostMapping("/sql")
	public void getReport(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description="Report Request, Can not be null", required=true, 
			content=@Content(mediaType =MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReportSQLRequest.class)))
			@Valid @RequestBody ReportSQLRequest reportRequest, HttpServletResponse response) throws Exception {
		try {
			logger.info("Reading information form the request");
			InputStream intStream = velocityUtil.processTemplate(reportRequest);
			templateCompiler.compileReport(intStream, response, reportRequest);
			logger.info("Request completed");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
