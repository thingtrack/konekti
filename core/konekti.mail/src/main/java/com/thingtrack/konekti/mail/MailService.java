package com.thingtrack.konekti.mail;

import java.util.HashMap;

public interface MailService {
	public void sendMessage(String emailTo, String subject, String template, HashMap<String, Object> staticResources, HashMap<String, Object> dynamicResources) throws Exception;
	
}
