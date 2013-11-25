package com.thingtrack.konekti.view.web.form.field;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.vaadin.addon.customfield.CustomField;
import org.vaadin.peter.buttongroup.ButtonGroup;

import com.thingtrack.konekti.domain.Product;
import com.thingtrack.konekti.service.api.ProductService;
import com.thingtrack.konekti.view.addon.ui.WindowDialog;
import com.thingtrack.konekti.view.addon.ui.WindowDialog.DialogResult;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.web.form.ProductViewForm;
import com.thingtrack.konekti.view.web.form.selector.ProductSelectorWindow;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class ProductSelectorField extends CustomField {
	@AutoGenerated
	private HorizontalLayout mainLayout;
	@AutoGenerated
	private Button btnSearchEntity;
	@AutoGenerated
	private Button btnClearEntity;
	@AutoGenerated
	private Button btnAddEntity;
	@AutoGenerated
	private TextField productField;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	
	private IWorkbenchContext context;
	
	private Product product;
	
	private ProductService productService;
	
	private boolean clearEntityVisible = true;
	private boolean addEntityVisible = true;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public ProductSelectorField() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		getServices();
		
		productField.setNullRepresentation("");
		productField.setRequiredError(productField.getCaption() + " es un campo requerido");
		
		btnSearchEntity.addListener(new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					@SuppressWarnings("unused")
					ProductSelectorWindow windowDialog = new ProductSelectorWindow(context, getApplication().getMainWindow(), product, new ProductSelectorWindow.CloseWindowDialogListener() {
					    public void windowDialogClose(ProductSelectorWindow.CloseWindowDialogEvent event) {
					    	if (event.getDialogResult() != ProductSelectorWindow.DialogResult.SELECT)
					    		return ;
					    	
					    	product = event.getDomainEntity();
					    	productField.setValue(product.getCode());
					    }
		
					});
				} catch (Exception e) {
					throw new RuntimeException("¡No se pudo abrir el selector de productos!", e);
				}
			}
		});
		
		btnClearEntity.addListener(new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				product = null;
				productField.setValue(null);
			}
		});
		
		btnAddEntity.addListener(new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				Product product = new Product();

				try {
					@SuppressWarnings("unused")
					WindowDialog<Product> windowDialog = new WindowDialog<Product>(getApplication().getMainWindow(), "Nuevo Producto", 
							"Guardar", DialogResult.SAVE, "Cancelar", DialogResult.CANCEL, 
							new ProductViewForm(context),  product, 
							new WindowDialog.CloseWindowDialogListener<Product>() {
					    public void windowDialogClose(WindowDialog<Product>.CloseWindowDialogEvent<Product> event) {
					    	if (event.getDialogResult() != WindowDialog.DialogResult.SAVE)
					    		return ;
					    	
					    	try {
					    		Product savingProduct = event.getDomainEntity();
					    		
					    		Product savedProduct = productService.save(savingProduct);
					    		savingProduct.setProductId(savedProduct.getProductId());
					    		
					    		productField.setValue(savingProduct.getCode());	
					    		
					    		ProductSelectorField.this.product = savingProduct;
							} catch (Exception e) {
								throw new RuntimeException(
										"¡No se pudo crear el nuevo producto!",
										e);
								
							}
					    }

					});
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(
							"¡No se pudo abrir el formulario Nuevo Producto!", e);
				} catch (Exception e) {
					throw new RuntimeException(
							"¡No se pudo abrir el formulario Nuevo Producto!", e);
				} 
			}
		});
		
		productField.addListener(new ValueChangeListener() {	
			@Override
			public void valueChange(Property.ValueChangeEvent event) {				
				String productCode = event.getProperty().getValue().toString();
		    	
		    	if (productCode != null && !productCode.isEmpty()) {
					try {
						product = productService.getByCode(productCode);
					} catch (Exception e) {						
						getWindow().showNotification("El producto no existe", "", Window.Notification.TYPE_WARNING_MESSAGE);
						
						productField.setValue("");
						productField.focus();

					}
		    	}
		    	
				fireValueChange(true);
			}
		});
		
		btnClearEntity.setVisible(clearEntityVisible);
		btnAddEntity.setVisible(addEntityVisible);
	}
	
	@Override
    public void focus() {
		productField.focus();
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void getServices() {
		try {
			BundleContext bundleContext = FrameworkUtil.getBundle(ProductSelectorField.class).getBundleContext();
			
			ServiceReference holeServiceReference = bundleContext.getServiceReference(ProductService.class.getName());
			productService = ProductService.class.cast(bundleContext.getService(holeServiceReference));
			
		}
		catch (Exception e) {
			e.getMessage();
			
		}
		
	}
	
	public void setContext(IWorkbenchContext context) {
		this.context = context;
	}
	
	@Override
	public void setPropertyDataSource(Property newDataSource) {
		product = (Product) newDataSource.getValue();

		if (product != null)
			productField.setValue(product.getCode());
		
		super.setPropertyDataSource(newDataSource);
	}
	
	@Override
	public Object getValue() {
		return product;
	}
	
	@Override
	public Class<?> getType() {
		return Product.class;
		
	}
	
	/**
	 * @return the clearEntityVisible
	 */
	public boolean isClearEntityVisible() {
		return clearEntityVisible;
	}

	/**
	 * @param clearEntityVisible the clearEntityVisible to set
	 */
	public void setClearEntityVisible(boolean clearEntityVisible) {
		this.clearEntityVisible = clearEntityVisible;
		btnClearEntity.setVisible(clearEntityVisible);
	}

	/**
	 * @return the addEntityVisible
	 */
	public boolean isAddEntityVisible() {
		return addEntityVisible;
	}

	/**
	 * @param addEntityVisible the addEntityVisible to set
	 */
	public void setAddEntityVisible(boolean addEntityVisible) {
		this.addEntityVisible = addEntityVisible;
		btnAddEntity.setVisible(addEntityVisible);
	}
	
	@AutoGenerated
	private HorizontalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new HorizontalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("28px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("-1px");
		setHeight("28px");
		
		// holeField
		productField = new TextField();
		productField.setImmediate(true);
		productField.setWidth("150px");
		productField.setHeight("-1px");
		mainLayout.addComponent(productField);
		
		ButtonGroup editionButtonGroup = new ButtonGroup();
		
		// btnSearch
		btnSearchEntity = new Button();
		btnSearchEntity.setIcon(new ThemeResource("../konekti/images/icons/container-module/magnifier-zoom.png"));
		btnSearchEntity.setImmediate(true);
		btnSearchEntity.setWidth("-1px");
		btnSearchEntity.setHeight("-1px");
		editionButtonGroup.addButton(btnSearchEntity);
		
		btnClearEntity = new Button();
		btnClearEntity.setIcon(new ThemeResource("../konekti/images/icons/eraser.png"));
		btnClearEntity.setImmediate(true);
		btnClearEntity.setWidth("-1px");
		btnClearEntity.setHeight("-1px");
		editionButtonGroup.addButton(btnClearEntity);
		
		btnAddEntity = new Button();
		btnAddEntity.setIcon(new ThemeResource("../konekti/images/icons/plus-circle.png"));
		btnAddEntity.setImmediate(true);
		btnAddEntity.setWidth("-1px");
		btnAddEntity.setHeight("-1px");
		editionButtonGroup.addButton(btnAddEntity);
		
		mainLayout.addComponent(editionButtonGroup);
		mainLayout.setExpandRatio(editionButtonGroup, 1.0f);
		
		return mainLayout;
	}

}
