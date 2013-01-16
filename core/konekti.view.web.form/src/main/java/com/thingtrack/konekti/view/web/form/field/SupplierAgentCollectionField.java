package com.thingtrack.konekti.view.web.form.field;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.vaadin.addon.customfield.CustomField;
import org.vaadin.dialogs.ConfirmDialog;

import com.thingtrack.konekti.domain.Supplier;
import com.thingtrack.konekti.domain.SupplierAgent;
import com.thingtrack.konekti.view.addon.ui.WindowDialog;
import com.thingtrack.konekti.view.addon.ui.WindowDialog.DialogResult;
import com.thingtrack.konekti.view.web.form.SupplierAgentViewForm;
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
public class SupplierAgentCollectionField extends CustomField {
	private VerticalLayout mainLayout;
	private VerticalLayout vlSupplierAgent;
	private HorizontalLayout hlToolbar;
	private Button btnRemove;
	private Button btnAdd;	
	private Table tbSupplierAgent;	
	
	private BeanItemContainer<SupplierAgent> supplierAgentTableContainer;
	
	private Supplier supplier;
	
	public SupplierAgentCollectionField() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		supplierAgentTableContainer = new BeanItemContainer<SupplierAgent>(SupplierAgent.class);
		tbSupplierAgent.setContainerDataSource(supplierAgentTableContainer);
		
		tbSupplierAgent.setVisibleColumns(new String[] { "name", "surname", "phone", "active"} );       
		tbSupplierAgent.setColumnHeaders(new String[] { "Nombre", "Apellidos", "Teléfono", "Activa" } );
		
		tbSupplierAgent.setColumnCollapsed("code", true);
		tbSupplierAgent.setColumnCollapsed("comment", true);		
		
		// set button event handlers
		btnAdd.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				SupplierAgent supplierAgent = new SupplierAgent();				
				supplierAgent.setSupplier(supplier);
				
				try {
					@SuppressWarnings("unused")
					WindowDialog<SupplierAgent> windowDialog = new WindowDialog<SupplierAgent>(getApplication().getMainWindow(), "Nuevo Contacto", 
							"Guardar", DialogResult.SAVE, "Cancelar", DialogResult.CANCEL, 
							new SupplierAgentViewForm(), supplierAgent, 
							new WindowDialog.CloseWindowDialogListener<SupplierAgent>() {
					    public void windowDialogClose(WindowDialog<SupplierAgent>.CloseWindowDialogEvent<SupplierAgent> event) {
					    	if (event.getDialogResult() != WindowDialog.DialogResult.SAVE)
					    		return ;
					    	
					    	try {
					    		SupplierAgent savingSupplierAgent = event.getDomainEntity();
					    		
					    		supplierAgentTableContainer.addBean(savingSupplierAgent);
							} catch (Exception e) {
								throw new RuntimeException("¡No se pudo crear el nuevo Contacto!", e);
								
							}
					    }

					});
				} catch (IllegalArgumentException e) {
					throw new RuntimeException("¡No se pudo abrir el formulario Nuevo Contacto!", e);
				} catch (Exception e) {
					throw new RuntimeException("¡No se pudo abrir el formulario Nuevo Contacto!", e);
				}
			}
		});
		
		btnRemove.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				final SupplierAgent supplierAgentSelected = (SupplierAgent) tbSupplierAgent.getValue();
				
				try {
					ConfirmDialog.show(getApplication().getMainWindow(), "Borrar Contacto",
					        "¿Estás seguro?", "Si", "No",
					        new ConfirmDialog.Listener() {
	
					            public void onClose(ConfirmDialog dialog) {
					                if (dialog.isConfirmed()) {
					            		try {
					            			supplierAgentTableContainer.removeItem(supplierAgentSelected);					            			
					            		} catch (IllegalArgumentException e) {
					            			throw new RuntimeException("¡No se pudo borrar el nuevo contacto!", e);
					            		} catch (Exception e) {
					            			throw new RuntimeException("¡No se pudo borrar el nuevo contacto!", e);
					            		}
					                } 
					            }
					        });
				} catch (IllegalArgumentException e) {
					throw new RuntimeException("¡No se pudo borrar el nuevo contacto!", e);
				} catch (Exception e) {
					throw new RuntimeException("¡No se pudo borrar el nuevo contacto!", e);
				} 
			}
		});
	}
	
	@Override
    public void attach() {  
		// recover the parent entity (Vehicle) from parent view window (VehicleViewForm)
		if (getParent().getWindow() instanceof WindowDialog<?>) {
			@SuppressWarnings("unchecked")
			WindowDialog<Supplier> supplierWindowDialog = (WindowDialog<Supplier>)getParent().getWindow();
			supplier = supplierWindowDialog.getDomainEntity();
    		
		}
	}
	
	@Override
	public Class<?> getType() {
		return List.class;
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void setPropertyDataSource(Property newDataSource) {
		List collectionsValue = (List) newDataSource.getValue();
		
		supplierAgentTableContainer.removeAllItems();
		supplierAgentTableContainer.addAll(collectionsValue);
				
		super.setPropertyDataSource(newDataSource);
	}
	
	@Override
	public Object getValue() {		
		return new ArrayList<SupplierAgent>(
				(Collection<? extends SupplierAgent>) supplierAgentTableContainer.getItemIds());
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
		vlSupplierAgent = buildVlOrganization();
		mainLayout.addComponent(vlSupplierAgent);
		mainLayout.setExpandRatio(vlSupplierAgent, 1.0f);
		
		return mainLayout;
	}

	private VerticalLayout buildVlOrganization() {
		// common part: create layout
		vlSupplierAgent = new VerticalLayout();
		vlSupplierAgent.setImmediate(false);
		vlSupplierAgent.setWidth("100.0%");
		vlSupplierAgent.setHeight("100.0%");
		vlSupplierAgent.setMargin(false);
		
		// tbTable
		tbSupplierAgent = new Table();
		tbSupplierAgent.setImmediate(true);
		tbSupplierAgent.setSelectable(true);
		tbSupplierAgent.setMultiSelect(false);
		tbSupplierAgent.setColumnCollapsingAllowed(true);
		tbSupplierAgent.setWidth("100.0%");
		tbSupplierAgent.setHeight("100.0%");
		tbSupplierAgent.setEditable(true);
		tbSupplierAgent.setTableFieldFactory(new TableFieldFactory() {					
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
		
		vlSupplierAgent.addComponent(tbSupplierAgent);
		vlSupplierAgent.setExpandRatio(tbSupplierAgent, 1.0f);
		
		// hlToolbar
		hlToolbar = buildHlToolbar();
		vlSupplierAgent.addComponent(hlToolbar);
		
		return vlSupplierAgent;
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
