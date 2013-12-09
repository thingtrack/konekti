package com.thingtrack.konekti.view.web.form.field;

import com.thingtrack.konekti.domain.Agent;
import com.thingtrack.konekti.domain.Client;
import com.thingtrack.konekti.domain.EmployeeAgent;
import com.thingtrack.konekti.domain.Supplier;
import com.thingtrack.konekti.domain.User;
import com.thingtrack.konekti.view.addon.ui.AbstractField;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class UserField extends AbstractField {

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private CheckBox activeField;
	@AutoGenerated
	private LocaleField defaultLocaleField;
	@AutoGenerated
	private PasswordField passwordConfirmField;
	@AutoGenerated
	private Button btnCancelUser;
	@AutoGenerated
	private Button btnRemoveUser;
	@AutoGenerated
	private Button btnApplyUser;
	@AutoGenerated
	private PasswordField passwordField;
	@AutoGenerated
	private TextField usernameField;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	
	private static final int TAB_USER_ORGANIZATION = 3;
	private static final int TAB_USER_APPLICATION = 4;
	private static final int TAB_USER_ROLE = 5;	
	
	private TabSheet tabSheet;
	
	private Agent agent;
	private User userUndo;
	private User user;

	private final static String DEFAULT_LOCALE = "es" + LocaleField.LOCALE_SEPARATOR + "ES";
	
	private UserOrganizationCollectionField userOrganizationCollectionField;
	private UserApplicationCollectionField userApplicationCollectionField;
	private UserRoleCollectionField userRoleCollectionField;
	
	public UserField() {		
		buildMainLayout();
		setCompositionRoot(mainLayout);

		btnApplyUser.setEnabled(false);
		btnCancelUser.setEnabled(false);		
		passwordConfirmField.setEnabled(false);
		activeField.setValue(true);
		
		usernameField.addListener(new TextChangeListener() {			
			@Override
			public void textChange(TextChangeEvent event) {				
				btnApplyUser.setEnabled(true);
				btnCancelUser.setEnabled(true);		
			}
		});
		
		passwordField.addListener(new TextChangeListener() {			
			@Override
			public void textChange(TextChangeEvent event) {				
				btnApplyUser.setEnabled(true);
				btnCancelUser.setEnabled(true);
				passwordConfirmField.setEnabled(true);
			}
		});
		
		activeField.addListener(new ValueChangeListener() {
			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				if (user != null)
					user.setActive(Boolean.parseBoolean(activeField.getValue().toString()));
				
			}
		});
		
		btnApplyUser.addListener(new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				if (!passwordField.getValue().equals(passwordConfirmField.getValue())) {
					getWindow().showNotification("¡Las claves introducidas son diferentes!");
					return;
				}
				
				if (user == null) {
					user = new User();
					user.setDefaultLocale(DEFAULT_LOCALE);
					
					if (agent instanceof EmployeeAgent)
						user.setEmployeeAgent((EmployeeAgent)agent);
					else if (agent instanceof Client)
						user.setClient((Client)agent);
					else if (agent instanceof Supplier)
						user.setSupplier((Supplier)agent);
				}
				
				user.setUsername(usernameField.getValue().toString());
				user.setPassword(passwordField.getValue().toString());
				user.setActive(Boolean.parseBoolean(activeField.getValue().toString()));
				
				btnApplyUser.setEnabled(false);
				btnCancelUser.setEnabled(false);
				passwordConfirmField.setEnabled(false);
												
				userOrganizationCollectionField.setValue(user);
				userApplicationCollectionField.setValue(user);
				userRoleCollectionField.setValue(user);
				
				tabSheet.getTab(TAB_USER_ORGANIZATION).setVisible(true);
				tabSheet.getTab(TAB_USER_APPLICATION).setVisible(true);
				tabSheet.getTab(TAB_USER_ROLE).setVisible(true);
			}
		});
	
		btnCancelUser.addListener(new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				if (user != null) {
					user.setUsername(userUndo.getUsername());
					user.setPassword(userUndo.getPassword());
					
					usernameField.setValue(userUndo.getUsername());
					passwordField.setValue(userUndo.getUsername());
				}
				else
					usernameField.setValue("");								
				
				passwordConfirmField.setValue("");				
				passwordConfirmField.setValue("");
				
				btnApplyUser.setEnabled(false);
				btnCancelUser.setEnabled(false);
				passwordConfirmField.setEnabled(false);
			}
		});
		
		btnRemoveUser.addListener(new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				user = null;
				
				usernameField.setValue("");
				passwordField.setValue("");
				passwordConfirmField.setValue("");	
				activeField.setValue(false);
				
				btnApplyUser.setEnabled(false);
				btnCancelUser.setEnabled(false);
				
				userOrganizationCollectionField.setValue(null);
				userApplicationCollectionField.setValue(null);
				
				tabSheet.getTab(TAB_USER_ORGANIZATION).setVisible(false);
				tabSheet.getTab(TAB_USER_APPLICATION).setVisible(false);
				tabSheet.getTab(TAB_USER_ROLE).setVisible(false);
				
			}
		});
	}

	public void setTabSheet(TabSheet tabSheet) {
		this.tabSheet = tabSheet;
		
		// get userOrganizationCollectionField from Tab component
		Tab userOrganization = tabSheet.getTab(TAB_USER_ORGANIZATION);
		VerticalLayout userOrganizationLayout = (VerticalLayout)userOrganization.getComponent();
		userOrganizationCollectionField = (UserOrganizationCollectionField) userOrganizationLayout.getComponent(0);

		Tab userApplication = tabSheet.getTab(TAB_USER_APPLICATION);
		VerticalLayout userApplicationLayout = (VerticalLayout)userApplication.getComponent();
		userApplicationCollectionField = (UserApplicationCollectionField) userApplicationLayout.getComponent(0);
		
		Tab userRole = tabSheet.getTab(TAB_USER_ROLE);
		VerticalLayout userRoleLayout = (VerticalLayout)userRole.getComponent();
		userRoleCollectionField = (UserRoleCollectionField) userRoleLayout.getComponent(0);	
	}
	
	public void setAgent(Agent agent) {
		this.agent = agent;
		
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setPropertyDataSource(Property newDataSource) {
		userUndo = (User)newDataSource.getValue();
		user = (User)newDataSource.getValue();		

		if (user != null) {
			usernameField.setValue(user.getUsername());
			passwordField.setValue(user.getPassword());
			activeField.setValue(user.isActive());
			
			ObjectProperty propertyLocale = new ObjectProperty(user.getDefaultLocale(), String.class);
			defaultLocaleField.setPropertyDataSource(propertyLocale);
			
			userOrganizationCollectionField.setValue(user);
			userApplicationCollectionField.setValue(user);
			userRoleCollectionField.setValue(user);
			
			tabSheet.getTab(TAB_USER_ORGANIZATION).setVisible(true);
			tabSheet.getTab(TAB_USER_APPLICATION).setVisible(true);
			tabSheet.getTab(TAB_USER_ROLE).setVisible(true);
			
		}
		else {
			tabSheet.getTab(TAB_USER_ORGANIZATION).setVisible(false);
			tabSheet.getTab(TAB_USER_APPLICATION).setVisible(false);
			tabSheet.getTab(TAB_USER_ROLE).setVisible(false);
		}
		
		super.setPropertyDataSource(newDataSource);

	}

	@Override
	public Class<?> getType() {
		return User.class;
		
	}

	@Override
	public Object getValue() {
		// Why I must force this binding?
		if (user != null)
			user.setDefaultLocale(defaultLocaleField.getValue().toString());
		
		return user;
		
	}
	
	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("500px");
		mainLayout.setHeight("140px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("500px");
		setHeight("140px");
		
		// usernameField
		usernameField = new TextField();
		usernameField.setCaption("Nombre Usuario");
		usernameField.setImmediate(false);
		usernameField.setWidth("114px");
		usernameField.setHeight("-1px");
		mainLayout.addComponent(usernameField, "top:16.0px;left:6.0px;");
		
		// passwordField
		passwordField = new PasswordField();
		passwordField.setCaption("Contraseña");
		passwordField.setImmediate(false);
		passwordField.setWidth("114px");
		passwordField.setHeight("-1px");
		mainLayout.addComponent(passwordField, "top:60.0px;left:6.0px;");
		
		// btnApplyUser
		btnApplyUser = new Button();
		btnApplyUser.setCaption("Aplicar");
		btnApplyUser.setImmediate(true);
		btnApplyUser.setWidth("-1px");
		btnApplyUser.setHeight("-1px");
		mainLayout.addComponent(btnApplyUser, "top:16.0px;left:128.0px;");
		
		// btnRemoveUser
		btnRemoveUser = new Button();
		btnRemoveUser.setCaption("Borrar");
		btnRemoveUser.setImmediate(true);
		btnRemoveUser.setWidth("-1px");
		btnRemoveUser.setHeight("-1px");
		mainLayout.addComponent(btnRemoveUser, "top:54.0px;left:128.0px;");
		
		// btnCancelUser
		btnCancelUser = new Button();
		btnCancelUser.setCaption("Cancelar");
		btnCancelUser.setImmediate(true);
		btnCancelUser.setWidth("-1px");
		btnCancelUser.setHeight("-1px");
		mainLayout.addComponent(btnCancelUser, "top:16.0px;left:200.0px;");
		
		// passwordConfirmField
		passwordConfirmField = new PasswordField();
		passwordConfirmField.setCaption("Confirmar contraseña");
		passwordConfirmField.setImmediate(false);
		passwordConfirmField.setWidth("114px");
		passwordConfirmField.setHeight("-1px");
		mainLayout
				.addComponent(passwordConfirmField, "top:102.0px;left:6.0px;");
		
		// defaultLocaleField
		defaultLocaleField = new LocaleField();
		defaultLocaleField.setImmediate(true);
		defaultLocaleField.setWidth("260px");
		defaultLocaleField.setHeight("-1px");
		mainLayout.addComponent(defaultLocaleField, "top:56.0px;left:220.0px;");
		
		// activeField
		activeField = new CheckBox();
		activeField.setCaption("Activa");
		activeField.setImmediate(false);
		activeField.setWidth("-1px");
		activeField.setHeight("-1px");
		mainLayout.addComponent(activeField, "top:16.0px;left:427.0px;");
		
		return mainLayout;
	}

	@Override
	protected void updateLabels() {
		usernameField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.field.UserField.usernameField.caption"));
		passwordField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.field.UserField.passwordField.caption"));
		activeField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.field.UserField.activeField.caption"));
		passwordConfirmField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.field.UserField.passwordConfirmField.caption"));
		btnApplyUser.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.field.UserField.btnApplyUser.caption"));
		btnRemoveUser.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.field.UserField.btnRemoveUser.caption"));
		btnCancelUser.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.field.UserField.btnCancelUser.caption"));
		
	}
}
