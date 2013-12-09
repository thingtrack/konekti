package com.thingtrack.konekti.view.web.form;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import com.thingtrack.konekti.domain.Client;
import com.thingtrack.konekti.domain.ClientGroup;
import com.thingtrack.konekti.domain.ClientType;
import com.thingtrack.konekti.service.api.ClientGroupService;
import com.thingtrack.konekti.service.api.ClientTypeService;
import com.thingtrack.konekti.view.addon.data.BindingSource;
import com.thingtrack.konekti.view.addon.ui.AbstractViewForm;
import com.thingtrack.konekti.view.addon.ui.WindowDialog;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.web.form.field.AddressField;
import com.thingtrack.konekti.view.web.form.field.AddressField.AddressChangeEvent;
import com.thingtrack.konekti.view.web.form.field.AddressField.AddressChangeListener;
import com.thingtrack.konekti.view.web.form.field.ImageField;
import com.thingtrack.konekti.view.web.form.field.UserApplicationCollectionField;
import com.thingtrack.konekti.view.web.form.field.UserField;
import com.thingtrack.konekti.view.web.form.field.UserOrganizationCollectionField;
import com.thingtrack.konekti.view.web.form.field.UserRoleCollectionField;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Select;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ClientViewForm extends AbstractViewForm {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private HorizontalLayout horizontalLayoutBody;
	@AutoGenerated
	private TabSheet tabSheetClient;
	@AutoGenerated
	private VerticalLayout rolLayout;
	@AutoGenerated
	private UserRoleCollectionField userRoleCollectionField;
	@AutoGenerated
	private VerticalLayout applicationverticalLayout;
	@AutoGenerated
	private UserApplicationCollectionField userApplicationCollectionField;
	@AutoGenerated
	private VerticalLayout organizationLayout;
	@AutoGenerated
	private UserOrganizationCollectionField userOrganizationCollectionField;
	@AutoGenerated
	private VerticalLayout securityLayout;
	@AutoGenerated
	private UserField userField;
	@AutoGenerated
	private VerticalLayout addressLayout;
	@AutoGenerated
	private AddressField clientAddressField;
	@AutoGenerated
	private AbsoluteLayout generalLayout;
	@AutoGenerated
	private TextField emailField;
	@AutoGenerated
	private TextArea commentField;
	@AutoGenerated
	private TextField descriptionField;
	@AutoGenerated
	private TextField mobileField;
	@AutoGenerated
	private TextField facebookIdField;
	@AutoGenerated
	private TextField faxField;
	@AutoGenerated
	private TextField phoneField;
	@AutoGenerated
	private ComboBox clientGroupField;
	@AutoGenerated
	private ComboBox clientTypeField;
	@AutoGenerated
	private TextField vatField;
	@AutoGenerated
	private ImageField photoField;
	@AutoGenerated
	private HorizontalLayout horizontalLayoutHeader;
	@AutoGenerated
	private CheckBox activeField;
	@AutoGenerated
	private TextField nameField;
	@AutoGenerated
	private TextField codeField;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
		
	private ClientTypeService clientTypeService;
	private ClientGroupService clientGroupService;
	
	// client type datasource
	private BeanItemContainer<ClientType> bcClientType = new BindingSource<ClientType>(ClientType.class);
	private BeanItemContainer<ClientGroup> bcClientGroup = new BindingSource<ClientGroup>(ClientGroup.class);
	
	private Client client;
	
	private IWorkbenchContext context;
	
	private static final int TAB_GENERAL = 0;
	private static final int TAB_ADDRESS = 1;
	private static final int TAB_SECURITY = 2;
	private static final int TAB_ORGANIZATION = 3;
	private static final int TAB_APPLICATION = 4;
	private static final int TAB_ROL = 5;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 */
	public ClientViewForm(IWorkbenchContext context) throws IllegalArgumentException, Exception {
		this.context = context;
		
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		initComponents();
		
		// configure Organization Type data
		this.userField.setTabSheet(tabSheetClient);
		this.userRoleCollectionField.setContext(context);
		
		clientTypeField.setNullSelectionAllowed(false);
		clientTypeField.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
		clientTypeField.setItemCaptionPropertyId("description");
		
		clientGroupField.setNullSelectionAllowed(false);
		clientGroupField.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
		clientGroupField.setItemCaptionPropertyId("description");
		
		clientAddressField.addListenerAddressChange(new AddressChangeListener() {			
			@Override
			public void addressChange(AddressChangeEvent event) {
				if (client != null)
					client.setAddress(event.getAddress());
				
			}
		});
		
		// get form services from OSGi Service Registry
		getServices();
		
		// load data sources
		loadData();		
	}

	private void initComponents() {
		nameField.setNullRepresentation("");
		descriptionField.setNullRepresentation("");
		commentField.setNullRepresentation("");
		codeField.setNullRepresentation("");
		vatField.setNullRepresentation("");
		phoneField.setNullRepresentation("");
		faxField.setNullRepresentation("");
		mobileField.setNullRepresentation("");
		facebookIdField.setNullRepresentation("");
		emailField.setNullRepresentation("");
		
		userOrganizationCollectionField.setCaption("Organizaciones");
		
		codeField.setRequiredError(codeField.getCaption() + " es un campo requerido");
		clientGroupField.setRequiredError(clientGroupField.getCaption() + " es un campo requerido");
		clientTypeField.setRequiredError(clientTypeField.getCaption() + " es un campo requerido");
		
		codeField.focus();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void getServices() {
		try {
			BundleContext bundleContext = FrameworkUtil.getBundle(ClientViewForm.class).getBundleContext();
			
			ServiceReference clientTypeServiceReference = bundleContext.getServiceReference(ClientTypeService.class.getName());
			clientTypeService = ClientTypeService.class.cast(bundleContext.getService(clientTypeServiceReference));
			
			ServiceReference clientGroupServiceReference = bundleContext.getServiceReference(ClientGroupService.class.getName());
			clientGroupService = ClientGroupService.class.cast(bundleContext.getService(clientGroupServiceReference));
			
		}
		catch (Exception e) {
			e.getMessage();
			
		}
		
	}
	
	private void loadData() throws IllegalArgumentException, Exception {		
		bcClientType.removeAllItems();
		bcClientType.addAll(clientTypeService.getAll());
		
		clientTypeField.setContainerDataSource(bcClientType);
		
		bcClientGroup.removeAllItems();
		bcClientGroup.addAll(clientGroupService.getAll());
		
		clientGroupField.setContainerDataSource(bcClientGroup);
		
	}
	
	@Override
    public void attach() {
		super.attach();
		
		// recover the parent entity (Client) from parent view window (ClientViewForm)
		if (getParent().getWindow() instanceof WindowDialog<?>) {
			@SuppressWarnings("unchecked")
			WindowDialog<Client> clientWindowDialog = (WindowDialog<Client>)getParent().getWindow();
			client = clientWindowDialog.getDomainEntity();
			
			userField.setAgent(client);
		}
	}
	
	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("990px");
		mainLayout.setHeight("440px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("990px");
		setHeight("440px");
		
		// horizontalLayoutHeader
		horizontalLayoutHeader = buildHorizontalLayoutHeader();
		mainLayout.addComponent(horizontalLayoutHeader);
		
		// horizontalLayoutBody
		horizontalLayoutBody = buildHorizontalLayoutBody();
		mainLayout.addComponent(horizontalLayoutBody);
		mainLayout.setExpandRatio(horizontalLayoutBody, 1.0f);
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayoutHeader() {
		// common part: create layout
		horizontalLayoutHeader = new HorizontalLayout();
		horizontalLayoutHeader.setImmediate(false);
		horizontalLayoutHeader.setWidth("100.0%");
		horizontalLayoutHeader.setHeight("-1px");
		horizontalLayoutHeader.setMargin(true);
		horizontalLayoutHeader.setSpacing(true);
		
		// codeField
		codeField = new TextField();
		codeField.setCaption("Código");
		codeField.setImmediate(false);
		codeField.setWidth("100px");
		codeField.setHeight("-1px");
		codeField.setRequired(true);
		horizontalLayoutHeader.addComponent(codeField);
		
		// nameField
		nameField = new TextField();
		nameField.setCaption("Nombre Comercial");
		nameField.setImmediate(false);
		nameField.setWidth("100.0%");
		nameField.setHeight("-1px");
		nameField.setRequired(true);
		horizontalLayoutHeader.addComponent(nameField);
		horizontalLayoutHeader.setExpandRatio(nameField, 1.0f);
		
		// activeField
		activeField = new CheckBox();
		activeField.setCaption("Activo");
		activeField.setImmediate(false);
		activeField.setWidth("80px");
		activeField.setHeight("-1px");
		activeField.setRequired(true);
		horizontalLayoutHeader.addComponent(activeField);
		horizontalLayoutHeader.setComponentAlignment(activeField,
				new Alignment(10));
		
		return horizontalLayoutHeader;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayoutBody() {
		// common part: create layout
		horizontalLayoutBody = new HorizontalLayout();
		horizontalLayoutBody.setImmediate(false);
		horizontalLayoutBody.setWidth("100.0%");
		horizontalLayoutBody.setHeight("100.0%");
		horizontalLayoutBody.setMargin(true);
		horizontalLayoutBody.setSpacing(true);
		
		// tabSheetClient
		tabSheetClient = buildTabSheetClient();
		horizontalLayoutBody.addComponent(tabSheetClient);
		
		return horizontalLayoutBody;
	}

	@AutoGenerated
	private TabSheet buildTabSheetClient() {
		// common part: create layout
		tabSheetClient = new TabSheet();
		tabSheetClient.setImmediate(true);
		tabSheetClient.setWidth("100.0%");
		tabSheetClient.setHeight("100.0%");
		
		// generalLayout
		generalLayout = buildGeneralLayout();
		tabSheetClient.addTab(generalLayout, "General", null);
		
		// addressLayout
		addressLayout = buildAddressLayout();
		tabSheetClient.addTab(addressLayout, "Dirección", null);
		
		// securityLayout
		securityLayout = buildSecurityLayout();
		tabSheetClient.addTab(securityLayout, "Seguridad", null);
		
		// organizationLayout
		organizationLayout = buildOrganizationLayout();
		tabSheetClient.addTab(organizationLayout, "Organización", null);
		
		// applicationverticalLayout
		applicationverticalLayout = buildApplicationverticalLayout();
		tabSheetClient.addTab(applicationverticalLayout, "Aplicaciones", null);
		
		// rolLayout
		rolLayout = buildRolLayout();
		tabSheetClient.addTab(rolLayout, "Rol", null);
		
		return tabSheetClient;
	}

	@AutoGenerated
	private AbsoluteLayout buildGeneralLayout() {
		// common part: create layout
		generalLayout = new AbsoluteLayout();
		generalLayout.setImmediate(false);
		generalLayout.setWidth("100.0%");
		generalLayout.setHeight("100.0%");
		generalLayout.setMargin(false);
		
		// photoField
		photoField = new ImageField();
		photoField.setImmediate(false);
		photoField.setWidth("420px");
		photoField.setHeight("140px");
		generalLayout.addComponent(photoField, "top:20.0px;left:10.0px;");
		
		// vatField
		vatField = new TextField();
		vatField.setCaption("CIF/NIF");
		vatField.setImmediate(false);
		vatField.setWidth("120px");
		vatField.setHeight("-1px");
		generalLayout.addComponent(vatField, "top:16.0px;left:435.0px;");
		
		// clientTypeField
		clientTypeField = new ComboBox();
		clientTypeField.setCaption("Tipo");
		clientTypeField.setImmediate(false);
		clientTypeField.setDescription("Tipo Cliente");
		clientTypeField.setWidth("-1px");
		clientTypeField.setHeight("-1px");
		clientTypeField.setRequired(true);
		generalLayout.addComponent(clientTypeField, "top:54.0px;left:435.0px;");
		
		// clientGroupField
		clientGroupField = new ComboBox();
		clientGroupField.setCaption("Grupo");
		clientGroupField.setImmediate(false);
		clientGroupField.setWidth("-1px");
		clientGroupField.setHeight("-1px");
		clientGroupField.setRequired(true);
		generalLayout
				.addComponent(clientGroupField, "top:92.0px;left:435.0px;");
		
		// phoneField
		phoneField = new TextField();
		phoneField.setCaption("Teléfono 01");
		phoneField.setImmediate(false);
		phoneField.setWidth("115px");
		phoneField.setHeight("-1px");
		generalLayout.addComponent(phoneField, "top:16.0px;left:653.0px;");
		
		// faxField
		faxField = new TextField();
		faxField.setCaption("Fax");
		faxField.setImmediate(false);
		faxField.setWidth("115px");
		faxField.setHeight("-1px");
		generalLayout.addComponent(faxField, "top:54.0px;left:779.0px;");
		
		// facebookIdField
		facebookIdField = new TextField();
		facebookIdField.setCaption("Facebook Id");
		facebookIdField.setImmediate(false);
		facebookIdField.setWidth("242px");
		facebookIdField.setHeight("24px");
		generalLayout.addComponent(facebookIdField, "top:90.0px;left:653.0px;");
		
		// mobileField
		mobileField = new TextField();
		mobileField.setCaption("Teléfono 02");
		mobileField.setImmediate(false);
		mobileField.setWidth("115px");
		mobileField.setHeight("-1px");
		generalLayout.addComponent(mobileField, "top:16.0px;left:780.0px;");
		
		// descriptionField
		descriptionField = new TextField();
		descriptionField.setCaption("Descripción");
		descriptionField.setImmediate(false);
		descriptionField.setWidth("460px");
		descriptionField.setHeight("-1px");
		generalLayout.addComponent(descriptionField,
				"top:132.0px;left:435.0px;");
		
		// commentField
		commentField = new TextArea();
		commentField.setCaption("Comentarios");
		commentField.setImmediate(false);
		commentField.setWidth("885px");
		commentField.setHeight("70px");
		generalLayout.addComponent(commentField, "top:206.0px;left:11.0px;");
		
		// emailField
		emailField = new TextField();
		emailField.setCaption("Email");
		emailField.setImmediate(false);
		emailField.setWidth("460px");
		emailField.setHeight("-1px");
		generalLayout.addComponent(emailField, "top:172.0px;left:435.0px;");
		
		return generalLayout;
	}

	@AutoGenerated
	private VerticalLayout buildAddressLayout() {
		// common part: create layout
		addressLayout = new VerticalLayout();
		addressLayout.setImmediate(false);
		addressLayout.setWidth("100.0%");
		addressLayout.setHeight("100.0%");
		addressLayout.setMargin(false);
		
		// clientAddressField
		clientAddressField = new AddressField();
		clientAddressField.setImmediate(false);
		clientAddressField.setWidth("100.0%");
		clientAddressField.setHeight("100.0%");
		addressLayout.addComponent(clientAddressField);
		addressLayout.setExpandRatio(clientAddressField, 1.0f);
		
		return addressLayout;
	}

	@AutoGenerated
	private VerticalLayout buildSecurityLayout() {
		// common part: create layout
		securityLayout = new VerticalLayout();
		securityLayout.setImmediate(false);
		securityLayout.setWidth("100.0%");
		securityLayout.setHeight("100.0%");
		securityLayout.setMargin(false);
		
		// userField
		userField = new UserField();
		userField.setImmediate(false);
		userField.setWidth("100.0%");
		userField.setHeight("100.0%");
		securityLayout.addComponent(userField);
		securityLayout.setExpandRatio(userField, 1.0f);
		
		return securityLayout;
	}

	@AutoGenerated
	private VerticalLayout buildOrganizationLayout() {
		// common part: create layout
		organizationLayout = new VerticalLayout();
		organizationLayout.setImmediate(false);
		organizationLayout.setWidth("100.0%");
		organizationLayout.setHeight("100.0%");
		organizationLayout.setMargin(false);
		
		// userOrganizationCollectionField
		userOrganizationCollectionField = new UserOrganizationCollectionField();
		userOrganizationCollectionField.setImmediate(false);
		userOrganizationCollectionField.setWidth("100.0%");
		userOrganizationCollectionField.setHeight("100.0%");
		organizationLayout.addComponent(userOrganizationCollectionField);
		organizationLayout
				.setExpandRatio(userOrganizationCollectionField, 1.0f);
		
		return organizationLayout;
	}

	@AutoGenerated
	private VerticalLayout buildApplicationverticalLayout() {
		// common part: create layout
		applicationverticalLayout = new VerticalLayout();
		applicationverticalLayout.setImmediate(false);
		applicationverticalLayout.setWidth("100.0%");
		applicationverticalLayout.setHeight("100.0%");
		applicationverticalLayout.setMargin(false);
		
		// userApplicationCollectionField
		userApplicationCollectionField = new UserApplicationCollectionField(context);
		userApplicationCollectionField.setImmediate(false);
		userApplicationCollectionField.setWidth("100.0%");
		userApplicationCollectionField.setHeight("100.0%");
		applicationverticalLayout.addComponent(userApplicationCollectionField);
		applicationverticalLayout.setExpandRatio(
				userApplicationCollectionField, 1.0f);
		
		return applicationverticalLayout;
	}

	@AutoGenerated
	private VerticalLayout buildRolLayout() {
		// common part: create layout
		rolLayout = new VerticalLayout();
		rolLayout.setImmediate(false);
		rolLayout.setWidth("100.0%");
		rolLayout.setHeight("100.0%");
		rolLayout.setMargin(false);
		
		// userRoleCollectionField
		userRoleCollectionField = new UserRoleCollectionField();
		userRoleCollectionField.setImmediate(false);
		userRoleCollectionField.setWidth("100.0%");
		userRoleCollectionField.setHeight("100.0%");
		rolLayout.addComponent(userRoleCollectionField);
		rolLayout.setExpandRatio(userRoleCollectionField, 1.0f);
		
		return rolLayout;
	}

	@Override
	protected void updateLabels() {
		tabSheetClient.getTab(TAB_GENERAL).setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ClientViewForm.tabSheetClient.generalTab"));
		tabSheetClient.getTab(TAB_ADDRESS).setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ClientViewForm.tabSheetClient.addressTab"));
		tabSheetClient.getTab(TAB_SECURITY).setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ClientViewForm.tabSheetClient.securityTab"));
		tabSheetClient.getTab(TAB_ORGANIZATION).setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ClientViewForm.tabSheetClient.organizationTab"));
		tabSheetClient.getTab(TAB_APPLICATION).setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ClientViewForm.tabSheetClient.applicationTab"));
		tabSheetClient.getTab(TAB_ROL).setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ClientViewForm.tabSheetClient.roleTab"));
		
		codeField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ClientViewForm.codeField.caption"));
		nameField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ClientViewForm.nameField.caption"));
		activeField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ClientViewForm.activeField.caption"));
		vatField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ClientViewForm.vatField.caption"));
		clientTypeField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ClientViewForm.clientTypeField.caption"));
		clientGroupField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ClientViewForm.clientGroupField.caption"));
		phoneField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ClientViewForm.phoneField.caption"));
		faxField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ClientViewForm.faxField.caption"));
		facebookIdField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ClientViewForm.facebookIdField.caption"));
		mobileField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ClientViewForm.mobileField.caption"));
		descriptionField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ClientViewForm.descriptionField.caption"));
		commentField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ClientViewForm.commentField.caption"));
		emailField.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.web.form.ClientViewForm.emailField.caption"));
		
	}

}
