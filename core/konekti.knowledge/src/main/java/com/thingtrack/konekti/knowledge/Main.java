package com.thingtrack.konekti.knowledge;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactoryService;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderConfiguration;
import org.drools.builder.KnowledgeBuilderFactoryService;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceChangeScannerConfiguration;
import org.drools.io.ResourceFactory;
import org.drools.io.ResourceFactoryService;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.util.ServiceRegistry;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import com.thingtrack.konekti.domain.Knowledge;
import com.thingtrack.konekti.service.api.KnowledgeService;

public class Main implements BundleActivator {
	private static KnowledgeService knowledgeService;
	
	private KnowledgeThread thread;
	
	private static ResourceFactoryService resourceFactoryService ;
	private static KnowledgeBuilderFactoryService knowledgeBuilderFactoryService;
	private static KnowledgeBase kbase;
	private static KnowledgeBaseFactoryService knowledgeBaseFactoryService;
	private static KnowledgeBaseConfiguration kbaseConf;
	private static KnowledgeBuilderConfiguration kbConf;
	private static KnowledgeBuilder kBuilder;
	
	public Main() {
		getServices();
		
	}
	
	public void start(BundleContext bundleContext) throws Exception {
		try 
		{
			ResourceChangeScannerConfiguration sconf = ResourceFactory.getResourceChangeScannerService().newResourceChangeScannerConfiguration();
			
			ResourceFactory.getResourceChangeScannerService().configure( sconf );			
			ResourceFactory.getResourceChangeScannerService().start(); 
            ResourceFactory.getResourceChangeNotifierService().start(); 
            
			// get the service registry from bundlecontext
			ServiceReference serviceRef = bundleContext.getServiceReference( ServiceRegistry.class.getName() );
			ServiceRegistry registry = (ServiceRegistry) bundleContext.getService( serviceRef );
	
			// get knowledge necesary services from service registry
			knowledgeBuilderFactoryService = registry.get( KnowledgeBuilderFactoryService.class );
			knowledgeBaseFactoryService = registry.get( KnowledgeBaseFactoryService.class );
			resourceFactoryService = registry.get( ResourceFactoryService.class );
			
			// get default knowledge configurations
			kbaseConf = knowledgeBaseFactoryService.newKnowledgeBaseConfiguration( null, getClass().getClassLoader() );	
			kbConf = knowledgeBuilderFactoryService.newKnowledgeBuilderConfiguration( null, getClass().getClassLoader() );
			kbase = knowledgeBaseFactoryService.newKnowledgeBase(kbaseConf);
			
			// fill the kBase with knowledge from DB
			configureKnowledge();
		    
			// create a Knowledge Session and export to OSGi Service Registry
			StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
										
			bundleContext.registerService(StatefulKnowledgeSession.class.getName(), ksession, null);
			
			
		}
		catch(Exception ex) {
			ex.getMessage();
		}
		
	}

	public void stop(BundleContext arg0) throws Exception {
		thread.interrupt();
		
	}
	
	private void configureKnowledge() {
		thread = new KnowledgeThread();
	    thread.start();
	    
	}

	private void getServices() {
		try {
			BundleContext bundleContext = FrameworkUtil.getBundle(Main.class).getBundleContext();
			
			ServiceReference knowledgeServiceReference = bundleContext.getServiceReference(KnowledgeService.class.getName());
			knowledgeService = KnowledgeService.class.cast(bundleContext.getService(knowledgeServiceReference));			
		
		}
		catch (Exception e) {
			e.getMessage();
			
		}
		
	}
	
	public static class KnowledgeThread extends Thread {
		public void run() {
			System.out.println("registering knowledge ...");
			    
			try {			
				// get all knowledge from DB				;
				List<Knowledge> knowledges = knowledgeService.getAll();
					
				// build all knowledge and check errors				
				for(Knowledge knowledge : knowledges) {					
					if (knowledge.getActive()) {
						kBuilder = knowledgeBuilderFactoryService.newKnowledgeBuilder();
						kBuilder.add(resourceFactoryService.newInputStreamResource(new ByteArrayInputStream(knowledge.getFile())), ResourceType.BPMN2);
						
						// add to the knowledge base
						if(!kBuilder.hasErrors()) {
							knowledge.setError(false);
							knowledge.setErrorMessage(null);							
						}
						else {
							knowledge.setError(true);
							if (kBuilder.getErrors().toString().length() > 1024)
								knowledge.setErrorMessage(kBuilder.getErrors().toString().substring(0, 1023)); // take 1024 error caracters
							else
								knowledge.setErrorMessage(kBuilder.getErrors().toString());
							
						}
						
						knowledgeService.save(knowledge);

					}
					
				}
									
				// create a Knowledge Base with the knowledge packages without errors
				for(Knowledge knowledge : knowledges) {
					if (knowledge.getActive() && !knowledge.getError())
						kbase.addKnowledgePackages(kBuilder.getKnowledgePackages());
					
				}
																	
			} catch (Exception ex) {
			    	ex.printStackTrace();
			    	
			}
			 	    
		    System.out.println("knowledge registered ...");
		    
		}
		 
	 }
	 
}
