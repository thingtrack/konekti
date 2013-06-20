package com.thingtrack.konekti.view.web.form.field;

import org.vaadin.addon.customfield.CustomField;

import com.thingtrack.konekti.view.addon.ui.UploadViewForm;

import com.vaadin.data.Property;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

@SuppressWarnings("serial")
public class FileField extends CustomField {
	
	private VerticalLayout mainLayout;
	private Button btnAttach;
	
	private byte[] file;
	
	public FileField() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		// TODO add user code here
		btnAttach.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				final UploadViewForm uploadViewForm = new UploadViewForm(file);
				
				uploadViewForm.setWidth("300px");
				uploadViewForm.setHeight("-1px");
				uploadViewForm.addListener(new CloseListener() {				
					@Override
					public void windowClose(CloseEvent e) {
						file = uploadViewForm.getFile();
						
						btnAttach.setIcon(new ThemeResource("../konekti/images/icons/servicedesigner-module/tick.png"));
						
					}
				});
				
				getApplication().getMainWindow().addWindow(uploadViewForm);
				
			}
		});
	}

	@Override
	public void setCaption(String caption) {
		btnAttach.setCaption(caption);
		
	}
	
	@Override
	public void setPropertyDataSource(Property newDataSource) {
		file = (byte[])newDataSource.getValue();
		
		if (file != null)
			btnAttach.setIcon(new ThemeResource("../konekti/images/icons/servicedesigner-module/tick.png"));
		
		super.setPropertyDataSource(newDataSource);
	}
	
	public void setData(byte[] file) {
		if (file != null)
			btnAttach.setIcon(new ThemeResource("../konekti/images/icons/servicedesigner-module/tick.png"));
		
	}
	
	@Override
	public Class<?> getType() {
		return byte[].class;
	}

	@Override
	public Object getValue() {	
		return file;
		
	}
	
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");
		
		// btnAttach
		btnAttach = new Button();
		btnAttach.setCaption("Adjuntar Fichero");
		btnAttach.setImmediate(false);
		btnAttach.setWidth("100.0%");
		btnAttach.setHeight("-1px");
		mainLayout.addComponent(btnAttach);
		
		return mainLayout;
	}
}
