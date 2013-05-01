package com.thingtrack.konekti.datasource;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.annotation.PostConstruct;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.springframework.beans.factory.annotation.Autowired;

public class ConnectionConfiguration {
	
	@Autowired
	ConfigurationAdmin configurationAdmin;
	
	private String driverClass;
	private String url;
	private String user;
	private String password;
	
	public ConnectionConfiguration(String driverClass, String url, String user, String password) {
		
		this.driverClass = driverClass;
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	@PostConstruct
	private void createConfiguration(){
		
		 // Create a factory config and set the factory pid to "gemini.jpa.punit" 
        try {
			Configuration config = configurationAdmin.createFactoryConfiguration("gemini.jpa.punit", null);
			
			// Config properties
	        Dictionary props = new Hashtable();
	 
	        // Must include the punit name
	        props.put("gemini.jpa.punit.name", "konekti");
	 
	        // Specify JDBC properties for this installation
	        props.put("javax.persistence.jdbc.driver", driverClass);
	        props.put("javax.persistence.jdbc.url", url);
	        props.put("javax.persistence.jdbc.user", user);
	        props.put("javax.persistence.jdbc.password", password);
	 
	        // Causes config to be created
	        config.update(props);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
