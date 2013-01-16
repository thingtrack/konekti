package com.thingtrack.konekti.view.kernel.internal;

@SuppressWarnings("serial")
public class KernelException extends Exception {

	public static final String COMPONENT_NOT_FOUND = "Component not found";
	public static final String NULL_PARAMETER = "null value: ";
	public static final String NO_WORKBENCH = "Workbench is not created";
	
	private String message;
	
	public KernelException(String message) {
		super(message);
		
	}
	
	public String getMessage() {
		return message;
	}

}
