package com.thingtrack.konekti.dao.api;

import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Attachment;

public interface AttachmentDao extends Dao<Attachment, Integer> {
	public List<Attachment> getAllByEntity( String entityName, Integer entityId ) throws Exception;
}
