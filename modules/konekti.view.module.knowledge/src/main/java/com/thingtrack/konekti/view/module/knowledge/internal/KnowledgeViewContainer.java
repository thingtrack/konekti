package com.thingtrack.konekti.view.module.knowledge.internal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.VerticalLayout;

import com.thingtrack.konekti.view.addon.ui.AbstractViewContainer;
import com.thingtrack.konekti.view.addon.ui.SliderView;
import com.thingtrack.konekti.view.kernel.IModule;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.kernel.ui.layout.ISliderView;
import com.thingtrack.konekti.view.kernel.ui.layout.IViewChangeListener;
import com.thingtrack.konekti.view.kernel.ui.layout.IView;

import com.thingtrack.konekti.service.api.KnowledgeService;

@SuppressWarnings("serial")
public class KnowledgeViewContainer extends AbstractViewContainer {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private SliderView sliderView;

	/** Views **/
	private KnowledgeView knowledgeView;
		
	/** Business services **/
	@Autowired
	private KnowledgeService knowledgeService;;
	@Autowired
	private StatefulKnowledgeSession statefulKnowledgeSession;
	
	private IWorkbenchContext context; 
	
	// Thread Local Bundle Business Services
	private static ThreadLocal<KnowledgeService> threadKnowledgeService = new ThreadLocal<KnowledgeService>();
	private static ThreadLocal<StatefulKnowledgeSession> threadStatefulKnowledgeSession = new ThreadLocal<StatefulKnowledgeSession>();
	
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
		
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public KnowledgeViewContainer(IWorkbenchContext context, IModule module) {
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
		threadKnowledgeService.set(knowledgeService);
		threadStatefulKnowledgeSession.set(statefulKnowledgeSession);
		
		// add all views controlled by SliderView Component
		knowledgeView = new KnowledgeView(context, this);
		sliderView.addView(knowledgeView);
		views.put(0, knowledgeView);
	}

	@SuppressWarnings("unused")
	@PreDestroy
	private void destroyViews() {
		// destroy thread local bundle services
		threadKnowledgeService.set(null);
		threadStatefulKnowledgeSession.set(null);
	}
	
    public static KnowledgeService getKnowledgeService() {
        return threadKnowledgeService.get();
        
    }
    
    public static StatefulKnowledgeSession getStatefulKnowledgeSession() {
        return threadStatefulKnowledgeSession.get();
        
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
