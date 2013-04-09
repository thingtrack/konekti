package com.thingtrack.konekti.view.web.form.field;

import java.util.Calendar;
import java.util.Date;

import org.vaadin.addon.customfield.CustomField;
import com.vaadin.data.Property;
import com.vaadin.ui.DateField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class JobTriggerCalendarField  extends CustomField {
	private VerticalLayout mainLayout;
	private Calendar jobCalendar;
	private DateField jobCalendarField;
	
	public JobTriggerCalendarField() {
		mainLayout = buildMainLayout();
		setCompositionRoot(mainLayout);
	}
	
	@Override
	public Class<?> getType() {
		return Calendar.class;
		
	}
	
	@Override
	public Object getValue() {
		if (jobCalendarField.getValue() == null)
			return null;
			
		Calendar cal = Calendar.getInstance();
		cal.setTime((Date)jobCalendarField.getValue());
				
		return cal;
	}
	
	@Override
	public void setPropertyDataSource(Property newDataSource) {
		jobCalendar = (Calendar)newDataSource.getValue();
		
		if (jobCalendar == null) {
			super.setPropertyDataSource(newDataSource);
			return;
		}
		
		jobCalendarField.setValue(jobCalendar.getTime());
		
		super.setPropertyDataSource(newDataSource);
	}
	
	private VerticalLayout buildMainLayout() {
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSizeFull();
		mainLayout.setSpacing(true);

		// Map definition
		jobCalendarField = new DateField();
		jobCalendarField = new DateField();
		jobCalendarField.setCaption("Job Calendario");
		jobCalendarField.setImmediate(false);
		jobCalendarField.setInvalidAllowed(false);
		jobCalendarField.setResolution(DateField.RESOLUTION_SEC);
		jobCalendarField.setWidth("160px");
		jobCalendarField.setHeight("-1px");

		mainLayout.addComponent(jobCalendarField);
		
		return mainLayout;
	}

}
