package com.thingtrack.konekti.view.web.form.field;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.vaadin.addon.customfield.CustomField;

import com.thingtrack.konekti.domain.Location;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;

@SuppressWarnings("serial")
public class LocationCollectionField extends CustomField {
	private VerticalLayout mainLayout;
	private VerticalLayout vlOrganization;
	private HorizontalLayout hlToolbar;
	private Button btnRemove;
	private Button btnAdd;	
	private Table tbLocation;
	
	private BeanItemContainer<Location> locationsTableContainer;
	
	public LocationCollectionField() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
				
		locationsTableContainer = new BeanItemContainer<Location>(Location.class);
		tbLocation.setContainerDataSource(locationsTableContainer);
		
		tbLocation.setVisibleColumns(new String[] { "code", "name", "description", "comment", "active"} );       
		tbLocation.setColumnHeaders(new String[] { "Código", "Nombre", "Descriptión", "Comentarios", "Activa" } );
		
		tbLocation.setColumnCollapsed("code", true);
		tbLocation.setColumnCollapsed("comment", true);		
		
		// set button event handlers
		btnAdd.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				
				Location location = new Location();
				location.setCode("Test Location");
				location.setName(location.getCode());
				
				locationsTableContainer.addBean(location);
			}
		});
		
		btnRemove.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO
			}
		});
		
	}
	
	@Override
	public Class<?> getType() {
		return List.class;
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void setPropertyDataSource(Property newDataSource) {
		List collectionsValue = (List) newDataSource.getValue();
		
		locationsTableContainer.removeAllItems();
		locationsTableContainer.addAll(collectionsValue);
				
		super.setPropertyDataSource(newDataSource);
	}
	
	@Override
	public Object getValue() {
		
		return new ArrayList<Location>(
				(Collection<? extends Location>) locationsTableContainer.getItemIds());
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
		
		// vlOrganization
		vlOrganization = buildVlOrganization();
		mainLayout.addComponent(vlOrganization);
		mainLayout.setExpandRatio(vlOrganization, 1.0f);
		
		return mainLayout;
	}

	private VerticalLayout buildVlOrganization() {
		// common part: create layout
		vlOrganization = new VerticalLayout();
		vlOrganization.setImmediate(false);
		vlOrganization.setWidth("100.0%");
		vlOrganization.setHeight("100.0%");
		vlOrganization.setMargin(false);
		
		// tbTable
		tbLocation = new Table();
		tbLocation.setImmediate(true);
		tbLocation.setSelectable(true);
		tbLocation.setMultiSelect(false);
		tbLocation.setColumnCollapsingAllowed(true);
		tbLocation.setWidth("100.0%");
		tbLocation.setHeight("100.0%");
		tbLocation.setEditable(true);
		tbLocation.setTableFieldFactory(new TableFieldFactory() {					
			@Override
			public Field createField(Container container, Object itemId, Object propertyId, Component uiContext) {
				if("active".equals(propertyId)) {
					
					CheckBox field = new CheckBox();
					field.setReadOnly(true);
					return field;
				}
				
				return null;
			}
		});
		
		vlOrganization.addComponent(tbLocation);
		vlOrganization.setExpandRatio(tbLocation, 1.0f);
		
		// hlToolbar
		hlToolbar = buildHlToolbar();
		vlOrganization.addComponent(hlToolbar);
		
		return vlOrganization;
	}

	private HorizontalLayout buildHlToolbar() {
		// common part: create layout
		hlToolbar = new HorizontalLayout();
		hlToolbar.setImmediate(false);
		hlToolbar.setWidth("100.0%");
		hlToolbar.setHeight("26px");
		hlToolbar.setMargin(false);
		
		// btnAdd
		btnAdd = new Button();
		btnAdd.setCaption("Añadir");
		btnAdd.setImmediate(true);
		btnAdd.setWidth("-1px");
		btnAdd.setHeight("-1px");
		hlToolbar.addComponent(btnAdd);
		
		// btnRemove
		btnRemove = new Button();
		btnRemove.setCaption("Borrar");
		btnRemove.setImmediate(true);
		btnRemove.setWidth("-1px");
		btnRemove.setHeight("-1px");
		hlToolbar.addComponent(btnRemove);
		hlToolbar.setExpandRatio(btnRemove, 1.0f);
		hlToolbar.setComponentAlignment(btnRemove, new Alignment(33));
		
		return hlToolbar;
	}
	
}
