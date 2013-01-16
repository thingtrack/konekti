package com.thingtrack.konekti.view.web.form;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TextField;


@SuppressWarnings("serial")
public class SupplierGroupViewForm extends CustomComponent {

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private TextField descriptionField;
	@AutoGenerated
	private TextField nameField;


	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public SupplierGroupViewForm() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		initComponents();
		
	}
		
	private void initComponents() {
		nameField.setNullRepresentation("");
		descriptionField.setNullRepresentation("");
		
		nameField.setRequiredError(nameField.getCaption() + " es un campo requerido");
	}
	
	
	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("560px");
		mainLayout.setHeight("100px");
		mainLayout.setMargin(true);
		
		// top-level component properties
		setWidth("560px");
		setHeight("100px");
		
		// nameField
		nameField = new TextField();
		nameField.setCaption("Nombre");
		nameField.setImmediate(false);
		nameField.setWidth("140px");
		nameField.setHeight("-1px");
		nameField.setTabIndex(1);
		nameField.setRequired(true);
		mainLayout.addComponent(nameField, "top:16.0px;left:20.0px;");
		
		// descriptionField
		descriptionField = new TextField();
		descriptionField.setCaption("Descripción");
		descriptionField.setImmediate(false);
		descriptionField.setWidth("520px");
		descriptionField.setHeight("-1px");
		mainLayout.addComponent(descriptionField, "top:56.0px;left:20.0px;");
		
		return mainLayout;
	}
}