package com.thingtrack.konekti.view.kernel.ui.layout;

public class ViewEvent {
   	private IView sourceFrom;
	private IView sourceTo;
	
    /**
     * New instance of selected view change view event
     * 
     * @param source the Source of the event.
     */
    public ViewEvent(IView sourceFrom, IView sourceTo) {
        this.sourceFrom = sourceFrom;
        this.sourceTo = sourceTo;
    }

    /**
     * view from where the event occurred.
     * 
     * @return the Source of the event.
     */
    public IView getViewFrom() {
        return this.sourceFrom;
        
    }
    
    /**
     * slide to where the event occurred.
     * 
     * @return the Source of the event.
     */
    public IView getViewTo() {
        return this.sourceTo;
        
    }
}
