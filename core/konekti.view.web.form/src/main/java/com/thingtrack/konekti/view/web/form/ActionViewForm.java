package com.thingtrack.konekti.view.web.form;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class ActionViewForm extends CustomComponent {
	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private TextField descriptionField;
	@AutoGenerated
	private TextField codeField;
	@AutoGenerated
	private TextField commentField;
	@AutoGenerated
	private CheckBox activeField;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public ActionViewForm() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		initComponents();
	}
	
	private void initComponents() {
		codeField.setNullRepresentation("");
		descriptionField.setNullRepresentation("");
		commentField.setNullRepresentation("");
	}
	
	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("580px");
		mainLayout.setHeight("280px");
		mainLayout.setMargin(true);
		
		// top-level component properties
		setWidth("580px");
		setHeight("280px");
		
		// activeField
		activeField = new CheckBox();
		activeField.setCaption("Activo");
		activeField.setImmediate(false);
		activeField.setWidth("-1px");
		activeField.setHeight("-1px");
		activeField.setRequired(true);
		mainLayout.addComponent(activeField, "top:14.0px;left:507.0px;");
		
		// commentField
		commentField = new TextField();
		commentField.setCaption("Comentario");
		commentField.setImmediate(false);
		commentField.setWidth("540px");
		commentField.setHeight("160px");
		mainLayout.addComponent(commentField, "top:100.0px;left:20.0px;");
		
		// codeField
		codeField = new TextField();
		codeField.setCaption("Código");
		codeField.setImmediate(false);
		codeField.setWidth("160px");
		codeField.setHeight("-1px");
		codeField.setRequired(true);
		mainLayout.addComponent(codeField, "top:17.0px;left:20.0px;");
		
		// descriptionField
		descriptionField = new TextField();
		descriptionField.setCaption("Descripción");
		descriptionField.setImmediate(false);
		descriptionField.setWidth("541px");
		descriptionField.setHeight("-1px");
		descriptionField.setRequired(true);
		mainLayout.addComponent(descriptionField, "top:56.0px;left:20.0px;");
		
		return mainLayout;
	}
}
