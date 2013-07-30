package com.thingtrack.konekti.view.module.knowledge.internal;

import org.drools.runtime.StatefulKnowledgeSession;
import org.vaadin.dialogs.ConfirmDialog;

import com.thingtrack.konekti.domain.Knowledge;
import com.thingtrack.konekti.service.api.KnowledgeService;
import com.thingtrack.konekti.view.addon.data.BindingSource;
import com.thingtrack.konekti.view.addon.ui.AbstractView;
import com.thingtrack.konekti.view.addon.ui.BoxToolbar;
import com.thingtrack.konekti.view.addon.ui.EditionToolbar;
import com.thingtrack.konekti.view.addon.ui.WindowDialog;
import com.thingtrack.konekti.view.addon.ui.BoxToolbar.ClickFilterButtonListener;
import com.thingtrack.konekti.view.addon.ui.BoxToolbar.ClickPrintButtonListener;
import com.thingtrack.konekti.view.addon.ui.EditionToolbar.ClickAddButtonListener;
import com.thingtrack.konekti.view.addon.ui.EditionToolbar.ClickEditButtonListener;
import com.thingtrack.konekti.view.addon.ui.EditionToolbar.ClickRemoveButtonListener;
import com.thingtrack.konekti.view.addon.ui.DataGridView;
import com.thingtrack.konekti.view.addon.ui.NavigationToolbar;
import com.thingtrack.konekti.view.addon.ui.NavigationToolbar.ClickNavigationEvent;
import com.thingtrack.konekti.view.addon.ui.NavigationToolbar.ClickRefreshButtonListener;
import com.thingtrack.konekti.view.addon.ui.WindowDialog.DialogResult;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.kernel.ui.layout.IViewContainer;
import com.thingtrack.konekti.view.module.knowledge.addon.KnowledgeToolbar;
import com.thingtrack.konekti.view.module.knowledge.addon.KnowledgeToolbar.ClickActiveWorkflowButtonListener;
import com.thingtrack.konekti.view.web.form.KnowledgeViewForm;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class KnowledgeView extends AbstractView implements
		ClickRefreshButtonListener, ClickAddButtonListener, ClickEditButtonListener,
		ClickRemoveButtonListener, ClickFilterButtonListener, ClickPrintButtonListener, ClickActiveWorkflowButtonListener {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private DataGridView dgKnowledge;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private KnowledgeService knowledgeService;
    private StatefulKnowledgeSession statefulKnowledgeSession;
    
	private BindingSource<Knowledge> bsKnowledge = new BindingSource<Knowledge>(Knowledge.class, 0);
	
	private NavigationToolbar navigationToolbar;
	private EditionToolbar editionToolbar;
	private BoxToolbar boxToolbar;
	private KnowledgeToolbar knowledgeToolbar;
	
	private IViewContainer viewContainer;
	private IWorkbenchContext context;
	
	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public KnowledgeView(IWorkbenchContext context, IViewContainer viewContainer) {
		this.context = context;
		this.viewContainer = viewContainer;
		
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here		
		this.knowledgeService = KnowledgeViewContainer.getKnowledgeService();
		this.statefulKnowledgeSession = KnowledgeViewContainer.getStatefulKnowledgeSession();
		
		// initialize datasource views
		initView();

	}

	private void initView() {
		// initialize Slide View Organization View
		dgKnowledge.setImmediate(true);
		dgKnowledge.setSelectable(true);
		
		refreshBindindSource();
		
		try {
			bsKnowledge.addNestedContainerProperty("organization.description");
			
			dgKnowledge.setBindingSource(bsKnowledge);
			dgKnowledge.setVisibleColumns(new String[] { "knowledgeId", "organization.description", "name", "version", "description", "packageName", "error", "errorMessage", "active" });
			dgKnowledge.setColumnHeaders(new String[] { "Id", "Organización", "Nombre", "Version", "Descripción", "Paquete", "Error", "Mensaje Error", "Activo" });
			dgKnowledge.setEditable(true);
			dgKnowledge.setTableFieldFactory(new TableFieldFactory() {
				@Override
				public Field createField(Container container, Object itemId,
						Object propertyId, Component uiContext) {
					if ("active".equals(propertyId)) {
						CheckBox field = new CheckBox();
						field.setReadOnly(true);
						return field;
					}

					return null;
				}
			});
			
			dgKnowledge.setColumnCollapsed("knowledgeId", true);
			dgKnowledge.setColumnCollapsed("packageName", true);
			dgKnowledge.setColumnCollapsed("errorMessage", true);
			
			dgKnowledge.setTableFieldFactory(new TableFieldFactory() {					
				@Override
				public Field createField(Container container, Object itemId, Object propertyId, Component uiContext) {
					if("active".equals(propertyId) || "error".equals(propertyId)) {						
						CheckBox field = new CheckBox();
						field.setReadOnly(true);						
						return field;
					}
					
					return null;
				}
			});
		} catch (Exception ex) {
			ex.getMessage();
		}

		// STEP 02: create toolbar for slide Employee Agent View
		navigationToolbar = new NavigationToolbar(0, bsKnowledge, viewContainer);
		editionToolbar = new EditionToolbar(1, bsKnowledge);
		boxToolbar = new BoxToolbar(2, bsKnowledge);
		knowledgeToolbar = new KnowledgeToolbar(3, bsKnowledge);
		
		navigationToolbar.addListenerRefreshButton(this);
		navigationToolbar.setUpButton(false);
		navigationToolbar.setDownButton(false);
		
		editionToolbar.addListenerAddButton(this);
		editionToolbar.addListenerEditButton(this);
		editionToolbar.addListenerDeleteButton(this);
		editionToolbar.setPermission(context.getUser(), viewContainer.getModule().getSymbolicName(), viewContainer.getModule().getVersion());
		
		boxToolbar.addListenerFilterButton(this);
		boxToolbar.addListenerPrintButton(this);		
		
		knowledgeToolbar.addListenerExecuteReportButton(this);
		
		dgKnowledge.addListenerAddButton(this);
		dgKnowledge.addListenerEditButton(this);
		dgKnowledge.addListenerDeleteButton(this);
		
		removeAllToolbar();

		addToolbar(navigationToolbar);
		addToolbar(editionToolbar);
		addToolbar(boxToolbar);
		addToolbar(knowledgeToolbar);
		
	}

	private void refreshBindindSource() {
		try {
			bsKnowledge.removeAllItems();
			bsKnowledge.addAll(knowledgeService.getAll());

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void refreshButtonClick(ClickNavigationEvent event) {
		refreshBindindSource();

	}

	private void refreshDataGridView(Knowledge knowledgeSaved) {
		if (bsKnowledge.containsId(knowledgeSaved)) {
			Knowledge previousKnowledge = bsKnowledge.prevItemId(knowledgeSaved);

			bsKnowledge.removeItem(knowledgeSaved);
			bsKnowledge.addItemAfter(previousKnowledge, knowledgeSaved);
			bsKnowledge.setItemId(knowledgeSaved);
		} else
			bsKnowledge.addItem(knowledgeSaved);

	}
	
	@Override
	public void addButtonClick(EditionToolbar.ClickNavigationEvent event) {
		Knowledge knowledge = null;
		try {
			knowledge = knowledgeService.createNewEntity(context.getUser().getActiveOrganization());
		} catch (Exception e) {
			throw new RuntimeException(
					"¡No se pudo crear el nuevo conocimiento!",
					e);
		}	
		
		try {
			@SuppressWarnings("unused")
			WindowDialog<Knowledge> windowDialog = new WindowDialog<Knowledge>(
					getWindow(), "Nuevo Cliente", "Guardar", DialogResult.SAVE,
					"Cancelar", DialogResult.CANCEL, new KnowledgeViewForm(), knowledge,
					new WindowDialog.CloseWindowDialogListener<Knowledge>() {
						public void windowDialogClose(
								WindowDialog<Knowledge>.CloseWindowDialogEvent<Knowledge> event) {
							if (event.getDialogResult() != WindowDialog.DialogResult.SAVE)
								return;

							try {
								Knowledge savingKnowledge = event.getDomainEntity();
								Knowledge savedKnowledge = knowledgeService.save(savingKnowledge);

								savingKnowledge.setKnowledgeId(savedKnowledge.getKnowledgeId());
								
								refreshDataGridView(savingKnowledge);
							} catch (Exception e) {
								throw new RuntimeException(
										"¡No se pudo crear el nuevo conocimiento!",
										e);

							}
						}

					});
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(
					"¡No se pudo abrir el formulario Nuevo Informe!", e);
		} catch (Exception e) {
			throw new RuntimeException(
					"¡No se pudo abrir el formulario Nuevo Informe!", e);
		}
		
	}
	
	@Override
	public void editButtonClick(EditionToolbar.ClickNavigationEvent event) {
		Knowledge editingKnowledge = (Knowledge) event.getRegister();

		try {
			@SuppressWarnings("unused")
			WindowDialog<Knowledge> windowDialog = new WindowDialog<Knowledge>(
					getWindow(), "Editor Conocimiento", "Guardar",
					DialogResult.SAVE, "Cancelar", DialogResult.CANCEL,
					new KnowledgeViewForm(), editingKnowledge,
					new WindowDialog.CloseWindowDialogListener<Knowledge>() {
						public void windowDialogClose(
								WindowDialog<Knowledge>.CloseWindowDialogEvent<Knowledge> event) {
							if (event.getDialogResult() != WindowDialog.DialogResult.SAVE)
								return;

							try {
								Knowledge savingKnowledge = event.getDomainEntity();
								Knowledge savedKnowledge = knowledgeService.save(savingKnowledge);
								
								refreshDataGridView(savingKnowledge);

							} catch (Exception e) {
								throw new RuntimeException(
										"¡No se pudo modificar el conocimiento!", e);

							}
						}

					});
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(
					"¡No se pudo abrir el formulario Editor Conocimiento!", e);
		} catch (Exception e) {
			throw new RuntimeException(
					"¡No se pudo abrir el formulario Editor Conocimiento!", e);
		}
		
	}	

	@Override
	public void deleteButtonClick(EditionToolbar.ClickNavigationEvent event) {
		final Knowledge editingKnowledge = (Knowledge) event.getRegister();

		if (editingKnowledge == null)
			return;

		ConfirmDialog.show(getWindow(), "Borrar Conocimiento", "¿Estás seguro?",
				"Si", "No", new ConfirmDialog.Listener() {

					public void onClose(ConfirmDialog dialog) {
						if (dialog.isConfirmed()) {
							try {
								knowledgeService.delete(editingKnowledge);

								bsKnowledge.removeItem(editingKnowledge);

							} catch (IllegalArgumentException e) {
								throw new RuntimeException(
										"¡No se pudo borrar el conocimiento!", e);
							} catch (Exception e) {
								throw new RuntimeException(
										"¡No se pudo borrar el conocimiento!", e);
							}
						}
					}
				});
		
	}
	
	@Override
	public void filterButtonClick(BoxToolbar.ClickNavigationEvent event) {
		dgKnowledge.setFilterBarVisible();

	}

	@Override
	public void printButtonClick(BoxToolbar.ClickNavigationEvent event) {
		try {
			dgKnowledge.print("Listado Conocimiento");
		}
		catch (Exception e) {
			throw new RuntimeException("¡No se pudo imprimir el conocimiento!", e);
		}
		
	}
	
	@Override
	public void activeWorksflowButtonClick(KnowledgeToolbar.ClickNavigationEvent event) {
		Knowledge editingKnowledge = (Knowledge) event.getRegister();
		
		if (editingKnowledge == null)
			return;
		
		try {
			if (editingKnowledge.getActive())
				knowledgeService.setDesActive(editingKnowledge);
			else
				knowledgeService.setActive(editingKnowledge);
			
			refreshDataGridView(editingKnowledge);
			
		} catch (Exception e) {
			throw new RuntimeException("¡No se pudo cambiar estado workflow!", e);
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

		// dgKnowledge
		dgKnowledge = new DataGridView();
		dgKnowledge.setImmediate(false);
		dgKnowledge.setWidth("100.0%");
		dgKnowledge.setHeight("100.0%");
		mainLayout.addComponent(dgKnowledge);
		mainLayout.setExpandRatio(dgKnowledge, 1.0f);

		return mainLayout;
	}

}
