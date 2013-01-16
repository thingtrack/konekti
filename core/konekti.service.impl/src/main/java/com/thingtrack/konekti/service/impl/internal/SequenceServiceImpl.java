package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.SequenceDao;
import com.thingtrack.konekti.domain.Sequence;
import com.thingtrack.konekti.service.api.SequenceService;

public class SequenceServiceImpl implements SequenceService {
	@Autowired
	private SequenceDao sequenceDao;
	
	@Override
	public List<Sequence> getAll() throws Exception {
		return this.sequenceDao.getAll();
		
	}

	@Override
	public Sequence get(Integer sequenceId) throws Exception {
		return this.sequenceDao.get(sequenceId);
		
	}

	@Override
	public Sequence save(Sequence sequence) throws Exception {
		return this.sequenceDao.save(sequence);
		
	}

	@Override
	public void delete(Sequence sequence) throws Exception {
		this.sequenceDao.delete(sequence);
		
	}

	@Override
	public String setNextSequence(String code) throws Exception {
		return this.sequenceDao.setNextSequence(code);
		
	}
}
