package com.thingtrack.konekti.message.internal;

import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import com.thingtrack.konekti.domain.User;
import com.thingtrack.konekti.service.api.UserService;

public class MessageActivator implements BundleActivator {
	private BundleContext bundleContext;
	private UserService userService;
	
	private TaskExecutor taskExecutor;
	
	public void start(BundleContext context) throws Exception {
		this.bundleContext = context;
				
		// get business service
		getBusinessService();
		
		// create thread-pool for starting message broker and publish user topics
		taskExecutor = createDefaultTaskExecutor();
		
		// create Message Broker Runnable
		Runnable messageBrokerTask = new Runnable() {
			@Override
			public void run() {
				
			}
		};
		
		taskExecutor.execute(messageBrokerTask);
	}

	public void stop(BundleContext context) throws Exception {
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void getBusinessService() {
		ServiceReference userServiceReference = bundleContext.getServiceReference(UserService.class.getName());
		 
		if(userServiceReference != null)
			userService = (UserService)bundleContext.getService(userServiceReference);
	}
	
	private TaskExecutor createDefaultTaskExecutor() {
		ThreadGroup threadGroup = new ThreadGroup("konekti-message-broker-threads");
		
		SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
		taskExecutor.setThreadGroup(threadGroup);
		taskExecutor.setThreadNamePrefix("KonektiMessageBrokerThread-");
		
		return taskExecutor;
		
	}
}
