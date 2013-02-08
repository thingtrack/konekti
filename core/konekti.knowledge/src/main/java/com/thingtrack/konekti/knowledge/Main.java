package com.thingtrack.konekti.knowledge;

import java.util.Collection;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactoryService;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderConfiguration;
import org.drools.builder.KnowledgeBuilderFactoryService;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactoryService;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.util.ServiceRegistry;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Main implements BundleActivator {
	
	@SuppressWarnings("rawtypes")
	public void start(BundleContext arg0) throws Exception {
		try 
		{
			// get the service registry from bundlecontext
			ServiceReference serviceRef = arg0.getServiceReference( ServiceRegistry.class.getName() );
			@SuppressWarnings("unchecked")
			ServiceRegistry registry = (ServiceRegistry) arg0.getService( serviceRef );
	
			// get knowledge necesary services from service registry
			KnowledgeBuilderFactoryService knowledgeBuilderFactoryService = registry.get( KnowledgeBuilderFactoryService.class );
			KnowledgeBaseFactoryService knowledgeBaseFactoryService = registry.get( KnowledgeBaseFactoryService.class );
			ResourceFactoryService resourceFactoryService = registry.get( ResourceFactoryService.class );
			
			// create the knowledge base from BPM resources in bundle class resource path
			KnowledgeBaseConfiguration kbaseConf = knowledgeBaseFactoryService.newKnowledgeBaseConfiguration( null, getClass().getClassLoader() );	
			KnowledgeBuilderConfiguration kbConf = knowledgeBuilderFactoryService.newKnowledgeBuilderConfiguration( null, getClass().getClassLoader() );
			
			KnowledgeBuilder kbuilder = knowledgeBuilderFactoryService.newKnowledgeBuilder( kbConf );			
			//kbuilder.add(resourceFactoryService.newClassPathResource("TEST01.bpmn"), ResourceType.BPMN2);
			//kbuilder.add(resourceFactoryService.newClassPathResource("TEST02.bpmn"), ResourceType.BPMN2);
			//kbuilder.add(resourceFactoryService.newClassPathResource("TEST03.bpmn"), ResourceType.BPMN2);
			//kbuilder.add(resourceFactoryService.newClassPathResource("TEST04.rf"), ResourceType.BPMN2);

			kbuilder.add(resourceFactoryService.newClassPathResource("BPImportClient.bpmn"), ResourceType.BPMN2);
			kbuilder.add(resourceFactoryService.newClassPathResource("BPImportSupplier.bpmn"), ResourceType.BPMN2);
			kbuilder.add(resourceFactoryService.newClassPathResource("BPImportEmployeeAgent.bpmn"), ResourceType.BPMN2);
			kbuilder.add(resourceFactoryService.newClassPathResource("BPImportProduct.bpmn"), ResourceType.BPMN2);
			
			kbaseConf = knowledgeBaseFactoryService.newKnowledgeBaseConfiguration( null, getClass().getClassLoader() );
			KnowledgeBase kbase = knowledgeBaseFactoryService.newKnowledgeBase( kbaseConf );
			kbase.addKnowledgePackages( kbuilder.getKnowledgePackages());
	
			// create a state full knowledge session fron knowledge base
			StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
			
			// get Process loaded
			Collection<org.drools.definition.process.Process> processes = ksession.getKnowledgeBase().getProcesses();
			
			@SuppressWarnings("unused")
			String msg;
			if (processes.size() == 0)
				msg = "ERROR";
			
			// register the state full knowledge session
			arg0.registerService(StatefulKnowledgeSession.class.getName(), ksession, null);
			
		}
		catch(Exception ex) {
			ex.getMessage();
		}
		
	}

	public void stop(BundleContext arg0) throws Exception {
		// TODO Auto-generated method stub		
	}

}
