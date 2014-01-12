package com.thingtrack.konekti.view.module.modulemanager.internal;

import java.util.ArrayList;
import java.util.List;

import com.thingtrack.konekti.service.api.MenuCommandResourceService;
import com.thingtrack.konekti.view.addon.ui.AbstractViewForm;
import com.thingtrack.konekti.view.kernel.IModuleService;
import com.thingtrack.konekti.view.kernel.MetadataModule;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.TableDragMode;

@SuppressWarnings("serial")
public class AvailableModulesComponent extends AbstractViewForm {

	// Konekti Platform Services
	private MenuCommandResourceService menuCommandResourceService;
	private IModuleService moduleService;

	// Data Container
	private BeanItemContainer<MetadataModule> availableModuleTableContainer;

	public BeanItemContainer<MetadataModule> getAvailableModuleTableContainer() {
		return availableModuleTableContainer;
	}

	// Data Components
	private Table availableModuleTable;

	public AvailableModulesComponent() {

		buildMainLayout();
		setCompositionRoot(availableModuleTable);
	}

	private void buildMainLayout() {

		availableModuleTable = new Table();
		availableModuleTable.setWidth("450px");
		availableModuleTable.setHeight("100%");
		availableModuleTable.setDragMode(TableDragMode.ROW);
	}

	public void loadData(MenuCommandResourceService menuCommandResourceService, IModuleService moduleService) throws Exception {
		List<MetadataModule> unconfiguredModules = new ArrayList<MetadataModule>();

		List<MetadataModule> availableModules = moduleService.getMetadataModules();

		// Remove all modules are already configured
		for (MetadataModule metadataModule : availableModules) {

			try {
				menuCommandResourceService.getById(metadataModule.getId(), metadataModule.getVersion());
				continue;
				
			} catch (Exception e) {
				unconfiguredModules.add(metadataModule);
			}
		}

		// Set the unconfigured modules to the data container and component
		availableModuleTableContainer = new BeanItemContainer<MetadataModule>(MetadataModule.class, unconfiguredModules);
		availableModuleTable.setContainerDataSource(availableModuleTableContainer);

		availableModuleTable.setVisibleColumns(new String[] { "id", "version" });
		availableModuleTable.setColumnHeaders(new String[] { "Identificador", "Versión" });
	}

	@Override
	protected void updateLabels() {
		availableModuleTable.setColumnHeaders(new String[] { getI18N().getMessage("com.thingtrack.konekti.view.module.modulemanager.internal.AvailableModulesComponent.availableModuleTable.column.id"), 
				  getI18N().getMessage("com.thingtrack.konekti.view.module.modulemanager.internal.AvailableModulesComponent.availableModuleTable.column.organization.version")});
		
	}

}
