package com.thingtrack.konekti.filter.service.impl.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.template.DaoFilter;
import com.thingtrack.konekti.dao.api.AddressDao;
import com.thingtrack.konekti.dao.api.ClientDao;
import com.thingtrack.konekti.dao.api.ClientAgentDao;
import com.thingtrack.konekti.dao.api.ClientGroupDao;
import com.thingtrack.konekti.dao.api.ClientTypeDao;
import com.thingtrack.konekti.domain.Address;
import com.thingtrack.konekti.domain.Client;
import com.thingtrack.konekti.filter.service.api.FilterClientService;


public class FilterClientServiceImpl implements FilterClientService {
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private ClientTypeDao clientTypeDao;
	
	@Autowired
	private ClientGroupDao clientGroupDao;

	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private ClientAgentDao clientAgentDao;
	
	private List<DaoFilter> filters = new ArrayList<DaoFilter>();
	
	public DaoFilter getByAttributeName(String name) {
		for(DaoFilter daoFilter : filters) {
			if (daoFilter.getAttributeName() == name)
				return daoFilter;
			
		}
		
		return null;
	}
	
	@Override
	public List<DaoFilter> getFilters() throws Exception {		
		// get entity types
		EntityType<Client> clientEntityType = clientDao.getEntityType();
		
		// get primary attribute entity name
		SingularAttribute<? super Client, Integer> idClient = clientEntityType.getId(Integer.class);
		
		// create the filter collection
		for (Attribute<? super Client, ?> attribute: clientEntityType.getAttributes()) {
			 if (!attribute.isCollection()) {
				 if (attribute.getName().equals("clientType"))
					 filters.add(new DaoFilter(clientDao.getEntityName(), null, attribute.getName(), attribute.getJavaType(), clientTypeDao.getAll()));
				 else if (attribute.getName().equals("clientGroup"))					 
					 filters.add(new DaoFilter(clientDao.getEntityName(), null, attribute.getName(), attribute.getJavaType(), clientGroupDao.getAll()));
				 else if (attribute.getName().equals("clientAddress"))					 
					 filters.add(new DaoFilter(addressDao.getEntityName(), clientDao.getEntityName(), attribute.getName() + "." + addressDao.getEntityType().getAttribute("street").getName(), addressDao.getEntityType().getAttribute("street").getJavaType(), null));				 				 
				 else if (attribute.getName().equals("clientAgents"))					 
					 filters.add(new DaoFilter(clientDao.getEntityName(), null, attribute.getName(), attribute.getJavaType(), null));				 
				 else { 
					 if ((attribute.getJavaType().isPrimitive() ||
					      attribute.getJavaType().equals(String.class) ||
					      attribute.getJavaType().equals(Boolean.class) || 
						  attribute.getJavaType().equals(Character.class) ||
						  attribute.getJavaType().equals(Short.class) ||
						  attribute.getJavaType().equals(Integer.class) ||
						  attribute.getJavaType().equals(Long.class) ||
						  attribute.getJavaType().equals(Float.class) ||
						  attribute.getJavaType().equals(Double.class)) && attribute.getName() != idClient.getName())
						  	filters.add(new DaoFilter(clientDao.getEntityName(), null, attribute.getName(), attribute.getJavaType(), null));
				 }
			 }			 
			 
		}
		
		return filters;
	}

	@Override
	public List<Client> getFiltered(List<DaoFilter> filters) throws Exception {
		return clientDao.getFiltered(filters);
		
	}
}
