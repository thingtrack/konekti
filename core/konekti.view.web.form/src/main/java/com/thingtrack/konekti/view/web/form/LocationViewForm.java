package com.thingtrack.konekti.view.web.form;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import com.thingtrack.konekti.domain.LocationType;
import com.thingtrack.konekti.service.api.LocationTypeService;
import com.thingtrack.konekti.view.addon.data.BindingSource;
import com.thingtrack.konekti.view.web.form.field.AddressField;
import com.thingtrack.konekti.view.web.form.field.WarehouseCollectionField;
import com.thingtrack.konekti.view.web.form.field.WorkshopCollectionField;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Select;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class LocationViewForm extends CustomComponent {

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private TabSheet tbsLocation;
	@AutoGenerated
	private ComboBox locationTypeField;
	@AutoGenerated
	private TextField nameField;
	@AutoGenerated
	private AddressField locationAddressField;
	@AutoGenerated
	private TextField descriptionField;
	@AutoGenerated
	private TextField commentField;
	@AutoGenerated
	private TextField codeField;
	@AutoGenerated
	private CheckBox activeField;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private WarehouseCollectionField warehouseCollectionField;
	private WorkshopCollectionField workshopCollectionField;
		
	// form services	
	private LocationTypeService locationTypeService;
	
	// location datasource
	private BeanItemContainer<LocationType> bcLocationType = new BindingSource<LocationType>(LocationType.class);
	
	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 */
	public LocationViewForm() throws IllegalArgumentException, Exception {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		initComponents();
		
		// configure Location Type data
		locationTypeField.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
		locationTypeField.setItemCaptionPropertyId("code");
		
		tbsLocation.setCaption("Almacenes/Talleres");
		
		warehouseCollectionField = new WarehouseCollectionField();		
		warehouseCollectionField.setSizeFull();
		tbsLocation.addTab(warehouseCollectionField, "Almacenes");
		
		workshopCollectionField = new WorkshopCollectionField();
		workshopCollectionField.setSizeFull();
		tbsLocation.addTab(workshopCollectionField, "Talleres");
				
		// get form services from OSGi Service Registry
		getServices();
		
		// load data sources
		loadData();
	}
	
	
	private void initComponents() {
		nameField.setNullRepresentation("");
		descriptionField.setNullRepresentation("");
		codeField.setNullRepresentation("");
		commentField.setNullRepresentation("");
		
		codeField.setRequiredError(codeField.getCaption() + " es un campo requerido");
		nameField.setRequiredError(nameField.getCaption() + " es un campo requerido");
		locationTypeField.setRequiredError(locationTypeField.getCaption() + " es un campo requerido");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void getServices() {
		try {
			BundleContext bundleContext = FrameworkUtil.getBundle(LocationViewForm.class).getBundleContext();
						
			ServiceReference locationTypeServiceReference = bundleContext.getServiceReference(LocationTypeService.class.getName());
			locationTypeService = LocationTypeService.class.cast(bundleContext.getService(locationTypeServiceReference));
		}
		catch (Exception e) {
			e.getMessage();
			
		}
		
	}
	
	private void loadData() throws IllegalArgumentException, Exception {		
		bcLocationType.removeAllItems();
		bcLocationType.addAll(locationTypeService.getAll());
		
		locationTypeField.setContainerDataSource(bcLocationType);
		
	}
	
	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("560px");
		mainLayout.setHeight("520px");
		mainLayout.setMargin(true);
		
		// top-level component properties
		setWidth("560px");
		setHeight("520px");
		
		// activeField
		activeField = new CheckBox();
		activeField.setCaption("Activo");
		activeField.setImmediate(false);
		activeField.setWidth("-1px");
		activeField.setHeight("20px");
		activeField.setTabIndex(3);
		activeField.setRequired(true);
		mainLayout.addComponent(activeField, "top:20.0px;left:486.0px;");
		
		// codeField
		codeField = new TextField();
		codeField.setCaption("Código");
		codeField.setImmediate(false);
		codeField.setWidth("120px");
		codeField.setHeight("-1px");
		codeField.setTabIndex(1);
		codeField.setRequired(true);
		mainLayout.addComponent(codeField, "top:20.0px;left:20.0px;");
		
		// commentField
		commentField = new TextField();
		commentField.setCaption("Comentarios");
		commentField.setImmediate(false);
		commentField.setWidth("520px");
		commentField.setHeight("80px");
		commentField.setTabIndex(6);
		mainLayout.addComponent(commentField, "top:261.0px;left:20.0px;");
		
		// descriptionField
		descriptionField = new TextField();
		descriptionField.setCaption("Descripción");
		descriptionField.setImmediate(false);
		descriptionField.setWidth("320px");
		descriptionField.setHeight("-1px");
		descriptionField.setTabIndex(4);
		mainLayout.addComponent(descriptionField, "top:60.0px;left:20.0px;");
		
		// locationAddressField
		locationAddressField = new AddressField();
		locationAddressField.setImmediate(false);
		locationAddressField.setWidth("520px");
		locationAddressField.setHeight("135px");
		mainLayout.addComponent(locationAddressField,
				"top:100.0px;left:20.0px;");
		
		// nameField
		nameField = new TextField();
		nameField.setCaption("Nombre");
		nameField.setImmediate(false);
		nameField.setWidth("320px");
		nameField.setHeight("-1px");
		nameField.setTabIndex(2);
		nameField.setRequired(true);
		mainLayout.addComponent(nameField, "top:20.0px;left:160.0px;");
		
		// locationTypeField
		locationTypeField = new ComboBox();
		locationTypeField.setCaption("Tipo Ubicación");
		locationTypeField.setImmediate(false);
		locationTypeField.setWidth("-1px");
		locationTypeField.setHeight("-1px");
		locationTypeField.setTabIndex(5);
		locationTypeField.setRequired(true);
		mainLayout.addComponent(locationTypeField, "top:60.0px;left:346.0px;");
		
		// tbsLocation
		tbsLocation = new TabSheet();
		tbsLocation.setImmediate(false);
		tbsLocation.setWidth("520px");
		tbsLocation.setHeight("135px");
		mainLayout.addComponent(tbsLocation, "top:358.0px;left:20.0px;");
		
		return mainLayout;
	}
}
