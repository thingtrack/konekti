package com.thingtrack.konekti.report;

import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;

public interface ReportManagerService {
	public JasperPrint executeReport(String name, Map<String,Object> parameters) throws Exception;
}
