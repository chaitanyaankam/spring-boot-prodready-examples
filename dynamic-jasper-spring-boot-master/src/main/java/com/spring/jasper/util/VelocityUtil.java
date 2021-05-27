package com.spring.jasper.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.jasper.controller.ReportController;
import com.spring.jasper.model.TableMetaData;
import com.spring.jasper.request.ReportDataRequest;
import com.spring.jasper.request.ReportRequest;
import com.spring.jasper.request.ReportSQLRequest;

@Component
public class VelocityUtil {
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	private static final String DATA_BAND_FIELD_EXPRESSTION_PATTERN = "$F{0}";
	
	private static final String VELOCITY_DATA_TEMPLATE = "templates/data_dynamic_report.vm";
	
	private static final String VELOCITY_SQL_TEMPLATE = "templates/sql_dynamic_report.vm";

	private static Logger logger = LoggerFactory.getLogger(ReportController.class);

	public InputStream processTemplate(ReportRequest reportRequest) {
		
		Template template = null;		
		if(reportRequest instanceof ReportDataRequest)
			template = velocityEngine.getTemplate(VELOCITY_DATA_TEMPLATE);		
		else if(reportRequest instanceof ReportSQLRequest)
			template = velocityEngine.getTemplate(VELOCITY_SQL_TEMPLATE);
		         
        VelocityContext vc = new VelocityContext();       
        
        Map<String, String> columnsFieldsMap = reportRequest.getColumnsFieldsMap();
        List<TableMetaData> tableMetaDataList = new ArrayList<>(columnsFieldsMap.size());
        
        for(Map.Entry<String, String> entry: columnsFieldsMap.entrySet()) {
        	logger.info(MessageFormat.format("key {0} value {1}", entry.getKey(), entry.getValue()));
        	TableMetaData tableMetaData = new TableMetaData();
        	tableMetaData.setUuid(UUID.randomUUID().toString()); 
        	tableMetaData.setColName(entry.getKey());
        	tableMetaData.setFieldName(entry.getValue());
        	tableMetaData.setFieldExpression(MessageFormat.format(DATA_BAND_FIELD_EXPRESSTION_PATTERN, "{"+entry.getValue()+"}"));
        	tableMetaDataList.add(tableMetaData);
        }
        
        vc.put("param_title_expression", "$P{page_title}");
        vc.put("columnList", tableMetaDataList);
        if(reportRequest instanceof ReportSQLRequest) vc.put("sql", ((ReportSQLRequest) reportRequest).getSql());
        
        StringWriter writer = new StringWriter();
        template.merge(vc, writer);
        
        logger.info(writer.toString());
        
        return fromStringBuffer(writer.getBuffer());
	}
	
	public InputStream fromStringBuffer(StringBuffer buf) {
		return new ByteArrayInputStream(buf.toString().getBytes());
	}
}
