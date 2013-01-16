package com.thingtrack.konekti.view.web.form.field;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.vaadin.addon.customfield.CustomField;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import com.thingtrack.konekti.domain.InvoiceLine;

@SuppressWarnings("serial")
public class InvoiceLineCollectionField extends CustomField {
	private VerticalLayout mainLayout;
	private VerticalLayout vlInvoice;
	private HorizontalLayout hlToolbar;
	private Button btnRemove;
	private Button btnAdd;	
	private Table tbInvoiceLine;

	private BeanItemContainer<InvoiceLine> invoiceLineTableContainer;
	
	public InvoiceLineCollectionField() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		invoiceLineTableContainer = new BeanItemContainer<InvoiceLine>(InvoiceLine.class);
		tbInvoiceLine.setContainerDataSource(invoiceLineTableContainer);

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

		invoiceLineTableContainer.removeAllItems();
		invoiceLineTableContainer.addAll(collectionsValue);

		invoiceLineTableContainer.addNestedContainerProperty("invoiceLineStatus.description");
		
		tbInvoiceLine.setVisibleColumns(new String[] { "number", "price", "comment", "invoiceLineStatus.description", "invoiceLineDate"} );       
		tbInvoiceLine.setColumnHeaders(new String[] { "Número", "Precio", "Comentario", "Estado", "Fecha" } );

		tbInvoiceLine.setColumnCollapsed("comment", true);	
		
		super.setPropertyDataSource(newDataSource);
	}

	@Override
	public Object getValue() {		
		return new ArrayList<InvoiceLine>(
				(Collection<? extends InvoiceLine>) invoiceLineTableContainer.getItemIds());
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
		vlInvoice = buildVlOfferLine();
		mainLayout.addComponent(vlInvoice);
		mainLayout.setExpandRatio(vlInvoice, 1.0f);

		return mainLayout;
	}

	private VerticalLayout buildVlOfferLine() {
		// common part: create layout
		vlInvoice = new VerticalLayout();
		vlInvoice.setImmediate(false);
		vlInvoice.setWidth("100.0%");
		vlInvoice.setHeight("100.0%");
		vlInvoice.setMargin(false);

		// tbTable
		tbInvoiceLine = new Table() {
		    @Override
		    protected String formatPropertyValue(Object rowId, Object colId, Property property) {
		    	// Format by property type
		        if (property.getType() == Date.class) {
		            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		            
		            return df.format((Date)property.getValue());
		        }

		        return super.formatPropertyValue(rowId, colId, property);
		    }
		};
		tbInvoiceLine.setImmediate(true);
		tbInvoiceLine.setSelectable(true);
		tbInvoiceLine.setMultiSelect(false);
		tbInvoiceLine.setColumnCollapsingAllowed(true);
		tbInvoiceLine.setWidth("100.0%");
		tbInvoiceLine.setHeight("100.0%");		

		vlInvoice.addComponent(tbInvoiceLine);
		vlInvoice.setExpandRatio(tbInvoiceLine, 1.0f);

		// hlToolbar
		hlToolbar = buildHlToolbar();
		vlInvoice.addComponent(hlToolbar);

		return vlInvoice;
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