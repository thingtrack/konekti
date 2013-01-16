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

import com.thingtrack.konekti.service.api.OfferService;

public class Main implements BundleActivator {

	public static OfferService offerService;
    
	public void setOfferService(OfferService offerService) {
		Main.offerService = offerService;
		
	}
	
	public void start(BundleContext arg0) throws Exception {
		try 
		{
			// get the service registry from bundlecontext
			ServiceReference serviceRef = arg0.getServiceReference( ServiceRegistry.class.getName() );
			ServiceRegistry registry = (ServiceRegistry) arg0.getService( serviceRef );
	
			// get knowledge necesary services from service registry
			KnowledgeBuilderFactoryService knowledgeBuilderFactoryService = registry.get( KnowledgeBuilderFactoryService.class );
			KnowledgeBaseFactoryService knowledgeBaseFactoryService = registry.get( KnowledgeBaseFactoryService.class );
			ResourceFactoryService resourceFactoryService = registry.get( ResourceFactoryService.class );
			
			// create the knowledge base from BPM resources in bundle class resource path
			KnowledgeBaseConfiguration kbaseConf = knowledgeBaseFactoryService.newKnowledgeBaseConfiguration( null, getClass().getClassLoader() );	
			KnowledgeBuilderConfiguration kbConf = knowledgeBuilderFactoryService.newKnowledgeBuilderConfiguration( null, getClass().getClassLoader() );
			
			KnowledgeBuilder kbuilder = knowledgeBuilderFactoryService.newKnowledgeBuilder( kbConf );			
			kbuilder.add(resourceFactoryService.newClassPathResource("TEST01.bpmn"), ResourceType.BPMN2);
			kbuilder.add(resourceFactoryService.newClassPathResource("TEST02.bpmn"), ResourceType.BPMN2);
			kbuilder.add(resourceFactoryService.newClassPathResource("TEST03.bpmn"), ResourceType.BPMN2);
			kbuilder.add(resourceFactoryService.newClassPathResource("TEST04.rf"), ResourceType.BPMN2);
			//kbuilder.add(resourceFactoryService.newClassPathResource("PendingOfferRequestHead.rf"), ResourceType.BPMN2);
			//kbuilder.add(resourceFactoryService.newClassPathResource("ReOpenOfferRequestHead.rf"), ResourceType.BPMN2);
			//kbuilder.add(resourceFactoryService.newClassPathResource("TransferOfferRequestHead.rf"), ResourceType.BPMN2);
			//kbuilder.add(resourceFactoryService.newClassPathResource("RejectOfferRequestHead.rf"), ResourceType.BPMN2);
			
			kbuilder.add(resourceFactoryService.newClassPathResource("BPPendingOfferRequest.bpmn"), ResourceType.BPMN2);
			kbuilder.add(resourceFactoryService.newClassPathResource("BPReOpenOfferRequest.bpmn"), ResourceType.BPMN2);
			kbuilder.add(resourceFactoryService.newClassPathResource("BPRejectOfferRequest.bpmn"), ResourceType.BPMN2);
			kbuilder.add(resourceFactoryService.newClassPathResource("BPTransferOfferRequest.bpmn"), ResourceType.BPMN2);
			
			kbaseConf = knowledgeBaseFactoryService.newKnowledgeBaseConfiguration( null, getClass().getClassLoader() );
			KnowledgeBase kbase = knowledgeBaseFactoryService.newKnowledgeBase( kbaseConf );
			kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
	
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
		catch(Exception ex)
		{
			@SuppressWarnings("unused")
			String xxx = ex.getMessage();
		}
		
	}

	public void stop(BundleContext arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
