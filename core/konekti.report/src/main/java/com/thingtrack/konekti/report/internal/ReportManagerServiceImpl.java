package com.thingtrack.konekti.report.internal;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.domain.Report;
import com.thingtrack.konekti.report.ReportManagerService;
import com.thingtrack.konekti.service.api.ReportService;

public class ReportManagerServiceImpl implements ReportManagerService {
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ReportService reportService;
			
	@Override
	public JasperPrint executeReport(String code, Map<String,Object> parameters) throws Exception {
		// get  report to execute
		Report report = reportService.getByCode(code);
		
		if (report == null)
			throw new Exception("The Report with code " + code + " not exist!");
		
		// get connection from jpa transaction manager to inject to jasper report template
		Connection connection = entityManager.unwrap(java.sql.Connection.class);
				
		if (connection == null)
			throw new Exception("We can not get the JPA connection!");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(new ByteArrayInputStream(report.getTemplate()), parameters, connection);
        
        return jasperPrint;
			            					
	}
}
