package com.thingtrack.konekti.view.layout.toolbar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.thingtrack.konekti.view.kernel.ui.layout.IToolbarLayout;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;


@SuppressWarnings("serial")
public class ToolbarLayout extends CssLayout implements IToolbarLayout {

	
	public static final String TOOLBAR_LAYOUT_STYLE = "toolbar_layout";
	public static final String TOOLBAR_STYLE = "toolbar";
	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	//private float toolbarWidth = 0f; 
	//private float totalWidth = 0f;
	
	//private List<HorizontalLayout> dockHorizontalLayouts = new ArrayList<HorizontalLayout>();
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public ToolbarLayout() {
		
		super();
		
		buildMainLayout();
		
		// Enable dragging
		/*dockHorizontalLayout.setDragMode(LayoutDragMode.CLONE);
		
		// Enable dropping
		dockHorizontalLayout.setDropHandler(new DropHandler() {
			 public AcceptCriterion getAcceptCriterion() {
			    	// Only allow drops between components
			        return new Not(HorizontalLocationIs.CENTER);				 
			 }
			 
			 public void drop(DragAndDropEvent event) {
			    	// Get the transferable (We now the component comes from a layout so we can cast 
			        // it without checking)      
			        LayoutBoundTransferable transferable = (LayoutBoundTransferable) event
			                .getTransferable();

					// Get Horizontal layout specific target details
			        HorizontalLayoutTargetDetails details = (HorizontalLayoutTargetDetails) event
			                .getTargetDetails();

					// Get the dragged component
			        Component comp = transferable.getComponent();

					// Get the old and new indexes of the component
			        int currentIndex = dockHorizontalLayout.getComponentIndex(comp);
			        int newIndex = details.getOverIndex();

					// Drop the component
			        dockHorizontalLayout.removeComponent(comp);
			        if (currentIndex > newIndex
			                && details.getDropLocation() == HorizontalDropLocation.RIGHT) {
			            newIndex++;
			        }
			        dockHorizontalLayout.addComponent(comp, newIndex);
			    }
		});*/
	
		//dockHorizontalLayouts.add(dockHorizontalLayout);
		
	}

	@Override
	public void addToolbar(ComponentContainer toolbar) {
		toolbar.addStyleName(TOOLBAR_STYLE);
		this.addComponent(toolbar);

		
	}
	
	@Override
	public void addToolbar(ComponentContainer toolbar, int index) {
		toolbar.addStyleName(TOOLBAR_STYLE);
		this.addComponent(toolbar, index);
	}
	
	@Override
	public ComponentContainer[] getToolbars() {
		List<CustomComponent> result = new ArrayList<CustomComponent>();
		@SuppressWarnings("rawtypes")
		Iterator iterator = this.getComponentIterator();
		
		while(iterator.hasNext()){
			
			Component component = (Component) iterator.next();
			if(component instanceof CustomComponent){
				result.add((CustomComponent) component);
			}
		}
		
		CustomComponent[] resultToArray = new CustomComponent[result.size()];
		return result.toArray(resultToArray);
	}
	
	@Override
	public void removeToolbar(CustomComponent toolbar) {
		this.removeComponent(toolbar);
		
	}


	@AutoGenerated
	private void buildMainLayout() {
		setWidth("100%");
	}

	@Override
	public ComponentContainer getContent() {
		return this;
		
	}


}
