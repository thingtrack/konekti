package com.thingtrack.konekti.knowledge.service.impl.internal;

import java.util.HashMap;
import java.util.List;

import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thingtrack.konekti.domain.EmployeeAgent;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.knowledge.service.api.EmployeeAgentKnowledgeService;
import com.thingtrack.konekti.service.api.EmployeeAgentService;

public class EmployeeAgentKnowledgeServiceImpl implements EmployeeAgentKnowledgeService {
	@Autowired
	private EmployeeAgentService employeeAgentService;
	
	@Autowired
	private StatefulKnowledgeSession statefulKnowledgeSession;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = javax.persistence.NoResultException.class)
	public void importEmployeeAgent(Organization organization, List<EmployeeAgent> employeeAgents) throws Exception {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("employeeAgents", employeeAgents);		
		params.put("employeeAgentService", employeeAgentService);
		params.put("organization", organization);	
		
		// execute BP
		statefulKnowledgeSession.startProcess("com.konekti.knowledge.bpmn2.bpimportemployeeagent", params);
		statefulKnowledgeSession.dispose();	
		
	}

}
