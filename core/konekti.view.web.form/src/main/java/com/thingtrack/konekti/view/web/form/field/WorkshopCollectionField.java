package com.thingtrack.konekti.view.web.form.field;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.vaadin.addon.customfield.CustomField;

import com.thingtrack.konekti.domain.Workshop;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
public class WorkshopCollectionField extends CustomField {
	private VerticalLayout mainLayout;
	private VerticalLayout vlWorkshop;
	private HorizontalLayout hlToolbar;
	private Button btnRemove;
	private Button btnAdd;	
	private Table tbWorkshop;
	
	private BeanItemContainer<Workshop> workshopTableContainer;
	
	public WorkshopCollectionField() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		workshopTableContainer = new BeanItemContainer<Workshop>(Workshop.class);
		tbWorkshop.setContainerDataSource(workshopTableContainer);
		
		tbWorkshop.setVisibleColumns(new String[] { "code", "name", "description", "comment", "active"} );       
		tbWorkshop.setColumnHeaders(new String[] { "Código", "Nombre", "Descriptión", "Comentarios", "Activa" } );
		
		tbWorkshop.setColumnCollapsed("code", true);
		tbWorkshop.setColumnCollapsed("comment", true);		
		
		// set button event handlers
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
		
		workshopTableContainer.removeAllItems();
		workshopTableContainer.addAll(collectionsValue);
				
		super.setPropertyDataSource(newDataSource);
	}
	
	@Override
	public Object getValue() {		
		return new ArrayList<Workshop>(
				(Collection<? extends Workshop>) workshopTableContainer.getItemIds());
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
		vlWorkshop = buildVlOrganization();
		mainLayout.addComponent(vlWorkshop);
		mainLayout.setExpandRatio(vlWorkshop, 1.0f);
		
		return mainLayout;
	}

	private VerticalLayout buildVlOrganization() {
		// common part: create layout
		vlWorkshop = new VerticalLayout();
		vlWorkshop.setImmediate(false);
		vlWorkshop.setWidth("100.0%");
		vlWorkshop.setHeight("100.0%");
		vlWorkshop.setMargin(false);
		
		// tbTable
		tbWorkshop = new Table();
		tbWorkshop.setImmediate(true);
		tbWorkshop.setSelectable(true);
		tbWorkshop.setMultiSelect(false);
		tbWorkshop.setColumnCollapsingAllowed(true);
		tbWorkshop.setWidth("100.0%");
		tbWorkshop.setHeight("100.0%");
		tbWorkshop.setEditable(true);
		tbWorkshop.setTableFieldFactory(new TableFieldFactory() {					
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
		
		vlWorkshop.addComponent(tbWorkshop);
		vlWorkshop.setExpandRatio(tbWorkshop, 1.0f);
		
		// hlToolbar
		hlToolbar = buildHlToolbar();
		vlWorkshop.addComponent(hlToolbar);
		
		return vlWorkshop;
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
