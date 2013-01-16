package com.thingtrack.konekti.dao.impl.internal;

import java.util.List;

import javax.persistence.Query;

import com.thingtrack.konekti.dao.api.AttachmentDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.Attachment;

public class AttachmentDaoImpl extends JpaDao<Attachment, Integer> implements AttachmentDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Attachment> getAllByEntity( String entityName, Integer entityId ) throws Exception {
		String queryString = "SELECT at";
		queryString += " FROM Attachment at";
		queryString += " WHERE at.entityName = :entityName";
		queryString += " AND at.entityId = :entityId";

		Query query = (Query) getEntityManager().createQuery(queryString)
				.setParameter("entityName", entityName)
				.setParameter("entityId", entityId);

		return query.getResultList();

	}
}