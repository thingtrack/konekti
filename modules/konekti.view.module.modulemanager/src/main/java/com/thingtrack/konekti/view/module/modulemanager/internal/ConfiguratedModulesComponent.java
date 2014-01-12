package com.thingtrack.konekti.view.module.modulemanager.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vaadin.teemu.switchui.Switch;

import com.thingtrack.konekti.domain.MenuCommandResource;
import com.thingtrack.konekti.domain.MenuCommandResource.LOCATION;
import com.thingtrack.konekti.domain.MenuCommandResource.TYPE;
import com.thingtrack.konekti.domain.MenuFolderResource;
import com.thingtrack.konekti.domain.MenuResource;
import com.thingtrack.konekti.domain.MenuWorkbench;
import com.thingtrack.konekti.service.api.MenuCommandResourceService;
import com.thingtrack.konekti.service.api.MenuFolderResourceService;
import com.thingtrack.konekti.service.api.MenuWorkbenchService;
import com.thingtrack.konekti.view.addon.ui.AbstractViewForm;
import com.thingtrack.konekti.view.kernel.IModuleService;
import com.thingtrack.konekti.view.kernel.MetadataModule;
import com.thingtrack.konekti.view.module.modulemanager.ConfiguratorModule;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.DataBoundTransferable;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.ServerSideCriterion;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.client.ui.dd.VerticalDropLocation;
import com.vaadin.ui.AbstractSelect.AbstractSelectTargetDetails;
import com.vaadin.ui.AbstractSelect.ItemDescriptionGenerator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.TableDragMode;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class ConfiguratedModulesComponent extends AbstractViewForm {

	// Platform services
	private IModuleService moduleService;
	@SuppressWarnings("unused")
	private MenuWorkbenchService menuWorkbenchService;
	private MenuFolderResourceService menuFolderResourceService;
	private MenuCommandResourceService menuCommandResourceService;

	// Data Container
	private HierarchicalContainer configuredModuleTreeTableContainer;

	// Data Component
	private TreeTable configuredModuleTreeTable;

	// Listeners
	private List<MenuResourceListener> listeners;

	// List of removed menu resources
	List<MenuResource> removedMenuResources;

	public ConfiguratedModulesComponent() {

		buildMainLayout();
		setCompositionRoot(configuredModuleTreeTable);

		listeners = new ArrayList<MenuResourceListener>();
		removedMenuResources = new ArrayList<MenuResource>();
	}

	private void buildMainLayout() {

		configuredModuleTreeTable = new TreeTable();
		configuredModuleTreeTable.setSizeFull();
		configuredModuleTreeTable.setSelectable(true);
		configuredModuleTreeTable.setDragMode(TableDragMode.ROW);

		// Allow the table to receive drops and handle them
		configuredModuleTreeTable.setDropHandler(new DropHandler() {

			@Override
			public AcceptCriterion getAcceptCriterion() {
				// Accept drops in the middle of container items
				// and below and above all items.
				return new ServerSideCriterion() {

					@Override
					public boolean accept(DragAndDropEvent dragEvent) {

						// Wrapper for the object that is dragged
						DataBoundTransferable transferable = (DataBoundTransferable) dragEvent
								.getTransferable();

						AbstractSelectTargetDetails target = (AbstractSelectTargetDetails) dragEvent
								.getTargetDetails();

						// The tree item on which the load hovers
						Object sourceItemId = transferable.getData("itemId");
						Object targetItemId = target.getItemIdOver();

						// On which side of the target the item is hovered
						VerticalDropLocation location = target
								.getDropLocation();

						if (sourceItemId instanceof MenuFolderResource)
							return ((MenuFolderResource) sourceItemId)
									.getMenuResources().isEmpty();

						if (targetItemId instanceof MenuFolderResource) {

							return !(sourceItemId instanceof MenuCommandResource
									&& configuredModuleTreeTable
											.isRoot(targetItemId) && (location == VerticalDropLocation.TOP || location == VerticalDropLocation.BOTTOM));
						}

						else if (targetItemId instanceof MenuCommandResource) {

							return location != VerticalDropLocation.MIDDLE;
						}

						return false;
					}
				};
			}

			@SuppressWarnings("unchecked")
			@Override
			public void drop(DragAndDropEvent event) {

				// Wrapper for the object that is dragged
				final DataBoundTransferable transferable = (DataBoundTransferable) event
						.getTransferable();

				AbstractSelectTargetDetails target = (AbstractSelectTargetDetails) event
						.getTargetDetails();

				// Get ids of the dragged item and the target item
				final Object sourceItemId = transferable.getData("itemId");
				final Object targetItemId = target.getItemIdOver();

				// Check that the target is not in the subtree of
				// the dragged item itself
				for (Object itemId = targetItemId; itemId != null; itemId = configuredModuleTreeTable
						.getParent(itemId))
					if (itemId == sourceItemId)
						return;

				// On which side of the target the item was dropped
				final VerticalDropLocation location = target.getDropLocation();

				// Creating a new Command
				if (sourceItemId instanceof MetadataModule) {

					MetadataModule metadataModule = (MetadataModule) sourceItemId;

					final MenuCommandResourceComponent createMenuCommandResourceComponent = new MenuCommandResourceComponent(
							metadataModule);

					final Window createMenuCommandPopupWindow = new Window(
							"A�adir comando");
					createMenuCommandPopupWindow.center();
					createMenuCommandPopupWindow.setModal(true);
					createMenuCommandPopupWindow.setResizable(false);
					createMenuCommandPopupWindow.setWidth("300px");
					createMenuCommandPopupWindow.setHeight("345px");

					createMenuCommandPopupWindow
							.addComponent(createMenuCommandResourceComponent);

					getApplication().getMainWindow().addWindow(
							createMenuCommandPopupWindow);

					createMenuCommandResourceComponent.getSaveButton()
							.addListener(new ClickListener() {

								@Override
								public void buttonClick(ClickEvent event) {

									createMenuCommandResourceComponent
											.getMenuForm().commit();

									// Remove the item from the source container
									// and
									// add it to the tree's container
									Container sourceContainer = transferable
											.getSourceContainer();
									sourceContainer.removeItem(sourceItemId);

									updateMenuHierarchy(
											configuredModuleTreeTableContainer,
											createMenuCommandResourceComponent
													.getBeanItem().getBean());

									positionMenuResource(
											configuredModuleTreeTableContainer,
											location,
											(MenuResource) targetItemId,
											createMenuCommandResourceComponent
													.getBeanItem().getBean());

									configuredModuleTreeTable
											.select(createMenuCommandResourceComponent
													.getBeanItem().getBean());

									getApplication()
											.getMainWindow()
											.removeWindow(
													createMenuCommandPopupWindow);

								}
							});

				}

				// Organizing the current Menu Resources
				else {

					BeanItem<MenuResource> beanItem = null;

					if (sourceItemId instanceof BeanItem<?>) {

						beanItem = (BeanItem<MenuResource>) sourceItemId;
					} else if (sourceItemId instanceof MenuFolderResource)

						beanItem = new BeanItem<MenuResource>(
								(MenuResource) sourceItemId);

					else if (sourceItemId instanceof MenuCommandResource)

						beanItem = new BeanItem<MenuResource>(
								(MenuResource) sourceItemId);

					// Remove the item from the source container and
					// add it to the tree's container
					Container sourceContainer = transferable
							.getSourceContainer();
					sourceContainer.removeItem(sourceItemId);

					updateMenuHierarchy(configuredModuleTreeTableContainer,
							beanItem.getBean());

					positionMenuResource(configuredModuleTreeTableContainer,
							location, (MenuResource) targetItemId,
							beanItem.getBean());

				}

				// Notify to listenes
				for (MenuResourceListener listener : listeners) {
					listener.onMenuResourceDataChange();
				}

			}
		});

		configuredModuleTreeTable
				.addListener(new ItemClickEvent.ItemClickListener() {

					@Override
					public void itemClick(ItemClickEvent event) {

						if (!event.isDoubleClick())
							return;

						final MenuResource menuResource = (MenuResource) event
								.getItemId();

						if (menuResource instanceof MenuFolderResource) {

							final Window editMenuFolderPopupWindow = new Window(
									"Editar menu");
							editMenuFolderPopupWindow.center();
							editMenuFolderPopupWindow.setModal(true);
							editMenuFolderPopupWindow.setResizable(false);
							editMenuFolderPopupWindow.setWidth("250px");
							editMenuFolderPopupWindow.setHeight("245px");

							final MenuFolderResourceComponent menuFolderComponent = new MenuFolderResourceComponent(
									(MenuFolderResource) menuResource);
							editMenuFolderPopupWindow
									.addComponent(menuFolderComponent);

							getApplication().getMainWindow().addWindow(
									editMenuFolderPopupWindow);

							menuFolderComponent.getSaveButton().addListener(
									new ClickListener() {

										@Override
										public void buttonClick(ClickEvent event) {

											menuFolderComponent.getMenuForm()
													.commit();

											// Update Tree Table values
											MenuFolderResource editedMenuFolderComponent = menuFolderComponent
													.getBeanItem().getBean();

											configuredModuleTreeTableContainer
													.getItem(menuResource)
													.getItemProperty("caption")
													.setValue(
															editedMenuFolderComponent
																	.getCaption());

											configuredModuleTreeTableContainer
													.getItem(menuResource)
													.getItemProperty("shortCut")
													.setValue(
															editedMenuFolderComponent
																	.getShortCut());

											configuredModuleTreeTableContainer
													.getItem(menuResource)
													.getItemProperty("visible")
													.setValue(
															editedMenuFolderComponent
																	.isVisible());

											// Select the edited menu resource
											configuredModuleTreeTable
													.select(editedMenuFolderComponent);

											// Notify to listenes
											for (MenuResourceListener listener : listeners) {
												listener.onMenuResourceDataChange();
											}

											// Close the edition form
											getApplication()
													.getMainWindow()
													.removeWindow(
															editMenuFolderPopupWindow);

										}
									});

						} else if (menuResource instanceof MenuCommandResource) {

							final MenuCommandResourceComponent menuCommandResourceComponent = new MenuCommandResourceComponent(
									(MenuCommandResource) menuResource);

							final Window editMenuCommandPopupWindow = new Window(
									"Editar comando");
							editMenuCommandPopupWindow.center();
							editMenuCommandPopupWindow.setModal(true);
							editMenuCommandPopupWindow.setResizable(false);
							editMenuCommandPopupWindow.setWidth("300px");
							editMenuCommandPopupWindow.setHeight("345px");

							editMenuCommandPopupWindow
									.addComponent(menuCommandResourceComponent);

							getApplication().getMainWindow().addWindow(
									editMenuCommandPopupWindow);

							menuCommandResourceComponent.getSaveButton()
									.addListener(new ClickListener() {

										@Override
										public void buttonClick(ClickEvent event) {

											menuCommandResourceComponent
													.getMenuForm().commit();

											// Update Tree Table values
											MenuCommandResource editedMenuCommandResource = menuCommandResourceComponent
													.getBeanItem().getBean();

											configuredModuleTreeTableContainer
													.getItem(menuResource)
													.getItemProperty("caption")
													.setValue(
															editedMenuCommandResource
																	.getCaption());

											configuredModuleTreeTableContainer
													.getItem(menuResource)
													.getItemProperty("hint")
													.setValue(
															editedMenuCommandResource
																	.getHint());

											configuredModuleTreeTableContainer
													.getItem(menuResource)
													.getItemProperty("shortCut")
													.setValue(
															editedMenuCommandResource
																	.getShortCut());

											configuredModuleTreeTableContainer
													.getItem(menuResource)
													.getItemProperty("visible")
													.setValue(
															editedMenuCommandResource
																	.isVisible());

											configuredModuleTreeTableContainer
													.getItem(menuResource)
													.getItemProperty("type")
													.setValue(
															editedMenuCommandResource
																	.getType());

											configuredModuleTreeTableContainer
													.getItem(menuResource)
													.getItemProperty("location")
													.setValue(
															editedMenuCommandResource
																	.getLocation());

											configuredModuleTreeTableContainer
													.getItem(menuResource)
													.getItemProperty(
															"autostart")
													.setValue(
															editedMenuCommandResource
																	.isAutostart());

											// Select the edited menu resource
											configuredModuleTreeTable
													.select(menuCommandResourceComponent
															.getBeanItem()
															.getBean());

											// Notify to listenes
											for (MenuResourceListener listener : listeners) {
												listener.onMenuResourceDataChange();
											}

											// Close the edition form
											getApplication()
													.getMainWindow()
													.removeWindow(
															editMenuCommandPopupWindow);

										}
									});
						}

					}
				});

		// Styling boolean columns
		configuredModuleTreeTable.addGeneratedColumn(BooleanColumnGenerator.VISIBLE_COLUMN_ID, new BooleanColumnGenerator());
		configuredModuleTreeTable.addGeneratedColumn(BooleanColumnGenerator.AUTOSTART_COLUMN_ID, new BooleanColumnGenerator());
				
		// Apply tooltips to tree nodes has problems
		configuredModuleTreeTable
				.setItemDescriptionGenerator(new ItemDescriptionGenerator() {

					@Override
					public String generateDescription(Component source,
							Object itemId, Object propertyId) {

						if (itemId instanceof MenuCommandResource) {

							MenuCommandResource menuCommandResource = (MenuCommandResource) itemId;

							// Check if there is deployed the module associated
							if (moduleService.get(
									menuCommandResource.getModuleId(),
									menuCommandResource.getModuleVersion()) == null) {

								return "No existe en el servidor un m�dulo con id <b>"
										+ menuCommandResource.getModuleId()
										+ "</b> y versi�n <b>"
										+ menuCommandResource
												.getModuleVersion() + "</b>";
							}
						}
						return null;

					}
				});
	}

	public void addListener(MenuResourceListener listener) {
		listeners.add(listener);
	}

	public void loadData(MenuWorkbenchService menuWorkbenchService,
			IModuleService moduleService,
			MenuFolderResourceService menuFolderResourceService,
			MenuCommandResourceService menuCommandResourceService)
			throws Exception {

		this.moduleService = moduleService;
		this.menuWorkbenchService = menuWorkbenchService;
		this.menuFolderResourceService = menuFolderResourceService;
		this.menuCommandResourceService = menuCommandResourceService;

		configuredModuleTreeTableContainer = new HierarchicalContainer();
		configuredModuleTreeTableContainer.addContainerProperty("caption",
				String.class, null);
		configuredModuleTreeTableContainer.addContainerProperty("icon",
				Resource.class, null);
		configuredModuleTreeTableContainer.addContainerProperty("type",
				String.class, null);
		configuredModuleTreeTableContainer.addContainerProperty("location",
				String.class, null);
		configuredModuleTreeTableContainer.addContainerProperty("autostart",
				Boolean.class, null);
		configuredModuleTreeTableContainer.addContainerProperty("visible",
				Boolean.class, null);
		configuredModuleTreeTableContainer.addContainerProperty("shortCut",
				String.class, null);
		configuredModuleTreeTableContainer.addContainerProperty("hint",
				String.class, null);
		configuredModuleTreeTableContainer.addContainerProperty("moduleId",
				String.class, null);
		configuredModuleTreeTableContainer.addContainerProperty(
				"moduleVersion", String.class, null);

		for (MenuWorkbench menuWorkbench : menuWorkbenchService.getAll()) {

			List<MenuResource> rootResources = new ArrayList<MenuResource>(
					menuWorkbench.getMenuFolderResource());
			recursiveAddMenuResources(configuredModuleTreeTableContainer,
					rootResources, null);
		}

		configuredModuleTreeTable
				.setContainerDataSource(configuredModuleTreeTableContainer);

		configuredModuleTreeTable.setItemIconPropertyId("icon");
		configuredModuleTreeTable.setItemCaptionPropertyId("caption");

		configuredModuleTreeTable.setVisibleColumns(new Object[] { "caption",
				"hint", "type", "location", "shortCut", BooleanColumnGenerator.AUTOSTART_COLUMN_ID,
				"moduleId", "moduleVersion", BooleanColumnGenerator.VISIBLE_COLUMN_ID });
		configuredModuleTreeTable.setColumnHeaders(new String[] { "Nombre",
				"Descripción", "Tipo", "Ubicación", "Atajo", "Auto arranque",
				"Identificador de módulo", "Versión modulo", "Visible" });

		// Expand all nodes
		recursiveExpandMenuResources(configuredModuleTreeTable.getItemIds());

	}

	// PRIVATE METHODS

	private void updateMenuHierarchy(HierarchicalContainer container,
			MenuResource menuResource) {

		container.addItem(menuResource);

		// More hassle: copy the property values from the BeanItem to
		// the container item
		container.getContainerProperty(menuResource, "caption").setValue(
				menuResource.getCaption());
		container.getContainerProperty(menuResource, "visible").setValue(
				menuResource.isVisible());

		if (menuResource instanceof MenuFolderResource) {

			container.getContainerProperty(menuResource, "icon").setValue(
					new ThemeResource(ConfiguratorModule.MODULE_ICONS_PATH + "folder-horizontal-open.png"));

		} else if (menuResource instanceof MenuCommandResource) {

			MenuCommandResource command = (MenuCommandResource) menuResource;

			container.getContainerProperty(command, "location").setValue(
					command.getLocation());

			container.getContainerProperty(command, "type").setValue(
					command.getType());

			container.getContainerProperty(command, "autostart").setValue(
					command.isAutostart());

			container.getContainerProperty(command, "shortCut").setValue(
					command.getShortCut());

			container.getContainerProperty(command, "hint").setValue(
					command.getHint());

			container.getContainerProperty(command, "moduleId").setValue(
					command.getModuleId());

			container.getContainerProperty(command, "moduleVersion").setValue(
					command.getModuleVersion());

			if (moduleService.get(command.getModuleId(),
					command.getModuleVersion()) != null)
				container.getContainerProperty(command, "icon").setValue(
						new ThemeResource(ConfiguratorModule.MODULE_ICONS_PATH + "puzzle.png"));
			else
				container.getContainerProperty(command, "icon").setValue(
						new ThemeResource(ConfiguratorModule.MODULE_ICONS_PATH + "puzzle--exclamation.png"));

			configuredModuleTreeTable.setChildrenAllowed(command, false);
		}

	}

	private void positionMenuResource(HierarchicalContainer container,
			VerticalDropLocation location, MenuResource targetItemId,
			MenuResource beanItem) {

		// Drop right on an item -> make it a child
		if (location == VerticalDropLocation.MIDDLE) {
			configuredModuleTreeTable.setParent(beanItem, targetItemId);
			configuredModuleTreeTable.setCollapsed(targetItemId, false);
		}

		// Drop at the top of a subtree -> make it previous
		else if (location == VerticalDropLocation.TOP) {
			Object parentId = container.getParent(targetItemId);
			configuredModuleTreeTable.setParent(beanItem, parentId);
			container.moveAfterSibling(beanItem, targetItemId);
			container.moveAfterSibling(targetItemId, beanItem);
			configuredModuleTreeTable.setCollapsed(parentId, false);
		}

		// Drop below another item -> make it next
		else if (location == VerticalDropLocation.BOTTOM) {
			Object parentId = container.getParent(targetItemId);
			configuredModuleTreeTable.setParent(beanItem, parentId);
			container.moveAfterSibling(beanItem, targetItemId);
			configuredModuleTreeTable.setCollapsed(parentId, false);
		}
	}

	private void recursiveAddMenuResources(HierarchicalContainer container,
			List<MenuResource> resources, MenuFolderResource parent) {

		if (resources == null)
			return;

		for (MenuResource resource : resources) {

			container.addItem(resource);

			container.getContainerProperty(resource, "caption").setValue(
					resource.getCaption());
			container.getContainerProperty(resource, "visible").setValue(
					resource.isVisible());

			// set the parent group, if there is one
			if (parent != null) {
				container.setParent(resource, parent);
			}

			if (resource instanceof MenuFolderResource) {

				container.getContainerProperty(resource, "icon").setValue(
						new ThemeResource(ConfiguratorModule.MODULE_ICONS_PATH + "folder-horizontal-open.png"));

				MenuFolderResource menuResource = (MenuFolderResource) resource;
				recursiveAddMenuResources(container,
						menuResource.getMenuResources(), menuResource);
			} else if (resource instanceof MenuCommandResource) {

				MenuCommandResource command = (MenuCommandResource) resource;

				container.getContainerProperty(resource, "location").setValue(
						command.getLocation());

				container.getContainerProperty(resource, "type").setValue(
						command.getType());

				container.getContainerProperty(resource, "autostart").setValue(
						command.isAutostart());

				container.getContainerProperty(resource, "shortCut").setValue(
						command.getShortCut());

				container.getContainerProperty(resource, "hint").setValue(
						command.getHint());

				container.getContainerProperty(resource, "moduleId").setValue(
						command.getModuleId());

				container.getContainerProperty(resource, "moduleVersion")
						.setValue(command.getModuleVersion());

				if (moduleService.get(command.getModuleId(),
						command.getModuleVersion()) != null)
					container.getContainerProperty(resource, "icon").setValue(
							new ThemeResource(ConfiguratorModule.MODULE_ICONS_PATH + "puzzle.png"));
				else
					container.getContainerProperty(resource, "icon").setValue(
							new ThemeResource(ConfiguratorModule.MODULE_ICONS_PATH + "puzzle--exclamation.png"));

				container.setChildrenAllowed(resource, false);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void recursiveRemoveMenuResources(HierarchicalContainer container,
			Collection<MenuResource> resources, MenuFolderResource parent) {

		if (resources == null)
			return;

		for (MenuResource resource : resources) {

			container.removeItem(resource);
			removedMenuResources.add(resource);

			if (resource instanceof MenuCommandResource) {

				MenuCommandResource menuCommandResource = (MenuCommandResource) resource;
				container.removeItem(menuCommandResource);
				configuredModuleTreeTable.addItem(new MetadataModule(
						menuCommandResource.getCaption(), null, null));
			}

			else if (container.hasChildren(resource))
				recursiveRemoveMenuResources(container,
						(Collection<MenuResource>) container
								.getChildren(resource),
						(MenuFolderResource) container.getParent(resource));
		}

		container.removeItem(parent);
	}

	private void recursiveExpandMenuResources(Collection<?> collection) {

		for (Object item : collection) {
			configuredModuleTreeTable.setCollapsed(item, false);

			if (configuredModuleTreeTable.hasChildren(item)) {

				recursiveExpandMenuResources(configuredModuleTreeTable
						.getChildren(item));
			}
		}
	}

	// INNER CLASSES
	private class MenuCommandResourceComponent extends CustomComponent {

		private Form menuForm;
		private BeanItem<MenuCommandResource> beanItem;
		private Button saveButton;

		public MenuCommandResourceComponent(
				MenuCommandResource menuCommandResource) {

			buildMainLayout();

			beanItem = new BeanItem<MenuCommandResource>(menuCommandResource);
			menuForm.setItemDataSource(beanItem);

			setCompositionRoot(menuForm);
		}

		public MenuCommandResourceComponent(MetadataModule metadataModule) {

			MenuCommandResource menuCommandResource = new MenuCommandResource();
			// Loading the data
			menuCommandResource.setModuleId(metadataModule.getId());
			menuCommandResource.setModuleVersion(metadataModule.getVersion());

			menuCommandResource.setVisible(true);

			buildMainLayout();

			beanItem = new BeanItem<MenuCommandResource>(menuCommandResource);
			menuForm.setItemDataSource(beanItem);

			setCompositionRoot(menuForm);
		}

		private void buildMainLayout() {

			menuForm = new Form();
			menuForm.setSizeFull();
			menuForm.setFormFieldFactory(new FormFieldFactory() {

				@Override
				public Field createField(Item item, Object propertyId,
						Component uiContext) {

					if ("caption".equals(propertyId)) {

						TextField field = new TextField("Nombre");
						field.setNullRepresentation("");
						field.setRequired(true);
						field.setRequiredError("Campo requerido");
						field.setWidth("100%");
						return field;
					}
					if ("hint".equals(propertyId)) {

						TextField field = new TextField("Descripci�n");
						field.setNullRepresentation("");
						field.setWidth("100%");
						return field;
					}
					if ("type".equals(propertyId)) {

						final ComboBox combox = new ComboBox("Tipo");
						combox.setNullSelectionAllowed(false);
						combox.setSizeFull();
						combox.setImmediate(true);

						IndexedContainer types = new IndexedContainer();

						for (TYPE type : MenuCommandResource.TYPE.values()) {
							types.addItem(type);
						}

						combox.setContainerDataSource(types);
						combox.setValidationVisible(true);

						return combox;

					}
					if ("location".equals(propertyId)) {

						final ComboBox combox = new ComboBox("Ubicaci�n");
						combox.setNullSelectionAllowed(false);
						combox.setSizeFull();
						combox.setImmediate(true);

						IndexedContainer locations = new IndexedContainer();

						for (LOCATION location : MenuCommandResource.LOCATION
								.values()) {
							locations.addItem(location);
						}

						combox.setContainerDataSource(locations);
						combox.setValidationVisible(true);

						return combox;

					}
					if ("autostart".equals(propertyId)) {

						return new Switch("Inicializar");
					}
					if ("shortCut".equals(propertyId)) {

						TextField field = new TextField("Atajo");
						field.setNullRepresentation("");
						field.setWidth("100%");
						return field;
					}
					if ("visible".equals(propertyId)) {

						return new Switch("Visible");
					}

					return null;
				}
			});

			menuForm.setVisibleItemProperties(new Object[] { "caption", "hint",
					"type", "location", "autostart", "shortCut", "visible" });

			saveButton = new Button("Guardar");
			menuForm.getFooter().addComponent(saveButton);

			HorizontalLayout layout = (HorizontalLayout) menuForm.getFooter();
			layout.setSizeFull();
			layout.setComponentAlignment(saveButton, Alignment.MIDDLE_RIGHT);
		}

		public Form getMenuForm() {
			return menuForm;
		}

		public BeanItem<MenuCommandResource> getBeanItem() {
			return beanItem;
		}

		public Button getSaveButton() {
			return saveButton;
		}

	}

	// MenuFolderResource creation form
	private class MenuFolderResourceComponent extends CustomComponent {

		private Form menuForm;
		private BeanItem<MenuFolderResource> beanItem;
		private Button saveButton;

		public MenuFolderResourceComponent(MenuFolderResource menuFolderResource) {

			buildMainLayout();

			beanItem = new BeanItem<MenuFolderResource>(menuFolderResource);
			menuForm.setItemDataSource(beanItem);

			setCompositionRoot(menuForm);
		}

		private void buildMainLayout() {

			menuForm = new Form();
			menuForm.setSizeFull();
			menuForm.setFormFieldFactory(new FormFieldFactory() {

				@Override
				public Field createField(Item item, Object propertyId,
						Component uiContext) {

					if ("caption".equals(propertyId)) {

						TextField field = new TextField("Nombre");
						field.setNullRepresentation("Nueva carpeta");
						field.setWidth("100%");
						return field;
					}
					if ("hint".equals(propertyId)) {

						TextField field = new TextField("Atajo");
						field.setNullRepresentation("");
						field.setWidth("100%");
						return field;
					}
					if ("shortCut".equals(propertyId)) {

						TextField field = new TextField("Atajo");
						field.setNullRepresentation("");
						field.setWidth("100%");
						return field;
					}
					if ("visible".equals(propertyId)) {

						return new Switch("Visible");
					}

					return null;
				}
			});

			menuForm.setVisibleItemProperties(new Object[] { "caption",
					"shortCut", "visible" });

			saveButton = new Button("Guardar");
			menuForm.getFooter().addComponent(saveButton);

			HorizontalLayout layout = (HorizontalLayout) menuForm.getFooter();
			layout.setSizeFull();
			layout.setComponentAlignment(saveButton, Alignment.MIDDLE_RIGHT);

		}

		public BeanItem<MenuFolderResource> getBeanItem() {
			return beanItem;
		}

		public Form getMenuForm() {
			return menuForm;
		}

		public Button getSaveButton() {
			return saveButton;
		}
	}

	public class CreateMenuFolderResourceHandler implements ClickListener {

		@Override
		public void buttonClick(ClickEvent event) {

			final Window createMenuFolderPopupWindow = new Window("A�adir menu");
			createMenuFolderPopupWindow.center();
			createMenuFolderPopupWindow.setModal(true);
			createMenuFolderPopupWindow.setResizable(false);
			createMenuFolderPopupWindow.setWidth("250px");
			createMenuFolderPopupWindow.setHeight("245px");

			getApplication().getMainWindow().addWindow(
					createMenuFolderPopupWindow);

			MenuFolderResource menu = new MenuFolderResource();
			menu.setVisible(true);
			menu.setMenuResources(new ArrayList<MenuResource>());

			final MenuFolderResourceComponent menuFolderComponent = new MenuFolderResourceComponent(
					menu);

			createMenuFolderPopupWindow.addComponent(menuFolderComponent);

			menuFolderComponent.getSaveButton().addListener(
					new ClickListener() {

						@Override
						public void buttonClick(ClickEvent event) {

							menuFolderComponent.getMenuForm().commit();

							MenuFolderResource editedMenuFolderResource = menuFolderComponent
									.getBeanItem().getBean();

							// Selected menu resource
							MenuResource selectedMenuResource = (MenuResource) configuredModuleTreeTable
									.getValue();

							// Add to the menu resources container
							configuredModuleTreeTableContainer
									.addItem(editedMenuFolderResource);

							configuredModuleTreeTableContainer
									.getContainerProperty(
											editedMenuFolderResource, "caption")
									.setValue(
											editedMenuFolderResource
													.getCaption());
							configuredModuleTreeTableContainer
									.getContainerProperty(
											editedMenuFolderResource, "visible")
									.setValue(
											editedMenuFolderResource
													.isVisible());
							configuredModuleTreeTableContainer
									.getContainerProperty(
											editedMenuFolderResource, "icon")
									.setValue(new ThemeResource(ConfiguratorModule.MODULE_ICONS_PATH + "folder-horizontal-open.png"));

							if (selectedMenuResource instanceof MenuFolderResource) {

								configuredModuleTreeTableContainer.setParent(
										editedMenuFolderResource,
										selectedMenuResource);

							} else if (selectedMenuResource instanceof MenuCommandResource) {

								MenuFolderResource parent = (MenuFolderResource) configuredModuleTreeTableContainer
										.getParent(selectedMenuResource);
								configuredModuleTreeTableContainer.setParent(
										editedMenuFolderResource, parent);
							}

							// Select the new folder
							configuredModuleTreeTable
									.select(editedMenuFolderResource);

							// Notify to listenes
							for (MenuResourceListener listener : listeners) {
								listener.onMenuResourceDataChange();
							}

							getApplication().getMainWindow().removeWindow(
									createMenuFolderPopupWindow);

						}
					});

		}

	}

	public class RemoveMenuResourceHandler implements ClickListener {

		private BeanItemContainer<MetadataModule> sourceContainer;

		public RemoveMenuResourceHandler(
				BeanItemContainer<MetadataModule> sourceContainer) {

			this.sourceContainer = sourceContainer;
		}

		@Override
		public void buttonClick(ClickEvent event) {

			MenuResource menuResource = (MenuResource) configuredModuleTreeTable
					.getValue();

			if (menuResource instanceof MenuFolderResource) {

				MenuFolderResource menuFolderResource = (MenuFolderResource) menuResource;
				recursiveRemoveMenuResources(
						configuredModuleTreeTableContainer,
						menuFolderResource.getMenuResources(),
						menuFolderResource);

				configuredModuleTreeTableContainer
						.removeItem(menuFolderResource);

				removedMenuResources.add(menuFolderResource);

				// Notify to listenes
				for (MenuResourceListener listener : listeners) {
					listener.onMenuResourceDataChange();
				}

			} else if (menuResource instanceof MenuCommandResource) {

				MenuCommandResource menuCommandResource = (MenuCommandResource) menuResource;

				configuredModuleTreeTableContainer
						.removeItem(menuCommandResource);

				removedMenuResources.add(menuCommandResource);

				MetadataModule metadataModule = moduleService.get(
						menuCommandResource.getModuleId(),
						menuCommandResource.getModuleVersion());

				if (metadataModule != null)
					sourceContainer.addItem(metadataModule);

				// Notify to listenes
				for (MenuResourceListener listener : listeners) {
					listener.onMenuResourceDataChange();
				}
			}
		}

	}

	public class ApplyMenuResourceChangesHandler implements ClickListener {

		@SuppressWarnings("unchecked")
		@Override
		public void buttonClick(ClickEvent event) {

			final Window applyingChangesIndicatorPopupWindow = new Window(
					"Aplicando cambios....");
			applyingChangesIndicatorPopupWindow.center();
			applyingChangesIndicatorPopupWindow.setModal(true);
			applyingChangesIndicatorPopupWindow.setResizable(false);
			applyingChangesIndicatorPopupWindow.setClosable(false);
			applyingChangesIndicatorPopupWindow.setWidth("250px");
			applyingChangesIndicatorPopupWindow.setHeight("245px");

			applyingChangesIndicatorPopupWindow
					.addComponent(new Label(
							"No cierre la ventana principal mientras se aplican los cambios"));

			final ProgressIndicator indicator = new ProgressIndicator(
					new Float(0.0));
			indicator.setIndeterminate(true);
			indicator.setPollingInterval(500);
			indicator.setSizeFull();
			applyingChangesIndicatorPopupWindow.addComponent(indicator);

			getApplication().getMainWindow().addWindow(
					applyingChangesIndicatorPopupWindow);

			new Thread() {
				@Override
				public void run() {

					List<MenuResource> dirtyMenuResources = new ArrayList<MenuResource>(
							(Collection<? extends MenuResource>) configuredModuleTreeTableContainer
									.getItemIds());

					Map<MenuResource, MenuResource> updatedMenuResources = new HashMap<MenuResource, MenuResource>(
							dirtyMenuResources.size());

					// Persist the menu resource's property changes
					for (MenuResource menuResource : dirtyMenuResources) {

						if (menuResource instanceof MenuFolderResource)
							try {
								updatedMenuResources
										.put(menuResource,
												menuFolderResourceService
														.save((MenuFolderResource) menuResource));

							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						else if (menuResource instanceof MenuCommandResource)
							try {
								updatedMenuResources
										.put(menuResource,
												menuCommandResourceService
														.save((MenuCommandResource) menuResource));

							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

					}

					// Persist the hierarchy relationship
					for (MenuResource menuResource : updatedMenuResources
							.keySet()) {

						if (configuredModuleTreeTableContainer
								.hasChildren(menuResource)) {

							List<MenuResource> children = new ArrayList<MenuResource>(
									(Collection<? extends MenuResource>) configuredModuleTreeTableContainer
											.getChildren(menuResource));

							MenuFolderResource updatedMenuFolderResource = (MenuFolderResource) updatedMenuResources
									.get(menuResource);
							updatedMenuFolderResource.getMenuResources()
									.clear();

							// add the children
							for (MenuResource child : children)
								updatedMenuFolderResource.getMenuResources()
										.add(updatedMenuResources.get(child));

							try {
								menuFolderResourceService
										.save(updatedMenuFolderResource);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

					}

					// Remove the removed MenuResource
					for (MenuResource removedmenuResource : removedMenuResources) {

						if (removedmenuResource instanceof MenuFolderResource) {
							try {
								menuFolderResourceService
										.delete((MenuFolderResource) removedmenuResource);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (removedmenuResource instanceof MenuCommandResource) {
							try {
								menuCommandResourceService
										.delete((MenuCommandResource) removedmenuResource);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}

					// Stop the indicator
					getApplication().getMainWindow().removeWindow(
							applyingChangesIndicatorPopupWindow);
				}

			}.start();

			// Notify changes that all changes has been applied
			for (MenuResourceListener listener : listeners)
				listener.onMenuResourceChangesApplied();

		}

	}

	// PUBLIC INTERFACE
	public interface MenuResourceListener {

		public void onMenuResourceDataChange();

		public void onMenuResourceChangesApplied();
	}

	private class BooleanColumnGenerator implements Table.ColumnGenerator {

		static final String VISIBLE_COLUMN_ID = "visible_column_id";
		static final String AUTOSTART_COLUMN_ID = "autostart_column_id";

		@Override
		public Object generateCell(Table source, Object itemId, Object columnId) {

			if (VISIBLE_COLUMN_ID.equals(columnId)&& itemId instanceof MenuResource) {
				MenuResource menuResource = (MenuResource) itemId;

				Label label = menuResource.isVisible() ? new Label("Si") : new Label("No");

				return label;

			}

			if (AUTOSTART_COLUMN_ID.equals(columnId)&& itemId instanceof MenuCommandResource) {
				MenuCommandResource menuCommandResource = (MenuCommandResource) itemId;

				Label label = menuCommandResource.isAutostart() ? new Label("Si") : new Label("No");

				return label;

			}
			return null;

		}

	}

	@Override
	protected void updateLabels() {
		configuredModuleTreeTable.setColumnHeaders(new String[] { getI18N().getMessage("com.thingtrack.konekti.view.module.modulemanager.internal.ConfiguratedModulesComponent.configuredModuleTreeTable.column.caption"), 
				  getI18N().getMessage("com.thingtrack.konekti.view.module.modulemanager.internal.ConfiguratedModulesComponent.configuredModuleTreeTable.column.organization.hint"), 
				  getI18N().getMessage("com.thingtrack.konekti.view.module.modulemanager.internal.ConfiguratedModulesComponent.configuredModuleTreeTable.column.type"), 
				  getI18N().getMessage("com.thingtrack.konekti.view.module.modulemanager.internal.ConfiguratedModulesComponent.configuredModuleTreeTable.column.location"),
				  getI18N().getMessage("com.thingtrack.konekti.view.module.modulemanager.internal.ConfiguratedModulesComponent.configuredModuleTreeTable.column.shortCut"),
				  getI18N().getMessage("com.thingtrack.konekti.view.module.modulemanager.internal.ConfiguratedModulesComponent.configuredModuleTreeTable.column.autostart"),
				  getI18N().getMessage("com.thingtrack.konekti.view.module.modulemanager.internal.ConfiguratedModulesComponent.configuredModuleTreeTable.column.moduleId"),
				  getI18N().getMessage("com.thingtrack.konekti.view.module.modulemanager.internal.ConfiguratedModulesComponent.configuredModuleTreeTable.column.moduleVersion"),
				  getI18N().getMessage("com.thingtrack.konekti.view.module.modulemanager.internal.ConfiguratedModulesComponent.configuredModuleTreeTable.column.visible")});
		
	}
}
