package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Sequence;

public interface SequenceDao extends Dao<Sequence, Integer> {
	public Sequence getByName(String name) throws Exception;
	public String setNextSequence(String code) throws Exception;
	
}
