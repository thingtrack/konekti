package com.thingtrack.konekti.view.module.dashboard.portlet;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.thingtrack.konekti.domain.Alarm;
import com.thingtrack.konekti.service.api.AlarmService;
import com.thingtrack.konekti.view.addon.data.BindingSource;
import com.thingtrack.konekti.view.addon.ui.DataGridView;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.module.dashboard.internal.DashBoardViewContainer;
import com.thingtrack.konekti.view.module.dashboard.portlet.AlarmPortletToolbar.ClickConfirmAlarmButtonListener;
import com.thingtrack.konekti.view.module.dashboard.portlet.AlarmPortletToolbar.ClickNavigationEvent;
import com.thingtrack.konekti.view.module.dashboard.portlet.AlarmPortletToolbar.ClickRefreshAlarmButtonListener;
import com.thingtrack.konekti.view.module.dashboard.portlet.KonektiPortal.KonektiPortlet;
import com.vaadin.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AlarmPortlet extends KonektiPortlet implements ClickConfirmAlarmButtonListener, ClickRefreshAlarmButtonListener{
	private VerticalLayout mainLayout;
	private AlarmPortletToolbar toolbarAlarm;
	private DataGridView dgAlarm;
	private IWorkbenchContext context;
	
	private AlarmService alarmService;
	
	private BindingSource<Alarm> bsAlarm = new BindingSource<Alarm>(Alarm.class, 0);
	
	public AlarmPortlet(IWorkbenchContext context, KonektiPortal konektiPortal) {
		konektiPortal.super();
		
		this.context = context;
		this.alarmService = DashBoardViewContainer.getAlarmService();
		
		buildMainLayout();
		
		// initialize datasource views
		initView();
		
		setCollapsible(mainLayout, false);
		setClosable(mainLayout, false);
	}
	
	private void initView() {
		// initialize Slide View Organization View
		dgAlarm.setImmediate(true);

		refreshAlarmBindindSource();

		// STEP 01: create grid view for slide Organization View
		try {
			bsAlarm.addNestedContainerProperty("alarmType.description");
			bsAlarm.addNestedContainerProperty("alarmStatus.description");
			bsAlarm.addNestedContainerProperty("area.description");
			
			dgAlarm.setBindingSource(bsAlarm);
			dgAlarm.setVisibleColumns(new String[] { "area.description", "alarmType.description", "message", "alarmDate", "alarmStatus.description" });
			dgAlarm.setColumnHeaders(new String[] { "Area Trabajo",  "Tipo", "Mensaje", "Fecha Alarma", "Estado" });

			toolbarAlarm.addListenerConfirmAlarmButton(this);
			toolbarAlarm.addListenerRefreshAlarmButton(this);
		} catch (Exception ex) {
			ex.getMessage();
		}

	}

	private void refreshAlarmBindindSource() {
		try {
			bsAlarm.removeAllItems();
			bsAlarm.addAll(alarmService.getAll(context.getUser()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void refreshAlarmButtonClick(ClickNavigationEvent event) {
		refreshAlarmBindindSource();
		
	}

	@Override
	public void confirmAlarmButtonClick(ClickNavigationEvent event) {
		Alarm alarm = (Alarm) event.getRegister();		
		
		try {
			alarmService.delete(alarm);
			
			refreshAlarmBindindSource();
		} catch (Exception e) {
			throw new RuntimeException("¡No se confirmar la alarma!", e);
		}
		
	}
	
	@Override
	public void addComponent(Component c, int position) {
        super.addComponent(c, position);
        
    };
    
    private void buildMainLayout() {
    	mainLayout = new VerticalLayout();
    	mainLayout.setWidth("100%");
    	mainLayout.setHeight("100%");
    	mainLayout.setCaption("Listado Alarmas");
    	
    	toolbarAlarm = new AlarmPortletToolbar(0, bsAlarm);
    	mainLayout.addComponent(toolbarAlarm);
    	
		// common part: create layout
    	dgAlarm = new DataGridView() {
			@Override
			protected String formatPropertyValue(Object rowId, Object colId, Property property) {
				// Format by property type
				if (property.getType() == Date.class) {
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

					if (property.getValue() != null)
						return df.format((Date) property.getValue());
				}

				return super.formatPropertyValue(rowId, colId, property);
			}
		};
    	dgAlarm.setImmediate(false);
    	dgAlarm.setWidth("100%");
    	dgAlarm.setHeight("100%");
    	
    	mainLayout.addComponent(dgAlarm);
    	mainLayout.setExpandRatio(dgAlarm, 1.0f);
    	
		// top-level component properties
    	addComponent(mainLayout);
		
	}
    
	@Override
	protected void updateLabels() {
		mainLayout.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.module.dashboard.internal.AlarmPortlet.dgAlarm.caption"));
		
		dgAlarm.setColumnHeaders(new String[] { getI18N().getMessage("com.thingtrack.konekti.view.module.dashboard.internal.AlarmPortlet.dgAlarm.column.area"), 
				  getI18N().getMessage("com.thingtrack.konekti.view.module.dashboard.internal.AlarmPortlet.dgAlarm.column.type"), 
				  getI18N().getMessage("com.thingtrack.konekti.view.module.dashboard.internal.AlarmPortlet.dgAlarm.column.message"),
				  getI18N().getMessage("com.thingtrack.konekti.view.module.dashboard.internal.AlarmPortlet.dgAlarm.column.date"),
				  getI18N().getMessage("com.thingtrack.konekti.view.module.dashboard.internal.AlarmPortlet.dgAlarm.column.status")});
		
	}
}
