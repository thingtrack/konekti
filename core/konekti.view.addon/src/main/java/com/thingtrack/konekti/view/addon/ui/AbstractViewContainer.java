package com.thingtrack.konekti.view.addon.ui;

import java.util.HashMap;
import java.util.Map.Entry;

import com.thingtrack.konekti.view.kernel.ui.layout.IView;
import com.thingtrack.konekti.view.kernel.ui.layout.IViewContainer;
import com.vaadin.ui.CustomComponent;

@SuppressWarnings("serial")
public abstract class AbstractViewContainer extends CustomComponent implements IViewContainer {
	protected HashMap<Integer, AbstractView> views = new HashMap<Integer, AbstractView>();
	
	@Override
	public IView getNext() {		
		Integer index = getKeyByValue((AbstractView) getSelectedView());
		
		if (index >= views.keySet().size()) 
			return (AbstractView) getSelectedView();
		
		return views.get(index + 1);
	}
	
	@Override
	public IView getPrevious() {
		Integer index = getKeyByValue((AbstractView) getSelectedView());
		
		if (index <= 0) 
			return (AbstractView) getSelectedView();
		
		return views.get(index - 1);
	}
	
	private Integer getKeyByValue(AbstractView value) {
	    for (Entry<java.lang.Integer, com.thingtrack.konekti.view.addon.ui.AbstractView> entry : views.entrySet()) {
	        if (value.equals(entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    
	    return null;
	}

}
