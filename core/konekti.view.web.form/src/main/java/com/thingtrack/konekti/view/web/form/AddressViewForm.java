package com.thingtrack.konekti.view.web.form;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class AddressViewForm extends CustomComponent {

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private TextField zipCodeField;
	@AutoGenerated
	private TextField webField;
	@AutoGenerated
	private TextField streetField;
	@AutoGenerated
	private TextField provinceField;
	@AutoGenerated
	private TextField phoneField;
	@AutoGenerated
	private TextField numberField;
	@AutoGenerated
	private TextField mobileField;
	@AutoGenerated
	private TextField letterField;
	@AutoGenerated
	private TextField faxField;
	@AutoGenerated
	private TextField emailField;
	@AutoGenerated
	private TextField countryField;
	@AutoGenerated
	private TextField cityField;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public AddressViewForm() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		initComponents();
	}
	
	private void initComponents() {
		zipCodeField.setNullRepresentation("");
		webField.setNullRepresentation("");
		streetField.setNullRepresentation("");
		provinceField.setNullRepresentation("");
		phoneField.setNullRepresentation("");
		numberField.setNullRepresentation("");
		mobileField.setNullRepresentation("");
		letterField.setNullRepresentation("");
		faxField.setNullRepresentation("");
		mobileField.setNullRepresentation("");
		faxField.setNullRepresentation("");
		emailField.setNullRepresentation("");
		countryField.setNullRepresentation("");
		cityField.setNullRepresentation("");
	
		emailField.addValidator(new EmailValidator(emailField.getCaption() + " no es un correo electrónico válido"));
		streetField.setRequiredError(streetField.getCaption() + " es un campo requerido");
	}
	
	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("720px");
		mainLayout.setHeight("300px");
		mainLayout.setMargin(true);
		
		// top-level component properties
		setWidth("720px");
		setHeight("300px");
		
		// cityField
		cityField = new TextField();
		cityField.setCaption("Ciudad");
		cityField.setImmediate(false);
		cityField.setWidth("440px");
		cityField.setHeight("-1px");
		cityField.setTabIndex(4);
		mainLayout.addComponent(cityField, "top:60.0px;left:20.0px;");
		
		// countryField
		countryField = new TextField();
		countryField.setCaption("País");
		countryField.setImmediate(false);
		countryField.setWidth("-1px");
		countryField.setHeight("-1px");
		countryField.setTabIndex(6);
		mainLayout.addComponent(countryField, "top:100.0px;left:20.0px;");
		
		// emailField
		emailField = new TextField();
		emailField.setCaption("Dirección email");
		emailField.setImmediate(false);
		emailField.setWidth("440px");
		emailField.setHeight("-1px");
		emailField.setTabIndex(8);
		mainLayout.addComponent(emailField, "top:140.0px;left:20.0px;");
		
		// faxField
		faxField = new TextField();
		faxField.setCaption("Número fax");
		faxField.setImmediate(false);
		faxField.setWidth("-1px");
		faxField.setHeight("-1px");
		faxField.setTabIndex(10);
		mainLayout.addComponent(faxField, "top:180.0px;left:20.0px;");
		
		// letterField
		letterField = new TextField();
		letterField.setCaption("Letra");
		letterField.setImmediate(false);
		letterField.setWidth("48px");
		letterField.setHeight("-1px");
		letterField.setTabIndex(3);
		mainLayout.addComponent(letterField, "top:20.0px;left:652.0px;");
		
		// mobileField
		mobileField = new TextField();
		mobileField.setCaption("Teléfono móbil");
		mobileField.setImmediate(false);
		mobileField.setWidth("-1px");
		mobileField.setHeight("-1px");
		mobileField.setTabIndex(12);
		mainLayout.addComponent(mobileField, "top:256.0px;left:20.0px;");
		
		// numberField
		numberField = new TextField();
		numberField.setCaption("Número");
		numberField.setImmediate(false);
		numberField.setWidth("85px");
		numberField.setHeight("-1px");
		numberField.setTabIndex(2);
		mainLayout.addComponent(numberField, "top:20.0px;left:555.0px;");
		
		// phoneField
		phoneField = new TextField();
		phoneField.setCaption("Teléfono fijo");
		phoneField.setImmediate(false);
		phoneField.setWidth("-1px");
		phoneField.setHeight("-1px");
		phoneField.setTabIndex(11);
		mainLayout.addComponent(phoneField, "top:216.0px;left:20.0px;");
		
		// provinceField
		provinceField = new TextField();
		provinceField.setCaption("Provincia");
		provinceField.setImmediate(false);
		provinceField.setWidth("220px");
		provinceField.setHeight("-1px");
		provinceField.setTabIndex(5);
		mainLayout.addComponent(provinceField, "top:60.0px;left:480.0px;");
		
		// streetField
		streetField = new TextField();
		streetField.setCaption("Calle");
		streetField.setImmediate(false);
		streetField.setWidth("520px");
		streetField.setHeight("-1px");
		streetField.setTabIndex(1);
		streetField.setRequired(true);
		mainLayout.addComponent(streetField, "top:20.0px;left:20.0px;");
		
		// webField
		webField = new TextField();
		webField.setCaption("Web");
		webField.setImmediate(false);
		webField.setWidth("220px");
		webField.setHeight("-1px");
		webField.setTabIndex(9);
		mainLayout.addComponent(webField, "top:140.0px;left:481.0px;");
		
		// zipCodeField
		zipCodeField = new TextField();
		zipCodeField.setCaption("Código Postal");
		zipCodeField.setImmediate(false);
		zipCodeField.setWidth("-1px");
		zipCodeField.setHeight("-1px");
		zipCodeField.setTabIndex(7);
		mainLayout.addComponent(zipCodeField, "top:100.0px;left:212.0px;");
		
		return mainLayout;
	}
}