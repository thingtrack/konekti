package com.thingtrack.konekti.knowledge.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Client;

public interface ClientKnowledgeService {
	public void importClients(List<Client> clients) throws Exception;
}
