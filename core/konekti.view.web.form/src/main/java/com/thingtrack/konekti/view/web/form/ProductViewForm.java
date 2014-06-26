package com.thingtrack.konekti.view.web.form;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import com.thingtrack.konekti.domain.ProductType;
import com.thingtrack.konekti.domain.Supplier;
import com.thingtrack.konekti.service.api.ProductTypeService;
import com.thingtrack.konekti.service.api.SupplierService;
import com.thingtrack.konekti.view.addon.data.BindingSource;
import com.thingtrack.konekti.view.addon.ui.AbstractViewForm;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.web.form.field.ProductUnitCollectionField;
import com.thingtrack.konekti.view.web.form.field.ProductAreaCollectionField;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class ProductViewForm extends AbstractViewForm {

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private ProductUnitCollectionField productUnitsField;
	@AutoGenerated
	private ProductAreaCollectionField areasField;
	@AutoGenerated
	private ComboBox productSupplierField;
	@AutoGenerated
	private ComboBox productTypeField;
	@AutoGenerated
	private CheckBox productActiveField;
	@AutoGenerated
	private TextField nameField;
	@AutoGenerated
	private CheckBox hasSerialNumberField;
	@AutoGenerated
	private CheckBox hasLoteField;
	@AutoGenerated
	private CheckBox hasExpeditionDateField;
	@AutoGenerated
	private TextField descriptionField;
	@AutoGenerated
	private TextField codeField;
	@AutoGenerated
	private TextField averagePriceField;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private SupplierService supplierService;
	private ProductTypeService productTypeService;	
	
	private BeanItemContainer<Supplier> bcSupplier = new BindingSource<Supplier>(Supplier.class);	
	private BeanItemContainer<ProductType> bcProductType = new BindingSource<ProductType>(ProductType.class);
	
	private IWorkbenchContext context;
	
	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 */
	public ProductViewForm(IWorkbenchContext context) throws IllegalArgumentException, Exception {
		this.context = context;
		
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		initComponents();
		
		// configure Organization Type data
		productSupplierField.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
		productSupplierField.setItemCaptionPropertyId("description");
		
		productTypeField.setNullSelectionAllowed(false);
		productTypeField.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
		productTypeField.setItemCaptionPropertyId("description");
		
		// get form services from OSGi Service Registry
		getServices();
		
		// load data sources
		loadData();
	}
		
	private void initComponents() {
		nameField.setNullRepresentation("");
		descriptionField.setNullRepresentation("");
		codeField.setNullRepresentation("");
		averagePriceField.setNullRepresentation("");
		
		productUnitsField.setCaption("Unidades");
		areasField.setCaption("Areas");
		
		codeField.setRequiredError(codeField.getCaption() + " es un campo requerido");
		nameField.setRequiredError(codeField.getCaption() + " es un campo requerido");
		productTypeField.setRequiredError(productTypeField.getCaption() + " es un campo requerido");
		
		
		codeField.focus();
	}
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void getServices() {
		try {
			BundleContext bundleContext = FrameworkUtil.getBundle(ProductViewForm.class).getBundleContext();

			ServiceReference SupplierServiceReference = bundleContext.getServiceReference(SupplierService.class.getName());
			supplierService = SupplierService.class.cast(bundleContext.getService(SupplierServiceReference));
			
			ServiceReference productTypeServiceReference = bundleContext.getServiceReference(ProductTypeService.class.getName());
			productTypeService = ProductTypeService.class.cast(bundleContext.getService(productTypeServiceReference));
			
		}
		catch (Exception e) {
			e.getMessage();
			
		}
		
	}
	
	private void loadData() throws IllegalArgumentException, Exception {
		bcSupplier.removeAllItems();
		bcSupplier.addAll(supplierService.getAll(context.getUser().getActiveOrganization()));	
		
		productSupplierField.setContainerDataSource(bcSupplier);
		
		bcProductType.removeAllItems();
		bcProductType.addAll(productTypeService.getAll(context.getUser().getActiveArea()));		
		
		productTypeField.setContainerDataSource(bcProductType);
	}
	
	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("700px");
		mainLayout.setHeight("280px");
		mainLayout.setMargin(true);
		
		// top-level component properties
		setWidth("700px");
		setHeight("280px");
		
		// averagePriceField
		averagePriceField = new TextField();
		averagePriceField.setCaption("Precio medio");
		averagePriceField.setImmediate(false);
		averagePriceField.setWidth("80px");
		averagePriceField.setHeight("-1px");
		averagePriceField.setTabIndex(5);
		mainLayout.addComponent(averagePriceField, "top:57.0px;left:365.0px;");
		
		// codeField
		codeField = new TextField();
		codeField.setCaption("Código");
		codeField.setImmediate(false);
		codeField.setWidth("120px");
		codeField.setHeight("-1px");
		codeField.setTabIndex(1);
		codeField.setRequired(true);
		mainLayout.addComponent(codeField, "top:16.0px;left:20.0px;");
		
		// descriptionField
		descriptionField = new TextField();
		descriptionField.setCaption("Description");
		descriptionField.setImmediate(false);
		descriptionField.setWidth("665px");
		descriptionField.setHeight("-1px");
		descriptionField.setTabIndex(7);
		mainLayout.addComponent(descriptionField, "top:216.0px;left:20.0px;");
		
		// hasExpeditionDateField
		hasExpeditionDateField = new CheckBox();
		hasExpeditionDateField.setCaption("Con fecha caducidad");
		hasExpeditionDateField.setImmediate(false);
		hasExpeditionDateField.setWidth("-1px");
		hasExpeditionDateField.setHeight("-1px");
		hasExpeditionDateField.setTabIndex(10);
		hasExpeditionDateField.setRequired(true);
		mainLayout.addComponent(hasExpeditionDateField,
				"top:242.0px;left:273.0px;");
		
		// hasLoteField
		hasLoteField = new CheckBox();
		hasLoteField.setCaption("Con lote");
		hasLoteField.setImmediate(false);
		hasLoteField.setWidth("-1px");
		hasLoteField.setHeight("-1px");
		hasLoteField.setTabIndex(9);
		hasLoteField.setRequired(true);
		mainLayout.addComponent(hasLoteField, "top:242.0px;left:170.0px;");
		
		// hasSerialNumberField
		hasSerialNumberField = new CheckBox();
		hasSerialNumberField.setCaption("Con número serie");
		hasSerialNumberField.setImmediate(false);
		hasSerialNumberField.setWidth("-1px");
		hasSerialNumberField.setHeight("-1px");
		hasSerialNumberField.setTabIndex(8);
		hasSerialNumberField.setRequired(true);
		mainLayout.addComponent(hasSerialNumberField,
				"top:242.0px;left:20.0px;");
		
		// nameField
		nameField = new TextField();
		nameField.setCaption("Nombre");
		nameField.setImmediate(false);
		nameField.setWidth("445px");
		nameField.setHeight("-1px");
		nameField.setTabIndex(3);
		nameField.setRequired(true);
		mainLayout.addComponent(nameField, "top:16.0px;left:160.0px;");
		
		// productActiveField
		productActiveField = new CheckBox();
		productActiveField.setCaption("Activo");
		productActiveField.setImmediate(false);
		productActiveField.setWidth("-1px");
		productActiveField.setHeight("-1px");
		productActiveField.setTabIndex(3);
		productActiveField.setRequired(true);
		mainLayout.addComponent(productActiveField, "top:16.0px;left:625.0px;");
		
		// productTypeField
		productTypeField = new ComboBox();
		productTypeField.setCaption("Tipo producto");
		productTypeField.setImmediate(false);
		productTypeField.setWidth("220px");
		productTypeField.setHeight("-1px");
		productTypeField.setTabIndex(6);
		productTypeField.setRequired(true);
		mainLayout.addComponent(productTypeField, "top:57.0px;left:465.0px;");
		
		// productSupplierField
		productSupplierField = new ComboBox();
		productSupplierField.setCaption("Proveedor");
		productSupplierField.setImmediate(false);
		productSupplierField.setWidth("327px");
		productSupplierField.setHeight("-1px");
		productSupplierField.setTabIndex(6);
		productSupplierField.setRequired(true);
		mainLayout.addComponent(productSupplierField,
				"top:176.0px;left:20.0px;");
		
		// areasField
		areasField = new ProductAreaCollectionField(context);
		areasField.setImmediate(false);
		areasField.setWidth("320px");
		areasField.setHeight("100px");
		mainLayout.addComponent(areasField, "top:100.0px;left:365.0px;");
		
		// productUnitsField
		productUnitsField = new ProductUnitCollectionField(context);
		productUnitsField.setImmediate(false);
		productUnitsField.setWidth("325px");
		productUnitsField.setHeight("103px");
		mainLayout.addComponent(productUnitsField, "top:57.0px;left:20.0px;");
		
		return mainLayout;
	}

	@Override
	protected void updateLabels() {
		productUnitsField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ProductViewForm.productUnitsField.caption"));
		areasField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ProductViewForm.areasField.caption"));
		productSupplierField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ProductViewForm.productSupplierField.caption"));
		productTypeField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ProductViewForm.productTypeField.caption"));
		productActiveField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ProductViewForm.productActiveField.caption"));
		nameField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ProductViewForm.nameField.caption"));
		hasSerialNumberField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ProductViewForm.hasSerialNumberField.caption"));
		hasLoteField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ProductViewForm.hasLoteField.caption"));
		hasExpeditionDateField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ProductViewForm.hasExpeditionDateField.caption"));
		descriptionField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ProductViewForm.descriptionField.caption"));
		codeField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ProductViewForm.codeField.caption"));
		averagePriceField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ProductViewForm.averagePriceField.caption"));
		
	}
}