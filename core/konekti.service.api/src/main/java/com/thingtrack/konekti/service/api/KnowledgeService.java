package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Knowledge;
import com.thingtrack.konekti.domain.Organization;

public interface KnowledgeService {
	public List<Knowledge> getAll() throws Exception;
	public Knowledge get( Integer knowledgeSId ) throws Exception;
	public Knowledge save(Knowledge knowledge) throws Exception;
	public void delete(Knowledge knowledge) throws Exception;
	public Knowledge createNewEntity(Organization organization) throws Exception;
	public void setActive(Knowledge knowledge) throws Exception;
	public void setDesActive(Knowledge knowledge) throws Exception;
}
