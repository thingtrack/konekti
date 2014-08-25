package com.thingtrack.konekti.view.module.address.internal;

import org.vaadin.dialogs.ConfirmDialog;

import com.thingtrack.konekti.domain.Address;
import com.thingtrack.konekti.service.api.AddressService;
import com.thingtrack.konekti.view.addon.data.BindingSource;
import com.thingtrack.konekti.view.addon.ui.AbstractView;
import com.thingtrack.konekti.view.addon.ui.BoxToolbar;
import com.thingtrack.konekti.view.addon.ui.BoxToolbar.ClickFilterButtonListener;
import com.thingtrack.konekti.view.addon.ui.BoxToolbar.ClickPrintButtonListener;
import com.thingtrack.konekti.view.addon.ui.DataGridView;
import com.thingtrack.konekti.view.addon.ui.EditionToolbar.ClickAddButtonListener;
import com.thingtrack.konekti.view.addon.ui.EditionToolbar.ClickEditButtonListener;
import com.thingtrack.konekti.view.addon.ui.EditionToolbar.ClickNavigationEvent;
import com.thingtrack.konekti.view.addon.ui.EditionToolbar.ClickRemoveButtonListener;
import com.thingtrack.konekti.view.addon.ui.NavigationToolbar;
import com.thingtrack.konekti.view.addon.ui.NavigationToolbar.ClickRefreshButtonListener;
import com.thingtrack.konekti.view.addon.ui.WindowDialog;
import com.thingtrack.konekti.view.addon.ui.WindowDialog.DialogResult;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.kernel.ui.layout.IViewContainer;
import com.thingtrack.konekti.view.web.form.AddressViewForm;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AddressView extends AbstractView implements
		ClickRefreshButtonListener, ClickAddButtonListener,
		ClickEditButtonListener, ClickRemoveButtonListener, ClickFilterButtonListener,
		ClickPrintButtonListener {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private DataGridView dgAddress;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private AddressService addressService;

	private BindingSource<Address> bsAddress = new BindingSource<Address>(Address.class, 0);

	private NavigationToolbar navigationToolbar;
	// private EditionToolbar editionToolbar;
	private BoxToolbar boxToolbar;

	private IWorkbenchContext context;
	private IViewContainer viewContainer;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public AddressView(IWorkbenchContext context, IViewContainer viewContainer) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		this.context = context;
		
		// set Slide View Services and ViewContainer to navigate
		this.viewContainer = viewContainer;
		this.addressService = AddressViewContainer.getAddressService();

		// initialize datasource views
		initView();
	}

	private void initView() {
		// initialize Slide View Organization View
		dgAddress.setImmediate(true);

		refreshBindindSource();

		// STEP 01: create grid view for slide Organization View
		try {
			// add nested columns
			bsAddress.addNestedContainerProperty("organization.code");
			
			// add bind datasource
			dgAddress.setBindingSource(bsAddress);
						
			dgAddress.setVisibleColumns(new String[] { "street", "number", "letter", "city", "zipCode", "province", "country", "fax", "email", "web", "organization.code" });
			dgAddress.setColumnHeaders(new String[] { "Calle", "Número", "Letra", "Ciudad", "Código Postal", "Provincia", "País", "Fax", "Email", "Web", "Organización" });

			dgAddress.setColumnCollapsed("fax", true);
			dgAddress.setColumnCollapsed("email", true);
			dgAddress.setColumnCollapsed("web", true);
		} catch (Exception ex) {
			ex.getMessage();
		}

		// STEP 02: create toolbar for slide Employee Agent View
		navigationToolbar = new NavigationToolbar(0, bsAddress, viewContainer);
		boxToolbar = new BoxToolbar(2, bsAddress);
		
		boxToolbar.addListenerFilterButton(this);
		boxToolbar.addListenerPrintButton(this);
		
		navigationToolbar.addListenerRefreshButton(this);
		navigationToolbar.setUpButton(false);
		navigationToolbar.setDownButton(false);

		removeAllToolbar();

		addToolbar(navigationToolbar);
		addToolbar(boxToolbar);

	}

	private void refreshBindindSource() {
		try {
			bsAddress.removeAllItems();
			bsAddress.addAll(addressService.getAll());

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void refreshButtonClick(NavigationToolbar.ClickNavigationEvent event) {
		refreshBindindSource();

	}

	private void refreshDataGridView(Address addressSaved) {
		if (bsAddress.containsId(addressSaved)) {
			Address previousAddressn = bsAddress.prevItemId(addressSaved);
			bsAddress.removeItem(addressSaved);
			bsAddress.addItemAfter(previousAddressn, addressSaved);
		} else
			bsAddress.addItem(addressSaved);
	}

	@Override
	public void addButtonClick(ClickNavigationEvent event) {
		Address address = new Address();

		try {
			@SuppressWarnings("unused")
			WindowDialog<Address> windowDialog = new WindowDialog<Address>(
					getWindow(), "Nuevo Dirección", "Guardar",
					DialogResult.SAVE, "Cancelar", DialogResult.CANCEL,
					new AddressViewForm(), address,
					new WindowDialog.CloseWindowDialogListener<Address>() {
						public void windowDialogClose(
								WindowDialog<Address>.CloseWindowDialogEvent<Address> event) {
							if (event.getDialogResult() != WindowDialog.DialogResult.SAVE)
								return;

							try {
								Address savingAddress = event.getDomainEntity();

								Address savedAddress = addressService
										.save(savingAddress);

								// refresh binding source
								refreshDataGridView(savedAddress);

							} catch (Exception e) {
								e.printStackTrace();

							}
						}

					});
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void editButtonClick(ClickNavigationEvent event) {
		Address editingAddress = (Address) event.getRegister();

		try {
			@SuppressWarnings("unused")
			WindowDialog<Address> windowDialog = new WindowDialog<Address>(
					getWindow(), "Editor Dirección", "Guardar",
					DialogResult.SAVE, "Cancelar", DialogResult.CANCEL,
					new AddressViewForm(), editingAddress,
					new WindowDialog.CloseWindowDialogListener<Address>() {
						public void windowDialogClose(
								WindowDialog<Address>.CloseWindowDialogEvent<Address> event) {
							if (event.getDialogResult() != WindowDialog.DialogResult.SAVE)
								return;

							try {
								Address savingAddress = event.getDomainEntity();

								Address savedAddress = addressService
										.save(savingAddress);

								// refresh binding source
								refreshDataGridView(savedAddress);

							} catch (Exception e) {
								e.printStackTrace();

							}
						}

					});
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleteButtonClick(ClickNavigationEvent event) {
		final Address editingAddress = (Address) event.getRegister();

		if (editingAddress == null)
			return;

		ConfirmDialog.show(getWindow(), "Borrar Dirección", "¿Estás seguro?",
				"Si", "No", new ConfirmDialog.Listener() {

					public void onClose(ConfirmDialog dialog) {
						if (dialog.isConfirmed()) {
							try {
								addressService.delete(editingAddress);

								// refresh
								refreshBindindSource();

							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});

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

		// dgEmployee
		dgAddress = new DataGridView();
		dgAddress.setImmediate(false);
		dgAddress.setWidth("100.0%");
		dgAddress.setHeight("100.0%");
		mainLayout.addComponent(dgAddress);
		mainLayout.setExpandRatio(dgAddress, 1.0f);

		return mainLayout;
	}

	@Override
	public void filterButtonClick(BoxToolbar.ClickNavigationEvent event) {
		dgAddress.setFilterBarVisible();
		
	}

	@Override
	public void printButtonClick(BoxToolbar.ClickNavigationEvent event) {
		try {
			dgAddress.print("Listado Direcciones");
		}
		catch (Exception e) {
			throw new RuntimeException("¡No se pudo imprimir el informe!", e);
		}
		
	}

	@Override
	protected void updateLabels() {
		dgAddress.setColumnHeaders(new String[] { getI18N().getMessage("com.thingtrack.konekti.view.module.address.internal.AddressView.dgAddress.column.street"), 
				  getI18N().getMessage("com.thingtrack.konekti.view.module.address.internal.AddressView.dgAddress.column.number"), 
				  getI18N().getMessage("com.thingtrack.konekti.view.module.address.internal.AddressView.dgAddress.column.letter"), 
				  getI18N().getMessage("com.thingtrack.konekti.view.module.address.internal.AddressView.dgAddress.column.city"),
				  getI18N().getMessage("com.thingtrack.konekti.view.module.address.internal.AddressView.dgAddress.column.zipCode"),
				  getI18N().getMessage("com.thingtrack.konekti.view.module.address.internal.AddressView.dgAddress.column.province"),
				  getI18N().getMessage("com.thingtrack.konekti.view.module.address.internal.AddressView.dgAddress.column.country"),
				  getI18N().getMessage("com.thingtrack.konekti.view.module.address.internal.AddressView.dgAddress.column.fax"),
				  getI18N().getMessage("com.thingtrack.konekti.view.module.address.internal.AddressView.dgAddress.column.email"),
				  getI18N().getMessage("com.thingtrack.konekti.view.module.address.internal.AddressView.dgAddress.column.web"),
				  getI18N().getMessage("com.thingtrack.konekti.view.module.address.internal.AddressView.dgAddress.column.organizatin.code")});
		
	}

}
