package com.spring.jasper.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.spring.jasper.request.ReportDataRequest;
import com.spring.jasper.request.ReportRequest;
import com.spring.jasper.request.ReportSQLRequest;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

@Component
public class TemplateCompiler {
	
	@PersistenceContext
	EntityManager entityManager;
	
	private static final String MEDIA_TYPE_EXCEL = "application/vnd.ms-excel";
	
	private static final String XL_FILE_EXTENSION = ".xls";
	
	private static Logger logger = LoggerFactory.getLogger(TemplateCompiler.class);
	
	public void compileReport(InputStream inputStream, HttpServletResponse response, ReportRequest reportRequest) throws Exception {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ServletOutputStream outputStream = response.getOutputStream();){
			
			logger.info("Compiling Jasper report from Inputstream, page title is ", reportRequest.getPageTitle());
			
			HashMap<String, Object> params = new HashMap<String, Object>(); 
			params.put("page_title", reportRequest.getPageTitle());

			// Convert template to JasperDesign
			JasperDesign jd = JRXmlLoader.load(inputStream);
			
			// Compile design to JasperReport
			JasperReport jr = JasperCompileManager.compileReport(jd);
			
			// Create the JasperPrint object, make sure to pass the JasperReport, report parameters, and data source
			JasperPrint jp = getReportWithDatasource(jr, params, reportRequest);
						
			// Export report, export to output stream
			exportXls(jp, baos);
			
			response.setHeader("Content-Disposition", "inline; filename=" + reportRequest.getFileName()+XL_FILE_EXTENSION);
			response.setContentType(MEDIA_TYPE_EXCEL);
			response.setContentLength(baos.size());
			
			baos.writeTo(outputStream);
			
			logger.info("flushing report must be done automatically");			
		} catch(Exception e) {
			throw new RuntimeException(e);
		}		
	}
	
	private JasperPrint getReportWithDatasource(JasperReport jr, HashMap<String, Object> params, 
			ReportRequest reportRequest) throws Exception {
		JasperPrint jp = null;
		
		if(reportRequest instanceof ReportDataRequest) {
			
			ReportDataRequest reportDataRequest = (ReportDataRequest) reportRequest;
			JRDataSource data = new JRBeanCollectionDataSource(reportDataRequest.getData());
			jp = JasperFillManager.fillReport(jr, params, data);
			
		} else if(reportRequest instanceof ReportSQLRequest) {
			
			ReportSQLRequest reportSQLRequest = (ReportSQLRequest) reportRequest;
			params.put("sql", reportSQLRequest.getSql());
			
			Session session = entityManager.unwrap(Session.class);
			jp = session.doReturningWork(connection -> {
				JasperPrint jpTemp = null;
				try {
					jpTemp = JasperFillManager.fillReport(jr, params, connection);
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					if(!connection.isClosed())
						connection.close();
				}
				return jpTemp;
			});
		}	
		
		return jp; 
	}
	
	private void exportXls(JasperPrint jp, ByteArrayOutputStream baos) throws Exception {
		try {
			logger.info("Exporting parameters");
			// Create a JRXlsExporter instance
			JRXlsExporter exporter = new JRXlsExporter();
			 
			exporter.setExporterInput(new SimpleExporterInput(jp));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
			
			SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
			xlsReportConfiguration.setOnePagePerSheet(Boolean.FALSE);
	        xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
	        xlsReportConfiguration.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
	        xlsReportConfiguration.setDetectCellType(Boolean.FALSE);
	        xlsReportConfiguration.setWhitePageBackground(Boolean.FALSE);
	        xlsReportConfiguration.setWrapText(Boolean.FALSE);
	        xlsReportConfiguration.setIgnoreCellBorder(Boolean.FALSE);
	        xlsReportConfiguration.setShowGridLines(Boolean.TRUE);
	        xlsReportConfiguration.setCollapseRowSpan(Boolean.TRUE);
	        xlsReportConfiguration.setIgnoreCellBackground(Boolean.FALSE);
	        xlsReportConfiguration.setIgnoreGraphics(Boolean.FALSE);
	        	
	        exporter.setConfiguration(xlsReportConfiguration);
		
			exporter.exportReport();			
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}
	
}
