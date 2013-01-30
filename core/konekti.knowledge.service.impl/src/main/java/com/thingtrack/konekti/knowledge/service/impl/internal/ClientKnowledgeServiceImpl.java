package com.thingtrack.konekti.knowledge.service.impl.internal;

import java.util.HashMap;
import java.util.List;

import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thingtrack.konekti.domain.Client;
import com.thingtrack.konekti.knowledge.service.api.ClientKnowledgeService;
import com.thingtrack.konekti.service.api.ClientService;

public class ClientKnowledgeServiceImpl implements ClientKnowledgeService {
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private StatefulKnowledgeSession statefulKnowledgeSession;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = javax.persistence.NoResultException.class)
	public void importClients(List<Client> clients) throws Exception {
		/*for (Client item : clients) {
			Client client = null;
			try {
				client = clientService.getByCode(item.getCode());
			}
			catch(javax.persistence.NoResultException ex) {
				client = null;
			}
			
			if (client == null)
				clientService.save(item);			
			else {
				client.setName(item.getName());
				client.setDescription(item.getDescription());
				client.setPhone(item.getPhone());
				client.setFax(item.getFax());
				client.setMobile(item.getMobile());
				client.setEmail(item.getEmail());
				client.setClientAddress(item.getClientAddress());
				client.setVat(item.getVat());
				client.setClientType(item.getClientType());
				client.setClientGroup(item.getClientGroup());
				client.setComment(item.getComment());
				
				clientService.save(client);
			}
		}*/
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("clients", clients);
		params.put("clientService", clientService);
		
		// execute BP
		statefulKnowledgeSession.startProcess("com.konekti.knowledge.bpmn2.bpimportclient", params);
		statefulKnowledgeSession.dispose();
			
	}

}
