package com.thingtrack.konekti.knowledge.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Product;

public interface ProductKnowledgeService {
	public void importProducts(List<Product> products) throws Exception;
}
