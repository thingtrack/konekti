package com.thingtrack.konekti.knowledge.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.EmployeeAgent;
import com.thingtrack.konekti.domain.Organization;

public interface EmployeeAgentKnowledgeService {
	public void importEmployeeAgent(Organization organization, List<EmployeeAgent> employeeAgents) throws Exception;
}
