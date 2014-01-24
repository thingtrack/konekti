package com.thingtrack.konekti.dao.api;

import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Attachment;

/**
 * Attachment Data Access Layer
 * <p>
 * @author Thingtrack S.L.
 *
 */
public interface AttachmentDao extends Dao<Attachment, Integer> {
	
	/**
	 * Obtains an {@link List<Attachment>} object found by its {@code entityName} and {@code entityId}
	 * 
	 * @param entityName  the unique name of the entity among the same type, not null
	 * @param entityId the unique identifier of the type of the type of identifier, not null
	 * @return {@code List<Attachment>} of the found {@link Attachment}, not null
	 * @throws Exception
	 */
	public List<Attachment> getAllByEntity( String entityName, Integer entityId ) throws Exception;
}
