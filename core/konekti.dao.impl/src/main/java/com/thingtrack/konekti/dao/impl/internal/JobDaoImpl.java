package com.thingtrack.konekti.dao.impl.internal;

import java.util.List;

import javax.persistence.Query;

import com.thingtrack.konekti.dao.api.JobDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.Job;
import com.thingtrack.konekti.domain.User;

public class JobDaoImpl extends JpaDao<Job, Integer> implements JobDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> getAll(User user) throws Exception {
		StringBuffer queryString = new StringBuffer("SELECT p FROM " + getEntityName() + " p");

		if (user.getActiveArea() != null)
			queryString.append(" WHERE p.area = :area");

		Query query = (Query) getEntityManager().createQuery(queryString.toString());
		
		if (user.getActiveArea() != null)
			query.setParameter("area", user.getActiveArea());
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Job> getByGroupName(String group, String name) throws Exception {
		StringBuffer queryString = new StringBuffer("SELECT p FROM " + getEntityName() + " p");
		queryString.append(" WHERE p.jobGroup = :group");
		queryString.append(" AND p.jobName = :name");
		
		Query query = (Query) getEntityManager().createQuery(queryString.toString());
		
		query.setParameter("group", group);
		query.setParameter("name", name);
		
		return query.getResultList();
	
	}

	@Override
	public Job getByGroupNameAndArea(Integer areaId, String group, String name) throws Exception {
		StringBuffer queryString = new StringBuffer("SELECT p FROM " + getEntityName() + " p");
		queryString.append(" WHERE p.jobGroup = :group");
		queryString.append(" AND p.jobName = :name");
		queryString.append(" AND p.area.areaId = :areaId");

		Query query = (Query) getEntityManager().createQuery(queryString.toString());
		
		query.setParameter("group", group);
		query.setParameter("name", name);
		query.setParameter("areaId", areaId);
		
		return (Job) query.getSingleResult();
	}
}
