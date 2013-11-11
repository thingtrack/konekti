package com.thingtrack.konekti.view.addon.ui;

import java.awt.Color;
import java.util.Collection;
import java.util.Map;

import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;

import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.User;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class PrintViewForm extends Window {
	private VerticalLayout mainLayout;
	private GridComponentReport componentReport;
	
	private String reportTittle;
	private String[] columnProperties;
	private Class<?>[] columnClasses;
	private String[] columnTitles;
	private Collection<?> data;
	private Component parametersComponent;
	
	public PrintViewForm() {		
		buildMainLayout();
		setContent(mainLayout);

		// TODO add user code here
		setModal(true);
		setResizable(false);
		center();
	}
	
	public String getReportTittle() {
		return reportTittle;
	}
	
	public void setReportTittle(String reportTittle) {
		this.reportTittle = reportTittle;
		
	}
	
	public String[] getColumnProperties() {
		return columnProperties;
	}
	
	public void setColumnProperties(String[] columnProperties) {
		this.columnProperties = columnProperties;
		
	}
	
	public Class<?>[] getColumnClasses() {
		return columnClasses;
	}
	
	public void setColumnClasses(Class<?>[] columnClasses) {
		this.columnClasses = columnClasses;
		
	}
	
	public String[] getColumnTitles() {
		return columnTitles;
	}
	
	public void setColumnTitles(String[] columnTitles) {
		this.columnTitles = columnTitles;
		
	}
	
	public Collection<?> getData() {
		return data;
	}
	
	public void setData(Collection<?> data) {
		this.data = data;
		
	}
	
	public Component getParametersComponent() {
		return parametersComponent;
	}
	
	public void setParametersComponent(Component parametersComponent) {
		this.parametersComponent = parametersComponent;
		
	}
	
	public void generateTemplateReport(Organization organization, String code, Map<String,Object> parameters) throws Exception {
		TemplateComponentReport templateComponentReport = new TemplateComponentReport();  	
    	templateComponentReport.executeReport(organization, code, parameters);
    	
    	mainLayout.addComponent(templateComponentReport);
	}
	
	public void generateReport() {
		componentReport = new GridComponentReport() {
			@Override
			public DynamicReportBuilder getReportBuilder() {
				// let's override this method to add some elements to the report
				DynamicReportBuilder reportBuilder = super.getReportBuilder();
				
				Style headerStyle = new StyleBuilder(true).setFont(Font.ARIAL_MEDIUM).build();
				
				reportBuilder.addAutoText("Para uso interno", AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 200, headerStyle);
				reportBuilder.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_HEADER, AutoText.ALIGNMENT_RIGHT, 200, 10, headerStyle);
				reportBuilder.addAutoText("2012/10/12", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_RIGHT, 200, headerStyle);
				
				Style titleStyle = new StyleBuilder(true).setPadding(0).setFont(Font.ARIAL_BIG_BOLD).setHorizontalAlign(HorizontalAlign.CENTER).build();
				reportBuilder.setTitleStyle(titleStyle);
				reportBuilder.setTitleHeight(18);
				
				Style footerStyle = new StyleBuilder(true).setFont(Font.ARIAL_MEDIUM).setTextColor(Color.GRAY).build();
				reportBuilder.addAutoText("Powered by Enterprise App for Vaadin", AutoText.POSITION_FOOTER, AutoText.ALIGMENT_LEFT, 200, footerStyle);
				
				reportBuilder.setTitle(reportTittle);
				
				return reportBuilder;
	        }
		};
		
		componentReport.setImmediate(false);
		componentReport.setWidth("100.0%");
		componentReport.setHeight("100.0%");

		componentReport.setColumnProperties(columnProperties);
		componentReport.setColumnClasses(columnClasses);
		componentReport.setColumnTitles(columnTitles);
        componentReport.setData(data);

        mainLayout.addComponent(componentReport);
	}
	
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		return mainLayout;
	}
}
