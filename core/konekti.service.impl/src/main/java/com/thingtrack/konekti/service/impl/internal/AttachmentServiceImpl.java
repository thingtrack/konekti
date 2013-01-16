package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.AttachmentDao;
import com.thingtrack.konekti.domain.Attachment;
import com.thingtrack.konekti.service.api.AttachmentService;

public class AttachmentServiceImpl  implements AttachmentService {
	@Autowired
	private AttachmentDao attachmentDao;
	
	@Override
	public List<Attachment> getAll() throws Exception {
		return attachmentDao.getAll();
	}

	@Override
	public Attachment get(Integer attachmentId) throws Exception {
		return attachmentDao.get(attachmentId);
		
	}
	
	@Override
	public Attachment save(Attachment attachment) throws Exception {
		return attachmentDao.save(attachment);
	}

	@Override
	public void delete(Attachment attachment) throws Exception {
		attachmentDao.delete(attachment);
		
	}

	@Override
	public List<Attachment> getAllByEntity(String entityName, Integer entityId) throws Exception {
		return attachmentDao.getAllByEntity(entityName, entityId);
		
	}

}
