package com.thingtrack.konekti.view.web.form.field;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.vaadin.addon.customfield.CustomField;

import com.thingtrack.konekti.domain.Service;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
public class ServiceCollectionField extends CustomField {
	private VerticalLayout mainLayout;
	private VerticalLayout vlService;
	private HorizontalLayout hlToolbar;
	private Button btnRemove;
	private Button btnAdd;	
	private Table tbService;

	private BeanItemContainer<Service> serviceTableContainer;
	
	public ServiceCollectionField() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		serviceTableContainer = new BeanItemContainer<Service>(Service.class);
		tbService.setContainerDataSource(serviceTableContainer);

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

		serviceTableContainer.removeAllItems();
		serviceTableContainer.addAll(collectionsValue);

		serviceTableContainer.addNestedContainerProperty("client.description");
		
		tbService.setVisibleColumns(new String[] { "code", "description", "observation", "client.description" } );       
		tbService.setColumnHeaders(new String[] { "Código", "Descripción", "Observaciones", "Cliente" } );

		tbService.setColumnCollapsed("comment", true);	
		
		super.setPropertyDataSource(newDataSource);
	}

	@Override
	public Object getValue() {		
		return new ArrayList<Service>(
				(Collection<? extends Service>) serviceTableContainer.getItemIds());
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
		vlService = buildVlOfferLine();
		mainLayout.addComponent(vlService);
		mainLayout.setExpandRatio(vlService, 1.0f);

		return mainLayout;
	}

	private VerticalLayout buildVlOfferLine() {
		// common part: create layout
		vlService = new VerticalLayout();
		vlService.setImmediate(false);
		vlService.setWidth("100.0%");
		vlService.setHeight("100.0%");
		vlService.setMargin(false);

		// tbTable
		tbService = new Table();
		tbService.setImmediate(true);
		tbService.setSelectable(true);
		tbService.setMultiSelect(false);
		tbService.setColumnCollapsingAllowed(true);
		tbService.setWidth("100.0%");
		tbService.setHeight("100.0%");		

		vlService.addComponent(tbService);
		vlService.setExpandRatio(tbService, 1.0f);

		// hlToolbar
		hlToolbar = buildHlToolbar();
		vlService.addComponent(hlToolbar);

		return vlService;
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