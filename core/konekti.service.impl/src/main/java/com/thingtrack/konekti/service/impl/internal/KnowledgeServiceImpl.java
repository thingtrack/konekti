package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.KnowledgeDao;
import com.thingtrack.konekti.domain.Knowledge;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.service.api.KnowledgeService;

public class KnowledgeServiceImpl implements KnowledgeService {
	@Autowired
	private  KnowledgeDao knowledgeDao;
	
	@Override
	public List<Knowledge> getAll() throws Exception {
		return this.knowledgeDao.getAll();
	}

	@Override
	public Knowledge get(Integer knowledgeId) throws Exception {
		return knowledgeDao.get(knowledgeId);
	}
	
	@Override
	public Knowledge save(Knowledge knowledge) throws Exception {
		return this.knowledgeDao.save(knowledge);
	}

	@Override
	public void delete(Knowledge knowledge) throws Exception {
		this.knowledgeDao.delete(knowledge);
		
	}

	@Override
	public Knowledge createNewEntity(Organization organization) throws Exception {
		Knowledge knowledge = new Knowledge();
		
		knowledge.setOrganization(organization);			
		knowledge.setActive(true);
		
		return knowledge;
	}
	
	@Override
	public void setActive(Knowledge knowledge) throws Exception {
		knowledge.setActive(true);
						
		save(knowledge);
		
	}
	
	@Override
	public void setDesActive(Knowledge knowledge) throws Exception {
		knowledge.setActive(false);
						
		save(knowledge);
		
	}
}
