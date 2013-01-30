package com.thingtrack.konekti.knowledge.service.impl.internal;

import java.util.HashMap;
import java.util.List;

import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thingtrack.konekti.domain.Product;
import com.thingtrack.konekti.knowledge.service.api.ProductKnowledgeService;
import com.thingtrack.konekti.service.api.ProductService;

public class ProductKnowledgeServiceImpl implements ProductKnowledgeService {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private StatefulKnowledgeSession statefulKnowledgeSession;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = javax.persistence.NoResultException.class)
	public void importProducts(List<Product> products) throws Exception {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("products", products);
		params.put("productService", productService);
		
		// execute BP
		statefulKnowledgeSession.startProcess("com.konekti.knowledge.bpmn2.bpimportproduct", params);
		statefulKnowledgeSession.dispose();
			
	}

}
