package com.thingtrack.konekti.report.internal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Report;
import com.thingtrack.konekti.report.ReportManagerService;
import com.thingtrack.konekti.service.api.ReportService;
import com.vaadin.terminal.gwt.server.WebApplicationContext;

public class ReportManagerServiceImpl implements ReportManagerService {
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ReportService reportService;
	
	@Override
	public JasperPrint executeReport(Organization organization, String code, Map<String,Object> parameters) throws Exception {
		// get  report to execute
		Report report = reportService.getByCode(organization, code);
		
		if (report == null)
			throw new Exception("The Report with code " + code + " not exist!");
		
		// get connection from jpa transaction manager to inject to jasper report template
		Connection connection = entityManager.unwrap(java.sql.Connection.class);
				
		if (connection == null)
			throw new Exception("We can not get the JPA connection!");
		
		// generate report result
		JasperPrint jasperPrint = JasperFillManager.fillReport(new ByteArrayInputStream(report.getTemplate()), parameters, connection);
		
        return jasperPrint;
			            					
	}
	
	@Override
	public void exportReportToPdfFile(Organization organization, String code, Map<String,Object> parameters, String destinationFile) throws Exception {		
		JasperExportManager.exportReportToPdfFile(executeReport(organization, code, parameters), destinationFile);
		
	}
	
	@Override
	public void exportReportToXmlFile(Organization organization, String code, Map<String,Object> parameters, String destFileName, boolean isEmbeddingImages) throws Exception {
		JasperExportManager.exportReportToXmlFile(executeReport(organization, code, parameters), destFileName, isEmbeddingImages); 
		
	}
	
	@Override
	public void exportReportToHtmlFile(Organization organization, String code, Map<String,Object> parameters, String destFileName) throws Exception {
		JasperExportManager.exportReportToHtmlFile(executeReport(organization, code, parameters), destFileName);
		
	}
	
	@Override
	public ByteArrayOutputStream exportReportToHtmlStream(WebApplicationContext context, Organization organization, String code, Map<String,Object> parameters, String destFileName) throws Exception {				
		JasperPrint jasperPrint = executeReport(organization, code, parameters);
		
		// create the stream out report
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		// get context uri
		String uri = context.getHttpSession().getServletContext().getContextPath();
		
		JRHtmlExporter exporter = new JRHtmlExporter();
		String random = "" + Math.random() * 1000.0;
		random = random.replace('.', '0').replace(',', '0');	
		
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, uri + "/image?r=" + random + "&image=");
		
		context.getHttpSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		
		exporter.exportReport();
		
		outputStream.flush();
		outputStream.close();
		
		return outputStream;
	}
	
}
