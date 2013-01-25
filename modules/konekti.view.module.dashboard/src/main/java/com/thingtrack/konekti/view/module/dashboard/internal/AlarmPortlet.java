package com.thingtrack.konekti.view.module.dashboard.internal;

import com.thingtrack.konekti.domain.Alarm;
import com.thingtrack.konekti.service.api.AlarmService;
import com.thingtrack.konekti.view.addon.data.BindingSource;
import com.thingtrack.konekti.view.addon.ui.DataGridView;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.module.dashboard.internal.KonektiPortal.KonektiPortlet;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class AlarmPortlet extends KonektiPortlet {
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
	}
	
	private void initView() {
		// initialize Slide View Organization View
		dgAlarm.setImmediate(true);

		refreshBindindSource();

		// STEP 01: create grid view for slide Organization View
		try {
			dgAlarm.setBindingSource(bsAlarm);
			dgAlarm.setVisibleColumns(new String[] { "location.name", "alarmType.description", 
					                                 "message",	"alarmDate", "alarmStatus.description" });
			dgAlarm.setColumnHeaders(new String[] { "Ubicación",  "Tipo", "Mensaje",
													"Fecha Alarma", "Estado" });

		} catch (Exception ex) {
			ex.getMessage();
		}

	}

	private void refreshBindindSource() {
		try {
			bsAlarm.removeAllItems();
			bsAlarm.addAll(alarmService.getAll());

			bsAlarm.addNestedContainerProperty("alarmType.description");
			bsAlarm.addNestedContainerProperty("alarmStatus.description");
			bsAlarm.addNestedContainerProperty("location.name");
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void addComponent(Component c, int position) {
        super.addComponent(c, position);
        
    };
    
    private void buildMainLayout() {
		// common part: create layout
    	dgAlarm = new DataGridView();
    	dgAlarm.setImmediate(false);
    	dgAlarm.setWidth("100%");
    	dgAlarm.setHeight("100%");
		
    	dgAlarm.setCaption("Listado Alarmas");
    		
		// top-level component properties
		addComponent(dgAlarm);
		
	}
}
