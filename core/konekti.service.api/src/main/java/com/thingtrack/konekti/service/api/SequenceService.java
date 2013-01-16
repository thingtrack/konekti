package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Sequence;

/**
 * @author Thingtrack S.L.
 *
 */
public interface SequenceService {
	public List<Sequence> getAll() throws Exception;
	public Sequence get( Integer sequenceId ) throws Exception;
	public Sequence save(Sequence sequence) throws Exception;
	public void delete(Sequence sequence) throws Exception;
	public String setNextSequence(String code) throws Exception;

}