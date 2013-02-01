package com.thingtrack.konekti.domain.mobile.js;

import com.thingtrack.konekti.domain.mobile.device.Context;

public abstract class JsInterface {
	private Context context;
	private boolean active=true;
	
	public JsInterface (Context context) {
        this.setContext(context);
        
    }

	/**
	 * @param context the context to set
	 */
	public void setContext(Context context) {
		this.context = context;
	}

	/**
	 * @return the context
	 */
	public Context getContext() {
		return context;
	}

	public boolean isActive() {
		return active;
		
	}
}
