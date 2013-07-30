package com.thingtrack.konekti.view.module.dashboard.internal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.VerticalLayout;

import com.thingtrack.konekti.service.api.AlarmService;
import com.thingtrack.konekti.view.addon.ui.AbstractViewContainer;
import com.thingtrack.konekti.view.addon.ui.SliderView;
import com.thingtrack.konekti.view.kernel.IModule;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.kernel.ui.layout.ISliderView;
import com.thingtrack.konekti.view.kernel.ui.layout.IViewChangeListener;
import com.thingtrack.konekti.view.kernel.ui.layout.IView;

@SuppressWarnings("serial")
public class DashBoardViewContainer extends AbstractViewContainer {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private SliderView sliderView;

	/** Views **/
	private DashBoardView dashBoardView;
		
	/** Business services **/	
	private IWorkbenchContext context; 
	
	/** Business services **/
	@Autowired
	private AlarmService alarmService;	
	
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
		
	// Thread Local Bundle Business Services
	private static ThreadLocal<AlarmService> threadAlarmService = new ThreadLocal<AlarmService>();
		
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public DashBoardViewContainer(IWorkbenchContext context, IModule module) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		this.context = context;
		this.module = module;
	}
	
	@SuppressWarnings("unused")
	@PostConstruct
	private void createViews() {
		// initialize thread local bundle services
		threadAlarmService.set(alarmService);
		
		// add all views controlled by SliderView Component
		dashBoardView = new DashBoardView(context, this);
		sliderView.addView(dashBoardView);
		views.put(0, dashBoardView);
	}
    
	@SuppressWarnings("unused")
	@PreDestroy
	private void destroyViews() {
		// destroy thread local bundle services
		threadAlarmService.set(null);
	}
	
    public static AlarmService getAlarmService() {
        return threadAlarmService.get();
        
    }
    
	@Override
	public ISliderView getSliderView() {
		return sliderView;
		
	}
	
	@Override
	public IView getSelectedView() {
		return sliderView.getSelectedView();
		
	}
	
	@Override
	public void addListener(IViewChangeListener listener) {
		sliderView.addListener(listener);
		
	}
	
	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// sliderView_1
		sliderView = new SliderView();
		sliderView.setImmediate(false);
		sliderView.setWidth("100.0%");
		sliderView.setHeight("100.0%");
		mainLayout.addComponent(sliderView);
		mainLayout.setExpandRatio(sliderView, 1.0f);
		
		return mainLayout;
	}

}
