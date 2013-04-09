package com.thingtrack.konekti.view.web.form.field;

import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.vaadin.addon.customfield.CustomField;

import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.domain.Client;
import com.thingtrack.konekti.domain.Location;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.service.api.OrganizationService;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ClientOrganizationCollectionField extends CustomField {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private TreeTable clientOrganizationTreeTable;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	
	private OrganizationService organizationService;
	
	private Client client;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public ClientOrganizationCollectionField() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		getServices();
		
		clientOrganizationTreeTable.addContainerProperty("Codigo", CheckBox.class, "");
		clientOrganizationTreeTable.addContainerProperty("Descripcion", String.class, "");
		clientOrganizationTreeTable.addContainerProperty("Defecto", CheckBox.class, "");
		
	}

	@Override
	public void setValue(Object object) {
		super.setValue(object);
		
		if(object instanceof Client){
			client = (Client)object;

			try {
				fillTreeTable();
				
				// Expand the tree
				for (Object itemId: clientOrganizationTreeTable.getItemIds())
					clientOrganizationTreeTable.setCollapsed(itemId, false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
					
	}
	
	@Override
	public void setPropertyDataSource(Property newDataSource) {
		if(newDataSource.getValue() instanceof Client){
			client = (Client) newDataSource.getValue();

			try {
				fillTreeTable();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		super.setPropertyDataSource(newDataSource);
	}

	@Override
	public Class<?> getType() {
		if(getPropertyDataSource() instanceof Property)
			return getPropertyDataSource().getType();

		return Client.class;
	}

	@Override
	public Object getValue() {		
		return client;

	}

	private void fillTreeTable() throws Exception {
		List<Organization> organizations = organizationService.getAll();
		
		int organizationId = 0;
		int locationId = 0;
		int areaId = 0;
		int entityId = 0;
		
		for (Organization organization : organizations) {
			final CheckBox organizationCheck = new CheckBox(organization.getCode());
			organizationCheck.setImmediate(true);
			organizationCheck.setData(organization);
			organizationCheck.setValue(containsOrganization(organization.getCode()));
			organizationCheck.addListener(new ValueChangeListener() {
				@Override
				public void valueChange(Property.ValueChangeEvent event) {
					if ((Boolean)organizationCheck.getValue())
						client.getOrganizations().add((Organization)organizationCheck.getData());
					else
						client.getOrganizations().remove((Organization)organizationCheck.getData());
					
				}
			});
			final CheckBox organizationDefaultCheck = new CheckBox();
			organizationDefaultCheck.setImmediate(true);
			organizationDefaultCheck.setData(organization);
			organizationDefaultCheck.setValue(isDefaultOrganization(organization.getCode()));
			organizationDefaultCheck.addListener(new ValueChangeListener() {

				@Override
				public void valueChange(Property.ValueChangeEvent event) {
					if ((Boolean)organizationDefaultCheck.getValue())
						if ((Boolean)organizationDefaultCheck.getValue()) {
							client.setDefaultOrganization((Organization)organizationDefaultCheck.getData());
							unsetDefaultOrganization(((Organization)organizationDefaultCheck.getData()).getCode());
						}
					
				}
			});			
			clientOrganizationTreeTable.addItem(new Object[]{organizationCheck, organization.getName(), organizationDefaultCheck}, entityId);			
			organizationId = entityId;
			
			entityId++;
			for (Location location : organization.getLocations()) {		
				final CheckBox locationCheck = new CheckBox(location.getCode());
				locationCheck.setData(location);
				locationCheck.setImmediate(true);
				locationCheck.setValue(containsLocation(location.getCode()));
				locationCheck.addListener(new ValueChangeListener() {

					@Override
					public void valueChange(Property.ValueChangeEvent event) {
						if ((Boolean)locationCheck.getValue())
							client.getLocations().add((Location)locationCheck.getData());
						else
							client.getLocations().remove((Location)locationCheck.getData());
						
					}
				});
				final CheckBox locationDefaultCheck = new CheckBox();
				locationDefaultCheck.setImmediate(true);
				locationDefaultCheck.setData(location);
				locationDefaultCheck.setValue(isDefaultLocation(location.getCode()));
				locationDefaultCheck.addListener(new ValueChangeListener() {

					@Override
					public void valueChange(Property.ValueChangeEvent event) {						
						if ((Boolean)locationDefaultCheck.getValue()) {
							client.setDefaultLocation((Location)locationDefaultCheck.getData());
							unsetDefaultLocation(((Location)locationDefaultCheck.getData()).getCode());
						}						
						
					}
				});
				clientOrganizationTreeTable.addItem(new Object[]{locationCheck, location.getName(), locationDefaultCheck}, entityId);
				locationId = entityId;
				
				clientOrganizationTreeTable.setParent(locationId, organizationId);
				
				entityId++;
				for (Area area : location.getAreas()) {
					final CheckBox areaCheck = new CheckBox(area.getCode());
					areaCheck.setImmediate(true);
					areaCheck.setData(area);
					areaCheck.setValue(containsArea(area.getCode()));
					areaCheck.addListener(new ValueChangeListener() {

						@Override
						public void valueChange(Property.ValueChangeEvent event) {
							if ((Boolean)areaCheck.getValue())
								client.getAreas().add((Area)areaCheck.getData());
							else
								client.getAreas().remove((Area)areaCheck.getData());
							
						}
					});
					final CheckBox areaDefaultCheck = new CheckBox();
					areaDefaultCheck.setImmediate(true);
					areaDefaultCheck.setData(area);
					areaDefaultCheck.setValue(isDefaultArea(area.getCode()));
					areaDefaultCheck.addListener(new ValueChangeListener() {

						@Override
						public void valueChange(Property.ValueChangeEvent event) {
							if ((Boolean)areaDefaultCheck.getValue()) {
								client.setDefaultArea((Area)areaDefaultCheck.getData());
								unsetDefaultArea(((Area)areaDefaultCheck.getData()).getCode());
							}
							
						}
					});
					clientOrganizationTreeTable.addItem(new Object[]{areaCheck, area.getName(), areaDefaultCheck}, entityId);
					areaId = entityId;
					
					clientOrganizationTreeTable.setParent(areaId, locationId);
				
					entityId++;
				}
				
			}

		}		
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getServices(){		
		BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		
		ServiceReference organizationServiceReference = bundleContext.getServiceReference(OrganizationService.class.getName());
		organizationService =  OrganizationService.class.cast(bundleContext.getService(organizationServiceReference));
	}
	
	private boolean containsOrganization(String code) {
		for (Organization organization : client.getOrganizations()) {
			if (organization.getCode().equals(code))
				return true;
		}
		
		return false;
		
	}
	
	private boolean isDefaultOrganization(String code) {
		if (client.getDefaultOrganization() != null && client.getDefaultOrganization().getCode().equals(code))
			return true;
		
		return false;
		
	}
	
	private boolean containsLocation(String code) {
		for (Location location : client.getLocations()) {
			if (location.getCode().equals(code))
				return true;
		}
		
		return false;
		
	}

	private boolean isDefaultLocation(String code) {
		if (client.getDefaultLocation() != null && client.getDefaultLocation().getCode().equals(code))
			return true;
		
		return false;
		
	}
	
	private boolean containsArea(String code) {
		for (Area area : client.getAreas()) {
			if (area.getCode().equals(code))
				return true;
		}
		
		return false;
		
	}
	
	private boolean isDefaultArea(String code) {
		if (client.getDefaultArea() != null && client.getDefaultArea().getCode().equals(code))
			return true;
		
		return false;
		
	}
	
	private void unsetDefaultOrganization(String code) {
		for (Object itemId: clientOrganizationTreeTable.getItemIds()) {
			Item item = clientOrganizationTreeTable.getItem(itemId);
			
			CheckBox defaultOrganizationCheckbox = (CheckBox)item.getItemProperty("Defecto").getValue();
			
			if (defaultOrganizationCheckbox.getData() instanceof Organization) {
				if (!((Organization)defaultOrganizationCheckbox.getData()).getCode().equals(code))
					defaultOrganizationCheckbox.setValue(false);
			}
		}
		
	}
	
	private void unsetDefaultLocation(String code) {
		for (Object itemId: clientOrganizationTreeTable.getItemIds()) {
			Item item = clientOrganizationTreeTable.getItem(itemId);
			
			CheckBox defaultLocationCheckbox = (CheckBox)item.getItemProperty("Defecto").getValue();
			
			if (defaultLocationCheckbox.getData() instanceof Location) {
				if (!((Location)defaultLocationCheckbox.getData()).getCode().equals(code))
					defaultLocationCheckbox.setValue(false);
			}
		}
		
	}
	
	private void unsetDefaultArea(String code) {
		for (Object itemId: clientOrganizationTreeTable.getItemIds()) {
			Item item = clientOrganizationTreeTable.getItem(itemId);
			
			CheckBox defaultAreaCheckbox = (CheckBox)item.getItemProperty("Defecto").getValue();
			
			if (defaultAreaCheckbox.getData() instanceof Area) {
				if (!((Area)defaultAreaCheckbox.getData()).getCode().equals(code))
					defaultAreaCheckbox.setValue(false);			
			}
		}
		
	}
	
	@AutoGenerated
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

		// employeeAgentOrganizationTreeTable
		clientOrganizationTreeTable = new TreeTable();
		clientOrganizationTreeTable.setImmediate(true);
		clientOrganizationTreeTable.setWidth("100.0%");
		clientOrganizationTreeTable.setHeight("100.0%");
		mainLayout.addComponent(clientOrganizationTreeTable);
		mainLayout.setExpandRatio(clientOrganizationTreeTable, 1.0f);

		return mainLayout;
	}

}
