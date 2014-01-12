package com.thingtrack.konekti.view.web.form;

import com.thingtrack.konekti.view.addon.ui.AbstractViewForm;
import com.thingtrack.konekti.view.web.form.field.FileField;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class ReportViewForm extends AbstractViewForm {

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private FileField templateField;
	@AutoGenerated
	private TextField descriptionField;
	@AutoGenerated
	private TextField codeField;
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
	public ReportViewForm() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		descriptionField.setNullRepresentation("");
	}
	
	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("460px");
		mainLayout.setHeight("140px");
		mainLayout.setMargin(true);
		
		// top-level component properties
		setWidth("460px");
		setHeight("140px");
		
		// activeField
		activeField = new CheckBox();
		activeField.setCaption("Active");
		activeField.setImmediate(false);
		activeField.setWidth("-1px");
		activeField.setHeight("-1px");
		mainLayout.addComponent(activeField, "top:20.0px;left:388.0px;");
		
		// codeField
		codeField = new TextField();
		codeField.setCaption("Code");
		codeField.setImmediate(false);
		codeField.setWidth("114px");
		codeField.setHeight("-1px");
		mainLayout.addComponent(codeField, "top:20.0px;left:20.0px;");
		
		// descriptionField
		descriptionField = new TextField();
		descriptionField.setCaption("Description");
		descriptionField.setImmediate(false);
		descriptionField.setWidth("420px");
		descriptionField.setHeight("-1px");
		mainLayout.addComponent(descriptionField, "top:60.0px;left:20.0px;");
		
		// fileNameField
		templateField = new FileField();
		templateField.setImmediate(false);
		templateField.setWidth("420px");
		templateField.setHeight("-1px");
		mainLayout.addComponent(templateField, "top:100.0px;left:20.0px;");
		
		return mainLayout;
	}

	@Override
	protected void updateLabels() {
		templateField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ReportViewForm.templateField.caption"));
		descriptionField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ReportViewForm.descriptionField.caption"));
		codeField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ReportViewForm.codeField.caption"));
		activeField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ReportViewForm.activeField.caption"));
		
	}
}

