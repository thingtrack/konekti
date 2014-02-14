package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Sequence;

/**
 * Sequence Data Access Layer
 * <p>
 * @author Thingtrack S.L.
 *
 */
public interface SequenceDao extends Dao<Sequence, Integer> {
	/**
	 * The {@link Sequence} by its name
	 * @param name the unique name, not null
	 * @return @ {@link Sequence}
	 * @throws Exception
	 */
	public Sequence getByName(String name) throws Exception;
	
	/**
	 * The {@link Sequence} by its name
	 * @param name the unique name, not null
	 * @return @ {@link Sequence}
	 * @throws Exception
	 */
	public String setNextSequence(String code) throws Exception;
	
}
