package com.thingtrack.konekti.message.internal;

import org.springframework.beans.factory.annotation.Autowired;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.thingtrack.konekti.domain.Client;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.message.internal.domain.csv.client.CsvClient;
import com.thingtrack.konekti.service.api.ClientGroupService;
import com.thingtrack.konekti.service.api.ClientService;
import com.thingtrack.konekti.service.api.ClientTypeService;
import com.thingtrack.konekti.service.api.OrganizationService;

public class ClientParser implements Processor {
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private ClientService clientService;

	@Autowired
	private ClientTypeService clientTypeService;
	
	@Autowired
	private ClientGroupService clientGroupService;
	
	public void process(Exchange exchange) throws Exception {
    	CsvClient csvClient = (CsvClient)exchange.getIn().getBody();
    	
    	 // C: Create client
		 // U: Update client
		 // D: Delete client
		 if (csvClient.getAction().equals("C")) {
			 clientService.save(parse(csvClient));
		 }
		 else if (csvClient.getAction().equals("U")) {
			 clientService.save(parse(csvClient));
		 }
		 else if (csvClient.getAction().equals("D")) {
			 Client client = parse(csvClient);
			 client.setActive(false);
			 
			 clientService.save(client);
		 }	
    }
    
    private Client parse(CsvClient csvClient) throws Exception {
    	// get client organization
    	Organization organization = organizationService.getByCode(csvClient.getOrganizationCode());
    	
    	// create new client entity
    	Client client = clientService.createNewClient(organization);
    	    	
    	// client setters
    	client.setCode(csvClient.getCode());    	
    	client.setDescription(csvClient.getDescription());
    	client.setEmail(csvClient.getEmail());
		client.setFacebookId(csvClient.getFacebookId());
		client.setFax(csvClient.getFax());
    	client.setComment(csvClient.getComment());
    	client.setMobile(csvClient.getMobile());
    	client.setOrganization(organization);
    	
    	client.setClientType(clientTypeService.getByCode(csvClient.getTypeCode()));
    	client.setClientGroup(clientGroupService.getByName(csvClient.getGroupName()));
    	client.setVat(csvClient.getVat());
    	
    	//client.setAddress(address);
    	
    	return client;
    	
    }
}

