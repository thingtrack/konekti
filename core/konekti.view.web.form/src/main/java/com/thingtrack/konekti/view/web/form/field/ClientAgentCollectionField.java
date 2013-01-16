package com.thingtrack.konekti.view.web.form.field;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.vaadin.addon.customfield.CustomField;
import org.vaadin.dialogs.ConfirmDialog;

import com.thingtrack.konekti.domain.Client;
import com.thingtrack.konekti.domain.ClientAgent;
import com.thingtrack.konekti.view.addon.ui.WindowDialog;
import com.thingtrack.konekti.view.addon.ui.WindowDialog.DialogResult;
import com.thingtrack.konekti.view.web.form.ClientAgentViewForm;
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
public class ClientAgentCollectionField extends CustomField {
	private VerticalLayout mainLayout;
	private VerticalLayout vlClientAgent;
	private HorizontalLayout hlToolbar;
	private Button btnRemove;
	private Button btnAdd;	
	private Table tbClientAgent;	
	
	private BeanItemContainer<ClientAgent> clientAgentTableContainer;
	
	private Client client;
	
	public ClientAgentCollectionField() {	
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		clientAgentTableContainer = new BeanItemContainer<ClientAgent>(ClientAgent.class);
		tbClientAgent.setContainerDataSource(clientAgentTableContainer);
		
		tbClientAgent.setVisibleColumns(new String[] { "name", "surname", "phone", "active"} );       
		tbClientAgent.setColumnHeaders(new String[] { "Nombre", "Apellidos", "Teléfono", "Activa" } );
		
		tbClientAgent.setColumnCollapsed("code", true);
		tbClientAgent.setColumnCollapsed("comment", true);		
		
		// set button event handlers
		btnAdd.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				ClientAgent clientAgent = new ClientAgent();				
				clientAgent.setClient(client);
				
				try {
					@SuppressWarnings("unused")
					WindowDialog<ClientAgent> windowDialog = new WindowDialog<ClientAgent>(getApplication().getMainWindow(), "Nuevo Contacto", 
							"Guardar", DialogResult.SAVE, "Cancelar", DialogResult.CANCEL, 
							new ClientAgentViewForm(), clientAgent, 
							new WindowDialog.CloseWindowDialogListener<ClientAgent>() {
					    public void windowDialogClose(WindowDialog<ClientAgent>.CloseWindowDialogEvent<ClientAgent> event) {
					    	if (event.getDialogResult() != WindowDialog.DialogResult.SAVE)
					    		return ;
					    	
					    	try {
					    		ClientAgent savingClientAgent = event.getDomainEntity();
					    		
					    		clientAgentTableContainer.addBean(savingClientAgent);
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
				final ClientAgent clientAgentSelected = (ClientAgent) tbClientAgent.getValue();
				
				try {
					ConfirmDialog.show(getApplication().getMainWindow(), "Borrar Contacto",
					        "¿Estás seguro?", "Si", "No",
					        new ConfirmDialog.Listener() {
	
					            public void onClose(ConfirmDialog dialog) {
					                if (dialog.isConfirmed()) {
					            		try {
					            			clientAgentTableContainer.removeItem(clientAgentSelected);					            			
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
			WindowDialog<Client> clientWindowDialog = (WindowDialog<Client>)getParent().getWindow();
			client = clientWindowDialog.getDomainEntity();
    		
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
		
		clientAgentTableContainer.removeAllItems();
		clientAgentTableContainer.addAll(collectionsValue);
				
		super.setPropertyDataSource(newDataSource);
	}
	
	@Override
	public Object getValue() {		
		return new ArrayList<ClientAgent>(
				(Collection<? extends ClientAgent>) clientAgentTableContainer.getItemIds());
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
		vlClientAgent = buildVlOrganization();
		mainLayout.addComponent(vlClientAgent);
		mainLayout.setExpandRatio(vlClientAgent, 1.0f);
		
		return mainLayout;
	}

	private VerticalLayout buildVlOrganization() {
		// common part: create layout
		vlClientAgent = new VerticalLayout();
		vlClientAgent.setImmediate(false);
		vlClientAgent.setWidth("100.0%");
		vlClientAgent.setHeight("100.0%");
		vlClientAgent.setMargin(false);
		
		// tbTable
		tbClientAgent = new Table();
		tbClientAgent.setImmediate(true);
		tbClientAgent.setSelectable(true);
		tbClientAgent.setMultiSelect(false);
		tbClientAgent.setColumnCollapsingAllowed(true);
		tbClientAgent.setWidth("100.0%");
		tbClientAgent.setHeight("100.0%");
		tbClientAgent.setEditable(true);
		tbClientAgent.setTableFieldFactory(new TableFieldFactory() {					
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
		
		vlClientAgent.addComponent(tbClientAgent);
		vlClientAgent.setExpandRatio(tbClientAgent, 1.0f);
		
		// hlToolbar
		hlToolbar = buildHlToolbar();
		vlClientAgent.addComponent(hlToolbar);
		
		return vlClientAgent;
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
