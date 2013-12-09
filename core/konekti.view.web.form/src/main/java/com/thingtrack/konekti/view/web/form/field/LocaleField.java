package com.thingtrack.konekti.view.web.form.field;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.thingtrack.konekti.view.addon.ui.AbstractField;
import com.vaadin.data.Property;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Select;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class LocaleField  extends AbstractField {
	private VerticalLayout mainLayout;
	private ComboBox defaultLocaleField;
	
	public static final String LOCALE_SEPARATOR = "-";
	
	private String localeCode;
	private Locale[] locales;
	
	public LocaleField() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		// get all available locales
		locales = SimpleDateFormat.getAvailableLocales();
	    
		// construct custom datasource for combobox
		// id: language + LOCALE_SEPARATOR + country: es_ES
		// display: fraindly locale string
		/*for (int i = 0; i< locales.length; i++) {
			if (locales[i].getCountry() != "" && locales[i].getLanguage() != "") {
				String localeCode =  locales[i].getLanguage() + LOCALE_SEPARATOR + locales[i].getCountry();
				
				// Use the item ID also as the caption of this item
				defaultLocaleField.addItem(localeCode);
				defaultLocaleField.setItemCaption(localeCode, locales[i].getDisplayName());
			}
		}*/
		
   		// construct custom datasource for combobox
		defaultLocaleField.addItem("es-ES");
		defaultLocaleField.setItemCaption("es-ES", "Español");
		defaultLocaleField.addItem("en-US");
		defaultLocaleField.setItemCaption("en-US", "English");
		//defaultLocaleField.addItem("zh-CN");
		//defaultLocaleField.setItemCaption("zh-CN", "简体中文");
    	
		defaultLocaleField.addListener(new Property.ValueChangeListener() {
			public void valueChange(Property.ValueChangeEvent event) {
	            // Get the selected item
	            Object itemId = event.getProperty().getValue();
	            
	            if (itemId != null)
	            	localeCode = itemId.toString();
				
			}
	    });
		defaultLocaleField.setItemCaptionMode(Select.ITEM_CAPTION_MODE_EXPLICIT_DEFAULTS_ID);
		defaultLocaleField.setImmediate(true);
	    defaultLocaleField.setNullSelectionAllowed(false);
	    
	}
	
	@Override
	public Class<?> getType() {
		return String.class;
	}
	
	@Override
	public void setPropertyDataSource(Property newDataSource) {
		localeCode = (String)newDataSource.getValue();
		
		defaultLocaleField.setValue(localeCode);
		
		super.setPropertyDataSource(newDataSource);
	}
	
	@Override
	public Object getValue() {	
		return localeCode;
		
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
		
		// comboBox_1
		defaultLocaleField = new ComboBox();
		defaultLocaleField.setImmediate(false);
		defaultLocaleField.setWidth("100.0%");
		defaultLocaleField.setHeight("-1px");
		mainLayout.addComponent(defaultLocaleField);
		
		return mainLayout;
	}

	@Override
	protected void updateLabels() {
		defaultLocaleField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.field.LocaleField.defaultLocaleField.caption"));
		
	}
}
