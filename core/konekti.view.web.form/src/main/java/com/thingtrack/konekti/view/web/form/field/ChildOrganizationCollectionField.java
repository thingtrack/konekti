package com.thingtrack.konekti.view.web.form.field;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.vaadin.addon.customfield.CustomField;

import com.thingtrack.konekti.domain.Organization;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.CheckBox;

@SuppressWarnings("serial")
public class ChildOrganizationCollectionField extends CustomField {
	private VerticalLayout mainLayout;
	private VerticalLayout vlOrganization;
	private HorizontalLayout hlToolbar;
	private Button btnRemove;
	private Button btnAdd;	
	private Table tbChildOrganization;
	
	private BeanItemContainer<Organization> childOrganizationsTableContainer;
	
	public ChildOrganizationCollectionField() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		//Bean Item Containers
		childOrganizationsTableContainer = new BeanItemContainer<Organization>(Organization.class);
		tbChildOrganization.setContainerDataSource(childOrganizationsTableContainer);
				
		tbChildOrganization.setVisibleColumns(new String[] { "code", "name", "description", "cif", "socialAddress", "comment", "active" } );       
		tbChildOrganization.setColumnHeaders(new String[] { "Código", "Nombre", "Descriptión", "CIF", "Dirección Social", "Comentarios", "Activa" } );
				
		tbChildOrganization.setColumnCollapsed("code", true);
		tbChildOrganization.setColumnCollapsed("comment", true);
		tbChildOrganization.setColumnCollapsed("socialAddress", true);
		tbChildOrganization.setColumnCollapsed("active", true);
		
		tbChildOrganization.setTableFieldFactory(new TableFieldFactory() {
			
			@Override
			public Field createField(Container container, Object itemId,
					Object propertyId, Component uiContext) {
				
				if("active".equals(propertyId)){
					
					return new CheckBox();
				}
				return null;
			}
		});
		
		// set button events
		btnAdd.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO
				
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
		
		childOrganizationsTableContainer.removeAllItems();
		childOrganizationsTableContainer.addAll(collectionsValue);
				
		super.setPropertyDataSource(newDataSource);
	}	
	
	@Override
	public Object getValue() {
		return new ArrayList<Organization>(
				(Collection<? extends Organization>) childOrganizationsTableContainer.getItemIds());
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
		tbChildOrganization = new Table();
		tbChildOrganization.setImmediate(true);
		tbChildOrganization.setSelectable(true);
		tbChildOrganization.setSelectable(true);
		tbChildOrganization.setMultiSelect(false);
		tbChildOrganization.setColumnCollapsingAllowed(true);
		tbChildOrganization.setWidth("100.0%");
		tbChildOrganization.setHeight("100.0%");
		tbChildOrganization.setTableFieldFactory(new TableFieldFactory() {					
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
		
		vlOrganization.addComponent(tbChildOrganization);
		vlOrganization.setExpandRatio(tbChildOrganization, 1.0f);
		
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
