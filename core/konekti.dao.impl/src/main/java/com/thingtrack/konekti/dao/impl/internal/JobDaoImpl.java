package com.thingtrack.konekti.dao.impl.internal;

import com.thingtrack.konekti.dao.api.JobDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.Job;

public class JobDaoImpl extends JpaDao<Job, Integer> implements JobDao {

	@Override
	public Job getByGroupName(String group, String name) throws Exception {
		Job job = (Job)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.jobGroup = :group AND p.jobName = :name")
				.setParameter("group", group)
				.setParameter("name", name).getSingleResult();

		return job;
	}

}
