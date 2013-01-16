package com.thingtrack.konekti.view.web.form;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class PermissionViewForm extends CustomComponent {

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private TabSheet actionField;
	@AutoGenerated
	private TextField descripctionField;
	@AutoGenerated
	private Button btnRemove;
	@AutoGenerated
	private Button brnAdd;
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
	public PermissionViewForm() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}
	
	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("580px");
		mainLayout.setHeight("440px");
		mainLayout.setMargin(true);
		
		// top-level component properties
		setWidth("580px");
		setHeight("440px");
		
		// activeField
		activeField = new CheckBox();
		activeField.setCaption("Activo");
		activeField.setImmediate(false);
		activeField.setWidth("-1px");
		activeField.setHeight("-1px");
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
		mainLayout.addComponent(codeField, "top:17.0px;left:20.0px;");
		
		// brnAdd
		brnAdd = new Button();
		brnAdd.setCaption("+");
		brnAdd.setImmediate(true);
		brnAdd.setWidth("-1px");
		brnAdd.setHeight("-1px");
		mainLayout.addComponent(brnAdd, "top:283.0px;left:525.0px;");
		
		// btnRemove
		btnRemove = new Button();
		btnRemove.setCaption("-");
		btnRemove.setImmediate(true);
		btnRemove.setWidth("-1px");
		btnRemove.setHeight("-1px");
		mainLayout.addComponent(btnRemove, "top:320.0px;left:525.0px;");
		
		// descripctionField
		descripctionField = new TextField();
		descripctionField.setCaption("Descripción");
		descripctionField.setImmediate(false);
		descripctionField.setWidth("541px");
		descripctionField.setHeight("-1px");
		mainLayout.addComponent(descripctionField, "top:56.0px;left:20.0px;");
		
		// actionField
		actionField = new TabSheet();
		actionField.setCaption("Roles/Acciones");
		actionField.setImmediate(false);
		actionField.setWidth("487px");
		actionField.setHeight("137px");
		mainLayout.addComponent(actionField, "top:283.0px;left:20.0px;");
		
		return mainLayout;
	}
}