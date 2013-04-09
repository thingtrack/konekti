package com.thingtrack.konekti.view.web.form.field;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.vaadin.addon.customfield.CustomField;

import com.thingtrack.konekti.domain.Area;
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
public class AreaCollectionField extends CustomField {
	private VerticalLayout mainLayout;
	private VerticalLayout vlArea;
	private HorizontalLayout hlToolbar;
	private Button btnRemove;
	private Button btnAdd;	
	private Table tbArea;

	private BeanItemContainer<Area> areaTableContainer;

	public AreaCollectionField() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		areaTableContainer = new BeanItemContainer<Area>(Area.class);
		tbArea.setContainerDataSource(areaTableContainer);

		tbArea.setVisibleColumns(new String[] { "code", "name", "description", "comment", "active"} );       
		tbArea.setColumnHeaders(new String[] { "Código", "Nombre", "Descriptión", "Comentarios", "Activa" } );

		tbArea.setColumnCollapsed("code", true);
		tbArea.setColumnCollapsed("comment", true);		

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

		areaTableContainer.removeAllItems();
		areaTableContainer.addAll(collectionsValue);

		super.setPropertyDataSource(newDataSource);
	}

	@Override
	public Object getValue() {		
		return new ArrayList<Area>(
				(Collection<? extends Area>) areaTableContainer.getItemIds());
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
		vlArea = buildVlOrganization();
		mainLayout.addComponent(vlArea);
		mainLayout.setExpandRatio(vlArea, 1.0f);

		return mainLayout;
	}

	private VerticalLayout buildVlOrganization() {
		// common part: create layout
		vlArea = new VerticalLayout();
		vlArea.setImmediate(false);
		vlArea.setWidth("100.0%");
		vlArea.setHeight("100.0%");
		vlArea.setMargin(false);

		// tbTable
		tbArea = new Table();
		tbArea.setImmediate(true);
		tbArea.setSelectable(true);
		tbArea.setMultiSelect(false);
		tbArea.setColumnCollapsingAllowed(true);
		tbArea.setWidth("100.0%");
		tbArea.setHeight("100.0%");
		tbArea.setEditable(true);
		tbArea.setTableFieldFactory(new TableFieldFactory() {					
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

		vlArea.addComponent(tbArea);
		vlArea.setExpandRatio(tbArea, 1.0f);

		// hlToolbar
		hlToolbar = buildHlToolbar();
		vlArea.addComponent(hlToolbar);

		return vlArea;
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