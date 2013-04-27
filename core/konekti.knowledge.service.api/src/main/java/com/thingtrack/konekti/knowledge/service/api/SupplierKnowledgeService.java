package com.thingtrack.konekti.knowledge.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Supplier;

public interface SupplierKnowledgeService {
	public void importSuppliers(List<Supplier> suppliers) throws Exception;
}
