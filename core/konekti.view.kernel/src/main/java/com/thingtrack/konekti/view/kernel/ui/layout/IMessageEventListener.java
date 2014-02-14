package com.thingtrack.konekti.view.kernel.ui.layout;

import java.util.Date;

import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.domain.Location;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.User;

public interface IMessageEventListener {
	public void getMessage(Organization organizationFrom, 
				           Location locationFrom, 
				           Area areaFrom,
				           User userFrom,
				           Organization organizationTo, 
				           Location locationTo, 
				           Area areaTo, 			                
				           User userTo, 
				           String payload, 
				           Date messageDate);
}
