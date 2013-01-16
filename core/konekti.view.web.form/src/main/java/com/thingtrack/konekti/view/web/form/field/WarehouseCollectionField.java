package com.thingtrack.konekti.view.web.form.field;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.vaadin.addon.customfield.CustomField;

import com.thingtrack.konekti.domain.Warehouse;
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
public class WarehouseCollectionField extends CustomField {
	private VerticalLayout mainLayout;
	private VerticalLayout vlWarehouse;
	private HorizontalLayout hlToolbar;
	private Button btnRemove;
	private Button btnAdd;	
	private Table tbWarehouse;

	private BeanItemContainer<Warehouse> warehouseTableContainer;

	public WarehouseCollectionField() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		warehouseTableContainer = new BeanItemContainer<Warehouse>(Warehouse.class);
		tbWarehouse.setContainerDataSource(warehouseTableContainer);

		tbWarehouse.setVisibleColumns(new String[] { "code", "name", "description", "comment", "active"} );       
		tbWarehouse.setColumnHeaders(new String[] { "Código", "Nombre", "Descriptión", "Comentarios", "Activa" } );

		tbWarehouse.setColumnCollapsed("code", true);
		tbWarehouse.setColumnCollapsed("comment", true);		

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

		warehouseTableContainer.removeAllItems();
		warehouseTableContainer.addAll(collectionsValue);

		super.setPropertyDataSource(newDataSource);
	}

	@Override
	public Object getValue() {		
		return new ArrayList<Warehouse>(
				(Collection<? extends Warehouse>) warehouseTableContainer.getItemIds());
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
		vlWarehouse = buildVlOrganization();
		mainLayout.addComponent(vlWarehouse);
		mainLayout.setExpandRatio(vlWarehouse, 1.0f);

		return mainLayout;
	}

	private VerticalLayout buildVlOrganization() {
		// common part: create layout
		vlWarehouse = new VerticalLayout();
		vlWarehouse.setImmediate(false);
		vlWarehouse.setWidth("100.0%");
		vlWarehouse.setHeight("100.0%");
		vlWarehouse.setMargin(false);

		// tbTable
		tbWarehouse = new Table();
		tbWarehouse.setImmediate(true);
		tbWarehouse.setSelectable(true);
		tbWarehouse.setMultiSelect(false);
		tbWarehouse.setColumnCollapsingAllowed(true);
		tbWarehouse.setWidth("100.0%");
		tbWarehouse.setHeight("100.0%");
		tbWarehouse.setEditable(true);
		tbWarehouse.setTableFieldFactory(new TableFieldFactory() {					
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

		vlWarehouse.addComponent(tbWarehouse);
		vlWarehouse.setExpandRatio(tbWarehouse, 1.0f);

		// hlToolbar
		hlToolbar = buildHlToolbar();
		vlWarehouse.addComponent(hlToolbar);

		return vlWarehouse;
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