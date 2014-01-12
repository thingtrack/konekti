package com.thingtrack.konekti.view.module.alarm.addon;

import java.io.Serializable;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.vaadin.peter.buttongroup.ButtonGroup;

import com.thingtrack.konekti.domain.Job;
import com.thingtrack.konekti.view.addon.data.BindingSource;
import com.thingtrack.konekti.view.addon.data.BindingSource.IndexChangeEvent;
import com.thingtrack.konekti.view.addon.data.BindingSource.IndexChangeListener;
import com.thingtrack.konekti.view.addon.ui.AbstractToolbar;
import com.thingtrack.konekti.view.kernel.ui.layout.IViewContainer;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.client.MouseEventDetails;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class JobToolbar extends AbstractToolbar {
	@AutoGenerated
	private HorizontalLayout toolbarLayout;

	@AutoGenerated
	private Button btnStartJob;

	@AutoGenerated
	private Button btnStopJob;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private Object register;	
	private int position = 0;			
	private IViewContainer viewContainer;
	private Scheduler scheduler;	
	
	// navigator button listeners
	private ClickStartJobButtonListener listenerStartJobButton = null;
	private ClickStopJobButtonListener listenerStopJobButton = null;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */	
	public JobToolbar(int position, final BindingSource<?> bindingSource, IViewContainer viewContainer, final Scheduler scheduler) {
		super(position, bindingSource);
			
		buildMainLayout();
		//setCompositionRoot(mainLayout);

		// TODO add user code here
		this.position = position;
		this.viewContainer = viewContainer;
		this.scheduler = scheduler;
		
		setBindingSource(bindingSource);
		
		btnStartJob.setDescription("Iniciar Job");
		btnStopJob.setDescription("Parar Job");
		
		// set reject button listener
		btnStartJob.addListener(new ClickListener() {			
			public void buttonClick(ClickEvent event) {
				int index = bindingSource.getIndex();
				
				if (index == 0)
					return;
				
				Job jobSelected = (Job) bindingSource.getItemId();
				
				try {
					scheduler.resumeJob(new JobKey(jobSelected.getJobName(), jobSelected.getJobGroup()));
					
				} catch (SchedulerException e) {
					throw new RuntimeException("¡No se pudo parar el job seleccionada!", e);
				}
				
				if (listenerStartJobButton != null)
					listenerStartJobButton.startJobButtonClick(new ClickNavigationEvent(event.getButton(), event.getComponent() , null, jobSelected, 0));					
				
			}
		});
		
		btnStopJob.addListener(new ClickListener() {			
			public void buttonClick(ClickEvent event) {
				int index = bindingSource.getIndex();
				
				if (index == 0)
					return;
				
				Job jobSelected = (Job) bindingSource.getItemId();
				
				try {
					scheduler.pauseJob(new JobKey(jobSelected.getJobName(), jobSelected.getJobGroup()));					
					
				} catch (SchedulerException e) {
					throw new RuntimeException("¡No se pudo parar el job seleccionada!", e);
				}
				
				if (listenerStopJobButton != null)
					listenerStopJobButton.stopJobButtonClick(new ClickNavigationEvent(event.getButton(), event.getComponent() , null, jobSelected, 0));					
				
			}
		});
		
	}
	
	@Override
	public int getPosition() {
		return this.position;
		
	}

	@Override
	public ComponentContainer getContent() {
		return this.toolbarLayout;
		
	}
	
	public void addListenerStartJobButton(ClickStartJobButtonListener listener) {
		this.listenerStartJobButton = listener;
		
	}
	
	public void addListenerStopJobButton(ClickStopJobButtonListener listener) {
		this.listenerStopJobButton = listener;
		
	}
	
	public interface ClickStartJobButtonListener extends Serializable {
        public void startJobButtonClick(ClickNavigationEvent event);

    }
	
	public interface ClickStopJobButtonListener extends Serializable {
        public void stopJobButtonClick(ClickNavigationEvent event);

    }
	
	public class ClickNavigationEvent extends ClickEvent {
		private int index;
		private Object register;
		
		public ClickNavigationEvent(Button button, Component source) {
			button.super(source);
			
		}

		public ClickNavigationEvent(Button button, Component source, MouseEventDetails details) {
			button.super(source, details);
			
		}

		public ClickNavigationEvent(Button button, Component source, MouseEventDetails details, Object register, int index) {
			button.super(source, details);
			
			this.index = index;
			this.register = register;
		}

		public int getIndex() {
			return this.index;
			
		}
		
		public Object getRegister() {
			return this.register;
			
		}
		
	  }
	
	@Override
	public void setBindingSource(BindingSource<?> bindingSource) {
		this.bindingSource = bindingSource;
		
		// add change index binding source
		if (bindingSource != null) {
			bindingSource.addListenerToolBar((IndexChangeListener)this);
			
		}
		
	}
	
	@Override
	public void bindingSourceIndexChange(IndexChangeEvent event) {
		if (bindingSource != null) {
			Job jobSelected = (Job)event.getRegister();
			
			if(jobSelected == null)
				return;
			
			if (jobSelected.getActive()) {
				btnStartJob.setEnabled(false);
				btnStopJob.setEnabled(true);
			}
			else {
				btnStartJob.setEnabled(true);
				btnStopJob.setEnabled(false);
			}
		}
		
	}
	
	@AutoGenerated
	private void buildMainLayout() {
		toolbarLayout = buildToolbarLayout();
		addComponent(toolbarLayout);
		
	}
	
	@AutoGenerated
	private HorizontalLayout buildToolbarLayout() {		
		toolbarLayout = new HorizontalLayout();
		toolbarLayout.setImmediate(false);
		toolbarLayout.setSpacing(true);
		
		ButtonGroup editionButtonGroup = new ButtonGroup();
		toolbarLayout.addComponent(editionButtonGroup);
		
		// btnStartJob
		btnStartJob = new Button();
		btnStartJob.setCaption("Iniciar Job");
		btnStartJob.setImmediate(true);
		btnStartJob.setWidth("-1px");
		btnStartJob.setHeight("-1px");
		btnStartJob.setIcon(new ThemeResource("../konekti/images/icons/offerrequest-toolbar/paper-bag--exclamation.png"));
		
		editionButtonGroup.addButton(btnStartJob);
		
		// btnStopJob
		btnStopJob = new Button();
		btnStopJob.setCaption("Parar Job");
		btnStopJob.setImmediate(true);
		btnStopJob.setWidth("-1px");
		btnStopJob.setHeight("-1px");
		btnStopJob.setIcon(new ThemeResource("../konekti/images/icons/offerrequest-toolbar/paper-bag--arrow.png"));
		
		editionButtonGroup.addButton(btnStopJob);
				
		return toolbarLayout;
	}

	@Override
	protected void updateLabels() {
		btnStartJob.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.module.alarm.addon.JobToolbar.btnStartJob.caption"));
		btnStartJob.setDescription(getI18N().getMessage("com.thingtrack.konekti.view.module.alarm.addon.JobToolbar.btnStartJob.description"));
		btnStopJob.setCaption(getI18N().getMessage("com.thingtrack.konekti.view.module.alarm.addon.JobToolbar.btnStopJob.caption"));
		btnStopJob.setDescription(getI18N().getMessage("com.thingtrack.konekti.view.module.alarm.addon.JobToolbar.btnStopJob.description"));
		
	}
}
