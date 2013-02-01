package com.thingtrack.konekti.domain.mobile.device;

import java.util.Date;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="capture")
public class CaptureDevice {
	
	@Attribute
	private Date time;
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
}
