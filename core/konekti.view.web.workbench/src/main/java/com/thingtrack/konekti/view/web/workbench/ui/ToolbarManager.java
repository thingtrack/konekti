package com.thingtrack.konekti.view.web.workbench.ui;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.view.kernel.IToolbarManager;
import com.thingtrack.konekti.view.kernel.ui.layout.IModuleChangeListener;
import com.thingtrack.konekti.view.kernel.ui.layout.IModuleCloseListener;
import com.thingtrack.konekti.view.kernel.ui.layout.IToolbar;
import com.thingtrack.konekti.view.kernel.ui.layout.IViewChangeListener;
import com.thingtrack.konekti.view.kernel.ui.layout.ModuleEvent;
import com.thingtrack.konekti.view.kernel.ui.layout.ViewEvent;
import com.thingtrack.konekti.view.layout.KonektiLayout;
import com.vaadin.ui.ComponentContainer;


public class ToolbarManager implements IModuleChangeListener,
		IModuleCloseListener, IViewChangeListener, IToolbarManager {
	@Autowired
	private KonektiLayout konektiLayout;

	@PostConstruct
	public void Configure() {
		// define close module listener
		konektiLayout.getWorkbenchLayout().getTopPanelWorkbench()
				.addListener((IModuleCloseListener) this);
		konektiLayout.getWorkbenchLayout().getLeftPanelWorkbench()
				.addListener((IModuleCloseListener) this);
		konektiLayout.getWorkbenchLayout().getCentralPanelWorkbench()
				.addListener((IModuleCloseListener) this);
		konektiLayout.getWorkbenchLayout().getRightPanelWorkbench()
				.addListener((IModuleCloseListener) this);
		konektiLayout.getWorkbenchLayout().getBottonPanelWorkbench()
				.addListener((IModuleCloseListener) this);

		// define change module listener
		konektiLayout.getWorkbenchLayout().getTopPanelWorkbench()
				.addListener((IModuleChangeListener) this);
		konektiLayout.getWorkbenchLayout().getLeftPanelWorkbench()
				.addListener((IModuleChangeListener) this);
		konektiLayout.getWorkbenchLayout().getCentralPanelWorkbench()
				.addListener((IModuleChangeListener) this);
		konektiLayout.getWorkbenchLayout().getRightPanelWorkbench()
				.addListener((IModuleChangeListener) this);
		konektiLayout.getWorkbenchLayout().getBottonPanelWorkbench()
				.addListener((IModuleChangeListener) this);

	}

	@Override
	public void viewChanged(ViewEvent event) {
		konektiLayout.getToolbarLayout().getContent().removeAllComponents();
		
		if (event.getViewTo() != null)
			addToolbars(event.getViewTo().getToolbars());

	}

	@Override
	public void moduleClose(ModuleEvent event) {
		konektiLayout.getToolbarLayout().getContent().removeAllComponents();
		
		if (event.getViewComponent() != null)
			addToolbars(event.getViewComponent().getSelectedView().getToolbars());
	}

	@Override
	public void moduleChange(ModuleEvent event) {
		konektiLayout.getToolbarLayout().getContent().removeAllComponents();
		
		if (event.getViewComponent() != null)
			addToolbars(event.getViewComponent().getSelectedView().getToolbars());
	}


	public void addToolbars(List<IToolbar> toolbars) {
		konektiLayout.getToolbarLayout().getContent().removeAllComponents();

		Collections.sort(toolbars, new Comparator<IToolbar>() {
			@Override
			public int compare(IToolbar p1, IToolbar p2) {
				return (p1.getPosition() < p2.getPosition()) ? -1 : (p1
						.getPosition() > p2.getPosition()) ? 1 : 0;

			}

		});

		for (IToolbar toolbar : toolbars) {
			konektiLayout.getToolbarLayout().addToolbar((ComponentContainer) toolbar);
		}
	}
}
