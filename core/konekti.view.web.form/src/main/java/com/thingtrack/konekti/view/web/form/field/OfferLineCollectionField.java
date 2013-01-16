package com.thingtrack.konekti.view.web.form.field;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.vaadin.addon.customfield.CustomField;

import com.thingtrack.konekti.domain.OfferLine;
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
public class OfferLineCollectionField extends CustomField {
	private VerticalLayout mainLayout;
	private VerticalLayout vlOfferLine;
	private HorizontalLayout hlToolbar;
	private Button btnRemove;
	private Button btnAdd;	
	private Table tbOfferLine;

	private BeanItemContainer<OfferLine> offerLineTableContainer;
	
	public OfferLineCollectionField() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		offerLineTableContainer = new BeanItemContainer<OfferLine>(OfferLine.class);
		tbOfferLine.setContainerDataSource(offerLineTableContainer);

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

		offerLineTableContainer.removeAllItems();
		offerLineTableContainer.addAll(collectionsValue);

		offerLineTableContainer.addNestedContainerProperty("offerLineStatus.description");
		
		tbOfferLine.setVisibleColumns(new String[] { "number", "price", "comment", "offerLineStatus.description", "offerLineDate"} );       
		tbOfferLine.setColumnHeaders(new String[] { "Número", "Precio", "Comentario", "Estado", "Fecha" } );

		tbOfferLine.setColumnCollapsed("comment", true);	
		
		super.setPropertyDataSource(newDataSource);
	}

	@Override
	public Object getValue() {		
		return new ArrayList<OfferLine>(
				(Collection<? extends OfferLine>) offerLineTableContainer.getItemIds());
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
		vlOfferLine = buildVlOfferLine();
		mainLayout.addComponent(vlOfferLine);
		mainLayout.setExpandRatio(vlOfferLine, 1.0f);

		return mainLayout;
	}

	private VerticalLayout buildVlOfferLine() {
		// common part: create layout
		vlOfferLine = new VerticalLayout();
		vlOfferLine.setImmediate(false);
		vlOfferLine.setWidth("100.0%");
		vlOfferLine.setHeight("100.0%");
		vlOfferLine.setMargin(false);

		// tbTable
		tbOfferLine = new Table() {
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
		tbOfferLine.setImmediate(true);
		tbOfferLine.setSelectable(true);
		tbOfferLine.setMultiSelect(false);
		tbOfferLine.setColumnCollapsingAllowed(true);
		tbOfferLine.setWidth("100.0%");
		tbOfferLine.setHeight("100.0%");		

		vlOfferLine.addComponent(tbOfferLine);
		vlOfferLine.setExpandRatio(tbOfferLine, 1.0f);

		// hlToolbar
		hlToolbar = buildHlToolbar();
		vlOfferLine.addComponent(hlToolbar);

		return vlOfferLine;
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