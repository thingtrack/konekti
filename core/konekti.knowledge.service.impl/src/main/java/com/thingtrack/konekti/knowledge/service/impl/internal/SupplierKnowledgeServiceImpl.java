package com.thingtrack.konekti.knowledge.service.impl.internal;

import java.util.HashMap;
import java.util.List;

import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thingtrack.konekti.domain.Supplier;
import com.thingtrack.konekti.knowledge.service.api.SupplierKnowledgeService;
import com.thingtrack.konekti.service.api.SupplierService;

public class SupplierKnowledgeServiceImpl implements SupplierKnowledgeService {
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private StatefulKnowledgeSession statefulKnowledgeSession;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = javax.persistence.NoResultException.class)
	public void importSuppliers(List<Supplier> suppliers) throws Exception {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("suppliers", suppliers);
		params.put("supplierService", supplierService);
		
		// execute BP
		statefulKnowledgeSession.startProcess("com.konekti.knowledge.bpmn2.bpimportsupplier", params);
		statefulKnowledgeSession.dispose();	
			
	}

}
