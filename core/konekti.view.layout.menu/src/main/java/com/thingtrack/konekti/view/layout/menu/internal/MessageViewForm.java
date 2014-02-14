package com.thingtrack.konekti.view.layout.menu.internal;

import java.io.Serializable;
import java.util.Date;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.domain.Location;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.User;
import com.thingtrack.konekti.service.api.UserService;
import com.thingtrack.konekti.view.addon.ui.AbstractViewForm;
import com.thingtrack.konekti.view.addon.ui.DataGridView;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MessageViewForm extends AbstractViewForm {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Button btnSend;
	@AutoGenerated
	private DataGridView dgMessage;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;
	@AutoGenerated
	private TextArea textArea;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private MessageEventListener messageEventListener = null;
			
	private IWorkbenchContext context;
	
	private UserService userService;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public MessageViewForm() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		// TODO add user code here
		getServices();
		
		btnSend.addListener(new ClickListener() {			
			public void buttonClick(ClickEvent event) {		
				if (textArea.getValue() == null)
					return;
				
				// test	User (48)
				User userTest = null;
				try {
					userTest = userService.get(48);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
							
				if (messageEventListener != null)
					messageEventListener.getMessage(new MessageEvent(context.getUser().getActiveOrganization(),
																	 context.getUser().getActiveLocation(),																 
																	 context.getUser().getActiveArea(),
																	 context.getUser(),
																	 context.getUser().getActiveOrganization(),
																	 context.getUser().getActiveLocation(),
																	 context.getUser().getActiveArea(),
																	 userTest,
																	 textArea.getValue().toString(),
																	 new Date()));
			}
		});
		
		// Define columns for the built-in container
		dgMessage.addContainerProperty("Organization", String.class, null);
		dgMessage.addContainerProperty("Location",  String.class, null);
		dgMessage.addContainerProperty("Area", String.class, null);
		dgMessage.addContainerProperty("Type", String.class, null);
		dgMessage.addContainerProperty("User", String.class, null);
		dgMessage.addContainerProperty("Message",  String.class, null);
		dgMessage.addContainerProperty("Date",  Date.class, null);
		
		dgMessage.setColumnHeaders(new String[] { "Organización", "Localización", "Area", "Tipo", "Usuario", "Mensaje", "Fecha" });
	}

	public void setContext(IWorkbenchContext context) {
		this.context = context;
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void getServices() {
		try {
			BundleContext bundleContext = FrameworkUtil.getBundle(MessageViewForm.class).getBundleContext();

			ServiceReference userServiceReference = bundleContext.getServiceReference(UserService.class.getName());
			userService = UserService.class.cast(bundleContext.getService(userServiceReference));
			
		}
		catch (Exception e) {
			e.getMessage();
			
		}
		
	}
	
	public void addMessage(Organization organization, Location location, Area area, User user, String payload, Date messageDate) {
		// Add a row the hard way
		Object newMessage = dgMessage.addItem();
		Item rowMessage = dgMessage.getItem(newMessage);
		rowMessage.getItemProperty("Organization").setValue(organization.getName());
		rowMessage.getItemProperty("Location").setValue(location.getName());
		rowMessage.getItemProperty("Area").setValue(area.getName());
		if (user.getEmployeeAgent() != null) {
			rowMessage.getItemProperty("User").setValue("Empleado");
			rowMessage.getItemProperty("User").setValue(user.getEmployeeAgent().getName() + " " + user.getEmployeeAgent().getSurname());
		}
		else if (user.getClient() != null) {
			rowMessage.getItemProperty("User").setValue("Cliente");
			rowMessage.getItemProperty("User").setValue(user.getClient().getName());
		}
		else {		
			rowMessage.getItemProperty("User").setValue("Proveedor");
			rowMessage.getItemProperty("User").setValue(user.getSupplier().getName());
		}		
		rowMessage.getItemProperty("Message").setValue(payload);
		rowMessage.getItemProperty("Date").setValue(messageDate);
	}
	
	public void addListenerMessageEvent(MessageEventListener listener) {
		this.messageEventListener = listener;
		
	}
	
	public interface MessageEventListener extends Serializable {
        public void getMessage(MessageEvent event);

    }
	
	public class MessageEvent {
		private Organization organizationFrom; 
        private Location locationFrom; 
        private Area areaFrom;
        private User userFrom;
        private Organization organizationTo; 
        private Location locationTo; 
        private Area areaTo; 			                
        private User userTo; 
        private String payload; 
        private Date messageDate;
		
		public MessageEvent(Organization organizationFrom, 
			                Location locationFrom, 
			                Area areaFrom,
			                User userFrom,
			                Organization organizationTo, 
			                Location locationTo, 
			                Area areaTo, 			                
			                User userTo, 
			                String payload, 
			                Date messageDate) {
			this.organizationFrom = organizationFrom;
			this.locationFrom = locationFrom;
			this.areaFrom = areaFrom;
			this.userFrom = userFrom;
			this.organizationTo = organizationTo;
			this.locationTo = locationTo;
			this.areaTo = areaTo;
			this.userTo = userTo;
			this.payload = payload;
			this.messageDate = messageDate;
			
		}

		public Organization getOrganizationFrom() {
			return this.organizationFrom;
			
		}
		
		public Location getLocationFrom() {
			return this.locationFrom;
			
		}

		public Area getAreaFrom() {
			return this.areaFrom;
			
		}

		public User getUserFrom() {
			return this.userFrom;
			
		}

		public Organization getOrganizationTo() {
			return this.organizationTo;
			
		}
		
		public Location getLocationTo() {
			return this.locationTo;
			
		}

		public Area getAreaTo() {
			return this.areaTo;
			
		}

		public User getUserTo() {
			return this.userTo;
			
		}
		
		public String getPayload() {
			return this.payload;
			
		}

		public Date getMessageDate() {
			return this.messageDate;
			
		}
		
	}
	
	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("350px");
		mainLayout.setHeight("350px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("350px");
		setHeight("350px");
		
		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		mainLayout.addComponent(horizontalLayout_1);
		
		// dgMessage
		dgMessage = new DataGridView();
		dgMessage.setImmediate(false);
		dgMessage.setWidth("100.0%");
		dgMessage.setHeight("100.0%");
		mainLayout.addComponent(dgMessage);
		mainLayout.setExpandRatio(dgMessage, 1.0f);
		
		// btnSend
		btnSend = new Button();
		btnSend.setCaption("Enviar");
		btnSend.setImmediate(false);
		btnSend.setWidth("-1px");
		btnSend.setHeight("-1px");
		mainLayout.addComponent(btnSend);
		mainLayout.setComponentAlignment(btnSend, new Alignment(6));
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_1() {
		// common part: create layout
		horizontalLayout_1 = new HorizontalLayout();
		horizontalLayout_1.setImmediate(false);
		horizontalLayout_1.setWidth("100.0%");
		horizontalLayout_1.setHeight("-1px");
		horizontalLayout_1.setMargin(false);
		
		// textArea
		textArea = new TextArea();
		textArea.setImmediate(false);
		textArea.setWidth("100.0%");
		textArea.setHeight("-1px");
		horizontalLayout_1.addComponent(textArea);
		horizontalLayout_1.setExpandRatio(textArea, 1.0f);
		
		return horizontalLayout_1;
	}

	@Override
	protected void updateLabels() {
		// TODO Auto-generated method stub
		
	}
}