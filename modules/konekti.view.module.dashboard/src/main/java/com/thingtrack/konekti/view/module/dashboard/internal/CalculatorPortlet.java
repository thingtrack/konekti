package com.thingtrack.konekti.view.module.dashboard.internal;

import com.thingtrack.konekti.view.module.dashboard.internal.KonektiPortal.KonektiPortlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;

@SuppressWarnings("serial")
public class CalculatorPortlet extends KonektiPortlet implements ClickListener {
    // All variables are automatically stored in the session.
    private double current = 0.0;
    private double stored = 0.0;
    private char lastOperationRequested = 'C';
    
    // User interface components
    private final Label display = new Label("<font size=\"12\" color=\"blue\">0.0</font>");
    
    // Create the main layout for our application (4 columns, 5 rows)
    final GridLayout layout = new GridLayout(4, 5);
	
	public CalculatorPortlet(KonektiPortal konektiPortal) {
		konektiPortal.super();
		
		buildMainLayout();
	}
	
	@Override
	public void addComponent(Component c, int position) {
        super.addComponent(c, position);
        
    };
    
    // Event handler for button clicks. Called for all the buttons in the
    // application.
    public void buttonClick(ClickEvent event) {

        // Get the button that was clicked
        Button button = event.getButton();

        // Get the requested operation from the button caption
        //char requestedOperation = button.getCaption().charAt(0);
        char requestedOperation = button.getCaption().substring(4, 5).charAt(0);
        
        // Calculate the new value
        double newValue = calculate(requestedOperation);

        // Update the result label with the new value
        display.setValue("<font size=\"12\" color=\"blue\">" + newValue + "</font>");

    }

    // Calculator "business logic" implemented here to keep the example minimal
    private double calculate(char requestedOperation) {
        if ('0' <= requestedOperation && requestedOperation <= '9') {
            current = current * 10
                    + Double.parseDouble("" + requestedOperation);
            return current;
        }
        switch (lastOperationRequested) {
        case '+':
            stored += current;
            break;
        case '-':
            stored -= current;
            break;
        case '/':
            stored /= current;
            break;
        case '*':
            stored *= current;
            break;
        case 'C':
            stored = current;
            break;
        }
        lastOperationRequested = requestedOperation;
        current = 0.0;
        if (requestedOperation == 'C') {
            stored = 0.0;
        }
        return stored;
    }
    
    private void buildMainLayout() {
    	layout.setSizeFull();
    	
    	display.setContentMode(Label.CONTENT_XHTML);
    	
		// common part: create layout
    	// Create a result label that over all 4 columns in the first row
    	layout.setCaption("Calculadora");
    	layout.setSpacing(true);
        layout.addComponent(display, 0, 0, 3, 0);    
        layout.setComponentAlignment(display, Alignment.MIDDLE_LEFT);
                
        // The operations for the calculator in the order they appear on the
        // screen (left to right, top to bottom)
        String[] operations = new String[] { "<h1>7</h1>", "<h1>8</h1>", "<h1>9</h1>", "<h1>/</h1>", "<h1>4</h1>", "<h1>5</h1>", "<h1>6</h1>",
                "<h1>*</h1>", "<h1>1</h1>", "<h1>2</h1>", "<h1>3</h1>", "<h1>-</h1>", "<h1>0</h1>", "<h1>=</h1>", "<h1>C</h1>", "<h1>+</h1>" };

        for (String caption : operations) {

            // Create a button and use this application for event handling
        	NativeButton button = new NativeButton(caption);            
            button.setWidth("100%");
            button.setHeight("100%");
            button.setHtmlContentAllowed(true);
            button.addListener(this);
            
            // Add the button to our main layout
            layout.addComponent(button);
            layout.setComponentAlignment(button, Alignment.MIDDLE_CENTER);            
        }
    		
		// top-level component properties
		addComponent(layout);
		
	}
}
