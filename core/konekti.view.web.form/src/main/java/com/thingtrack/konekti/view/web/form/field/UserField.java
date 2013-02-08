package com.thingtrack.konekti.view.web.form.field;

import java.util.List;

import org.vaadin.addon.customfield.CustomField;

import com.thingtrack.konekti.domain.User;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class UserField extends CustomField {

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private UserRoleCollectionField rolesField;
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

	private User userUndo;
	private User user;

	private final static String DEFAULT_LOCALE = "es" + LocaleField.LOCALE_SEPARATOR + "ES";
	
	public UserField() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		btnApplyUser.setEnabled(false);
		btnCancelUser.setEnabled(false);		
		passwordConfirmField.setEnabled(false);
		
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
				}
				
				user.setUsername(usernameField.getValue().toString());
				user.setPassword(passwordField.getValue().toString());
				
				btnApplyUser.setEnabled(false);
				btnCancelUser.setEnabled(false);
				passwordConfirmField.setEnabled(false);
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
				
				btnApplyUser.setEnabled(false);
				btnCancelUser.setEnabled(false);
			}
		});
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setPropertyDataSource(Property newDataSource) {
		userUndo = (User)newDataSource.getValue();
		user = (User)newDataSource.getValue();		

		if (user != null) {
			usernameField.setValue(user.getUsername());
			passwordField.setValue(user.getPassword());			
			ObjectProperty propertyLocale = new ObjectProperty(user.getDefaultLocale(), String.class);
			defaultLocaleField.setPropertyDataSource(propertyLocale);
			ObjectProperty propertyRoles = new ObjectProperty(user.getRoles(), List.class);
			rolesField.setPropertyDataSource(propertyRoles);
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
		mainLayout.setHeight("240px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("500px");
		setHeight("240px");
		
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
		defaultLocaleField.setWidth("180px");
		defaultLocaleField.setHeight("-1px");
		mainLayout.addComponent(defaultLocaleField, "top:16.0px;left:300.0px;");
		
		// userRoleCollectionField_1
		rolesField = new UserRoleCollectionField();
		rolesField.setImmediate(true);
		rolesField.setWidth("260px");
		rolesField.setHeight("140px");
		mainLayout.addComponent(rolesField,
				"top:80.0px;left:220.0px;");
		
		return mainLayout;
	}
}
