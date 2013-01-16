package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Attachment;

public interface AttachmentService {
	public List<Attachment> getAll() throws Exception;
	public Attachment get( Integer attachmentId ) throws Exception;
	public List<Attachment> getAllByEntity( String entityName, Integer entityId ) throws Exception;
	public Attachment save(Attachment attachment) throws Exception;
	public void delete(Attachment attachment) throws Exception;
}
