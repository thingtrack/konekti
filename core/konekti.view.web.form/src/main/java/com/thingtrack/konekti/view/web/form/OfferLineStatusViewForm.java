package com.thingtrack.konekti.view.web.form;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TextField;


@SuppressWarnings("serial")
public class OfferLineStatusViewForm extends CustomComponent {

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private TextField descriptionField;
	@AutoGenerated
	private TextField codeField;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public OfferLineStatusViewForm() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		initComponents();
	}
	
	private void initComponents() {
		codeField.setNullRepresentation("");
		descriptionField.setNullRepresentation("");
		
		codeField.setRequiredError(codeField.getCaption() + " es un campo requerido");
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
		
		// codeField
		codeField = new TextField();
		codeField.setCaption("Código");
		codeField.setImmediate(false);
		codeField.setWidth("140px");
		codeField.setHeight("-1px");
		codeField.setTabIndex(1);
		codeField.setRequired(true);
		mainLayout.addComponent(codeField, "top:16.0px;left:20.0px;");
		
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