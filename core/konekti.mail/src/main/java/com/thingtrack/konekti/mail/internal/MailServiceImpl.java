package com.thingtrack.konekti.mail.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.thingtrack.konekti.domain.Configuration;
import com.thingtrack.konekti.mail.MailService;
import com.thingtrack.konekti.service.api.ConfigurationService;

public class MailServiceImpl implements MailService {
	@Autowired
	private ConfigurationService configurationService;
	
	private List<Configuration> configurations;
	
	private final static String SMTP_HOST = "SMTP_HOST";
	private final static String SMTP_PORT = "SMTP_PORT";
	private final static String SMTP_USERNAME = "SMTP_USERNAME";
	private final static String SMTP_PASSEWORD = "SMTP_PASSEWORD";
	private final static String SMTP_SSL = "SMTP_SSL";
	private final static String SMTP_TLS = "SMTP_TLS";
	
	private String host;
	private int port;
	private String username;
	private String password;
	boolean sslFlag;
	private boolean tlsFlag;
	
	private JavaMailSenderImpl javaMailSender;
	private VelocityEngine velocityEngine = new VelocityEngine();
			
	@PostConstruct
	public void Initialize() throws Exception {
		// get all SMTP Server atributes 
		configurations = configurationService.getAll();
		
		host = getConfigurationValue(SMTP_HOST);
		port = Integer.parseInt(getConfigurationValue(SMTP_PORT));
		username = getConfigurationValue(SMTP_USERNAME);
		password = getConfigurationValue(SMTP_PASSEWORD);
		sslFlag = Boolean.parseBoolean(getConfigurationValue(SMTP_SSL));
		tlsFlag = Boolean.parseBoolean(getConfigurationValue(SMTP_TLS));
		
		// configure mail sender
		configureMailSender();
		
		// configure velocity engine
		Properties props = new Properties();
		props.put("resource.loader", "class");
		props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		
		velocityEngine.init(props);
	}
	
	private String getConfigurationValue(String tag) {
		for (Configuration configuration : configurations) {
			if (configuration.getTag().equals(tag))
				return configuration.getValue();
			
		}
		
		return null;
	}
	
	private void configureMailSender() {
		javaMailSender = new JavaMailSenderImpl();
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", tlsFlag);
		javaMailProperties.put("mail.smtp.ssl.enable", sslFlag);
		
		javaMailSender.setHost(host);
		javaMailSender.setPort(port);
		javaMailSender.setUsername(username);
		javaMailSender.setPassword(password);
		javaMailSender.setJavaMailProperties(javaMailProperties);
		
	}
		
	@Override
	public void sendMessage(String emailTo, String subject, String template, HashMap<String, Object> staticResources, HashMap<String, Object> dynamicResources) throws Exception {
		sendMessage(username, emailTo, subject, template, staticResources, dynamicResources);
				
	}
	
	@Override
	public void sendMessage(String emailFrom, String emailTo, String subject, String template, HashMap<String, Object> staticResources, HashMap<String, Object> dynamicResources) throws Exception {		
		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, dynamicResources);
		
        // configure mail helper
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true); // configure multipart email configuration: css and images inside
		helper.setFrom(emailFrom);
		helper.setTo(emailTo);
		helper.setSubject(subject);
		helper.setText(text, true); // configure html email
		
		Iterator<String> iter = staticResources.keySet().iterator();
		while(iter.hasNext()) {
			String key = (String)iter.next();
		    InputStreamSource val = (InputStreamSource)staticResources.get(key);
		    
		    helper.addInline(key, val, "text/html");
		}
		
		// send message in OSGi envirotment
        ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
        try {
	        Thread.currentThread().setContextClassLoader(javax.mail.Session.class.getClassLoader());
			javaMailSender.send(message);
        }
		catch (Exception e) {
			throw new Exception(e);
		} finally {
			Thread.currentThread().setContextClassLoader(oldClassLoader);
		}
	}
}
