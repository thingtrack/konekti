package com.thingtrack.konekti.report;

/*
 * #%L
 * Konekti Report
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2013 Thingtrack s.l.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.ByteArrayOutputStream;
import java.util.Map;

import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.User;
import com.vaadin.terminal.gwt.server.WebApplicationContext;

import net.sf.jasperreports.engine.JasperPrint;

public interface ReportManagerService {
	public JasperPrint executeReport(Organization organization, String code, Map<String,Object> parameters) throws Exception;
	public ByteArrayOutputStream exportReportToHtmlStream(WebApplicationContext context, Organization organization, String code, Map<String,Object> parameters, String destFileName) throws Exception;
	public void exportReportToPdfFile(Organization organization, String code, Map<String,Object> parameters, String destinationFile) throws Exception;
	public void exportReportToXmlFile(Organization organization, String code, Map<String,Object> parameters, String destFileName, boolean isEmbeddingImages) throws Exception;
	public void exportReportToHtmlFile(Organization organization, String code, Map<String,Object> parameters, String destFileName) throws Exception;
}
