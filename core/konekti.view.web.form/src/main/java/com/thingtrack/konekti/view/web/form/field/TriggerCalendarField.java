package com.thingtrack.konekti.view.web.form.field;

import java.util.Calendar;
import java.util.Date;

import org.vaadin.addon.customfield.CustomField;
import com.vaadin.data.Property;
import com.vaadin.ui.DateField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class TriggerCalendarField  extends CustomField {
	private VerticalLayout mainLayout;
	private Calendar alarmCalendar;
	private DateField alarmCalendarField;
	
	public TriggerCalendarField() {
		mainLayout = buildMainLayout();
		setCompositionRoot(mainLayout);
	}
	
	@Override
	public Class<?> getType() {
		return Calendar.class;
		
	}
	
	@Override
	public Object getValue() {
		if (alarmCalendarField.getValue() == null)
			return null;
			
		Calendar cal = Calendar.getInstance();
		cal.setTime((Date)alarmCalendarField.getValue());
				
		return cal;
	}
	
	@Override
	public void setPropertyDataSource(Property newDataSource) {
		alarmCalendar = (Calendar)newDataSource.getValue();
		
		if (alarmCalendar == null) {
			super.setPropertyDataSource(newDataSource);
			return;
		}
		
		alarmCalendarField.setValue(alarmCalendar.getTime());
		
		super.setPropertyDataSource(newDataSource);
	}
	
	private VerticalLayout buildMainLayout() {
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSizeFull();
		mainLayout.setSpacing(true);

		// Map definition
		alarmCalendarField = new DateField();
		alarmCalendarField = new DateField();
		alarmCalendarField.setCaption("Alarma Calendario");
		alarmCalendarField.setImmediate(false);
		alarmCalendarField.setInvalidAllowed(false);
		alarmCalendarField.setResolution(DateField.RESOLUTION_SEC);
		alarmCalendarField.setWidth("160px");
		alarmCalendarField.setHeight("-1px");

		mainLayout.addComponent(alarmCalendarField);
		
		return mainLayout;
	}

}
