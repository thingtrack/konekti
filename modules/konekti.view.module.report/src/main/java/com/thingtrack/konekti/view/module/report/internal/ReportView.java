package com.thingtrack.konekti.view.module.report.internal;

import org.vaadin.dialogs.ConfirmDialog;

import com.thingtrack.konekti.domain.Report;
import com.thingtrack.konekti.report.ReportManagerService;
import com.thingtrack.konekti.service.api.ReportService;
import com.thingtrack.konekti.view.addon.data.BindingSource;
import com.thingtrack.konekti.view.addon.ui.AbstractView;
import com.thingtrack.konekti.view.addon.ui.BoxToolbar;
import com.thingtrack.konekti.view.addon.ui.EditionToolbar;
import com.thingtrack.konekti.view.addon.ui.PrintViewForm;
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
import com.thingtrack.konekti.view.module.report.addon.ReportToolbar;
import com.thingtrack.konekti.view.module.report.addon.ReportToolbar.ClickExecuteReportButtonListener;
import com.thingtrack.konekti.view.web.form.ReportViewForm;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ReportView extends AbstractView implements
		ClickRefreshButtonListener, ClickAddButtonListener, ClickEditButtonListener,
		ClickRemoveButtonListener, ClickFilterButtonListener, ClickPrintButtonListener, ClickExecuteReportButtonListener {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private DataGridView dgReport;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private ReportService reportService;
    private ReportManagerService reportManagerService;
    
	private BindingSource<Report> bsReport = new BindingSource<Report>(Report.class, 0);
	
	private NavigationToolbar navigationToolbar;
	private EditionToolbar editionToolbar;
	private BoxToolbar boxToolbar;
	private ReportToolbar reportToolbar;
	
	private IViewContainer viewContainer;
	private IWorkbenchContext context;
	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public ReportView(IWorkbenchContext context, IViewContainer viewContainer) {
		this.context = context;
		
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		// set Slide View Services and ViewContainer to navigate
		this.viewContainer = viewContainer;
		
		this.reportService = ReportViewContainer.getReportService();
		this.reportManagerService = ReportViewContainer.getReportManagerService();
		
		// initialize datasource views
		initView();

	}

	private void initView() {
		// initialize Slide View Organization View
		dgReport.setImmediate(true);
		dgReport.setSelectable(true);
		
		refreshBindindSource();
		
		try {
			bsReport.addNestedContainerProperty("organization.description");
			
			dgReport.setBindingSource(bsReport);
			dgReport.setVisibleColumns(new String[] { "reportId", "organization.description", "code", "description", "active" });
			dgReport.setColumnHeaders(new String[] { "Id", "Organización", "Codigo", "Descripción", "Activo" });				
			dgReport.setEditable(true);
			dgReport.setTableFieldFactory(new TableFieldFactory() {
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

			dgReport.setColumnCollapsed("reportId", true);
		} catch (Exception ex) {
			ex.getMessage();
		}

		// STEP 02: create toolbar for slide Employee Agent View
		navigationToolbar = new NavigationToolbar(0, bsReport, viewContainer);
		editionToolbar = new EditionToolbar(1, bsReport);
		boxToolbar = new BoxToolbar(2, bsReport);
		reportToolbar = new ReportToolbar(3, bsReport);
		
		navigationToolbar.addListenerRefreshButton(this);
		navigationToolbar.setUpButton(false);
		navigationToolbar.setDownButton(false);
		
		editionToolbar.addListenerAddButton(this);
		editionToolbar.addListenerEditButton(this);
		editionToolbar.addListenerDeleteButton(this);
		editionToolbar.setPermission(context.getUser(), viewContainer.getModule().getSymbolicName(), viewContainer.getModule().getVersion());
		
		boxToolbar.addListenerFilterButton(this);
		boxToolbar.addListenerPrintButton(this);		
		
		reportToolbar.addListenerExecuteReportButton(this);
		
		dgReport.addListenerAddButton(this);
		dgReport.addListenerEditButton(this);
		dgReport.addListenerDeleteButton(this);
		
		removeAllToolbar();

		addToolbar(navigationToolbar);
		addToolbar(editionToolbar);
		addToolbar(boxToolbar);
		//addToolbar(reportToolbar);
		
	}

	private void refreshBindindSource() {
		try {
			bsReport.removeAllItems();
			bsReport.addAll(reportService.getAll(context.getUser()));

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

	private void refreshDataGridView(Report reportSaved) {
		if (bsReport.containsId(reportSaved)) {
			Report previousReport = bsReport.prevItemId(reportSaved);

			bsReport.removeItem(reportSaved);
			bsReport.addItemAfter(previousReport, reportSaved);
			bsReport.setItemId(reportSaved);
		} else
			bsReport.addItem(reportSaved);

	}
	
	@Override
	public void addButtonClick(EditionToolbar.ClickNavigationEvent event) {
		Report report = null;
		try {
			report = reportService.createNewReport(context.getUser().getActiveOrganization());
		} catch (Exception e) {
			throw new RuntimeException(
					"¡No se pudo crear el nuevo informe!",
					e);
		}	
		
		try {
			@SuppressWarnings("unused")
			WindowDialog<Report> windowDialog = new WindowDialog<Report>(
					getWindow(), "Nuevo Cliente", "Guardar", DialogResult.SAVE,
					"Cancelar", DialogResult.CANCEL, new ReportViewForm(), report,
					new WindowDialog.CloseWindowDialogListener<Report>() {
						public void windowDialogClose(
								WindowDialog<Report>.CloseWindowDialogEvent<Report> event) {
							if (event.getDialogResult() != WindowDialog.DialogResult.SAVE)
								return;

							try {
								Report savingReport = event.getDomainEntity();
								Report savedReport = reportService.save(savingReport);

								savingReport.setReportId(savedReport.getReportId());
								
								refreshDataGridView(savingReport);
							} catch (Exception e) {
								throw new RuntimeException(
										"¡No se pudo crear el nuevo informe!",
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
		Report editingReport = (Report) event.getRegister();

		try {
			@SuppressWarnings("unused")
			WindowDialog<Report> windowDialog = new WindowDialog<Report>(
					getWindow(), "Editor Informe", "Guardar",
					DialogResult.SAVE, "Cancelar", DialogResult.CANCEL,
					new ReportViewForm(), editingReport,
					new WindowDialog.CloseWindowDialogListener<Report>() {
						public void windowDialogClose(
								WindowDialog<Report>.CloseWindowDialogEvent<Report> event) {
							if (event.getDialogResult() != WindowDialog.DialogResult.SAVE)
								return;

							try {
								Report savingReport = event.getDomainEntity();

								Report savedReport = reportService.save(savingReport);
								
								refreshDataGridView(savingReport);

							} catch (Exception e) {
								throw new RuntimeException(
										"¡No se pudo modificar el informe!", e);

							}
						}

					});
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(
					"¡No se pudo abrir el formulario Editor Informe!", e);
		} catch (Exception e) {
			throw new RuntimeException(
					"¡No se pudo abrir el formulario Editor Informe!", e);
		}
		
	}	

	@Override
	public void deleteButtonClick(EditionToolbar.ClickNavigationEvent event) {
		final Report editingReport = (Report) event.getRegister();

		if (editingReport == null)
			return;

		ConfirmDialog.show(getWindow(), "Borrar Informe", "¿Estás seguro?",
				"Si", "No", new ConfirmDialog.Listener() {

					public void onClose(ConfirmDialog dialog) {
						if (dialog.isConfirmed()) {
							try {
								reportService.delete(editingReport);

								bsReport.removeItem(editingReport);

							} catch (IllegalArgumentException e) {
								throw new RuntimeException(
										"¡No se pudo borrar el informe!", e);
							} catch (Exception e) {
								throw new RuntimeException(
										"¡No se pudo borrar el informe!", e);
							}
						}
					}
				});
		
	}
	
	@Override
	public void filterButtonClick(BoxToolbar.ClickNavigationEvent event) {
		dgReport.setFilterBarVisible();

	}

	@Override
	public void printButtonClick(BoxToolbar.ClickNavigationEvent event) {
		try {
			dgReport.print("Listado Reclamaciones");
		}
		catch (Exception e) {
			throw new RuntimeException("¡No se pudo imprimir el informe!", e);
		}
		
	}
	
	@Override
	public void executeReportButtonClick(ReportToolbar.ClickNavigationEvent event) {
		Report editingReport = (Report) event.getRegister();
		
		if (editingReport == null)
			return;
		
		try {
			//reportManagerService.exportReportToPdfFile(editingReport.getCode(), null, "/home/thk01/temp/" + editingReport.getCode() + ".pdf");
			
			PrintViewForm printViewForm = new PrintViewForm();
			printViewForm.generateTemplateReport(context.getUser().getActiveOrganization(), editingReport.getCode(), null);
			
			getWindow().addWindow(printViewForm);
			
		} catch (Exception e) {
			throw new RuntimeException("¡No se pudo ejecutar el informe!", e);
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

		// dataGridView_1
		dgReport = new DataGridView();
		dgReport.setImmediate(false);
		dgReport.setWidth("100.0%");
		dgReport.setHeight("100.0%");
		mainLayout.addComponent(dgReport);
		mainLayout.setExpandRatio(dgReport, 1.0f);

		return mainLayout;
	}

	@Override
	protected void updateLabels() {
		dgReport.setColumnHeaders(new String[] { getI18N().getMessage("com.thingtrack.konekti.view.module.report.internal.ReportView.dgReport.column.reportId"), 
				  getI18N().getMessage("com.thingtrack.konekti.view.module.report.internal.ReportView.dgReport.column.organization.description"), 
				  getI18N().getMessage("com.thingtrack.konekti.view.module.report.internal.ReportView.dgReport.column.code"), 
				  getI18N().getMessage("com.thingtrack.konekti.view.module.report.internal.ReportView.dgReport.column.description"),
				  getI18N().getMessage("com.thingtrack.konekti.view.module.report.internal.ReportView.dgReport.column.active")});
		
	}

}
