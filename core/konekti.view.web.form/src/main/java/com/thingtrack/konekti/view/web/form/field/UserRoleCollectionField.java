package com.thingtrack.konekti.view.web.form.field;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.vaadin.addon.customfield.CustomField;

import com.thingtrack.konekti.domain.Role;
import com.thingtrack.konekti.service.api.RoleService;
import com.thingtrack.konekti.view.addon.data.BindingSource;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class UserRoleCollectionField extends CustomField {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Table rolesTable;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private BindingSource<Role> rolesBindingSource;
	private List<Role> assignedRoles;

	private RoleService roleService;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public UserRoleCollectionField() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here

		// Retrieve enterprise services
		getServices();

		rolesBindingSource = new BindingSource<Role>(Role.class);

		rolesTable.setContainerDataSource(rolesBindingSource);
		rolesTable.addGeneratedColumn(UserAssignmentColumn.USER_COLUMN_ID, new UserAssignmentColumn());
		rolesTable.setVisibleColumns(new String[] { "code", "description", UserAssignmentColumn.USER_COLUMN_ID });
		rolesTable.setColumnHeaders(new String[] { "Código", "Descripción", "Asignado" } );
	}

	@Override
	public void setPropertyDataSource(Property newDataSource) {
		if (newDataSource.getValue() instanceof List) {
			@SuppressWarnings("unchecked")
			List<Role> roles = (List<Role>) newDataSource.getValue();

			// Already roles assigned to parent user
			assignedRoles = roles;
		}
		else 
			assignedRoles = new ArrayList<Role>();

		try {
			// Clean first the biding source
			rolesBindingSource.removeAllItems();
			
			// Add all available roles
			rolesBindingSource.addAll(roleService.getAll());
		} catch (Exception e) {
			e.printStackTrace();
		}

		super.setPropertyDataSource(newDataSource);
	}

	@Override
	public Class<?> getType() {
		if (getPropertyDataSource() instanceof Property)
			return getPropertyDataSource().getType();

		return List.class;
	}

	@Override
	public Object getValue() {
		return assignedRoles;

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

		// Organizationstable
		rolesTable = new Table();
		rolesTable.setImmediate(false);
		rolesTable.setWidth("100.0%");
		rolesTable.setHeight("100.0%");
		mainLayout.addComponent(rolesTable);
		mainLayout.setExpandRatio(rolesTable, 1.0f);

		return mainLayout;
	}

	@SuppressWarnings("unchecked")
	public void getServices() {

		BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass()).getBundleContext();

		@SuppressWarnings("rawtypes")
		ServiceReference roleServiceReference = bundleContext.getServiceReference(RoleService.class.getName());
		roleService = RoleService.class.cast(bundleContext.getService(roleServiceReference));
	}

	private class UserAssignmentColumn implements Table.ColumnGenerator {
		static final String USER_COLUMN_ID = "user-assignment";

		@Override
		public Object generateCell(Table source, Object itemId, Object columnId) {

			CheckBox assignmentColumn = new CheckBox();

			final Role roleItem = (Role) itemId;

			assignmentColumn.setValue(assignedRoles.contains(roleItem));

			assignmentColumn.addListener(new ValueChangeListener() {

				@Override
				public void valueChange(Property.ValueChangeEvent event) {

					boolean assignmentValue = (Boolean) event.getProperty().getValue();

					if (assignmentValue)
						assignedRoles.add(roleItem);

					else
						assignedRoles.remove(roleItem);

				}

			});

			return assignmentColumn;
		}

	}

}
