package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.WorkshopDao;
import com.thingtrack.konekti.domain.Location;
import com.thingtrack.konekti.domain.Sequence;
import com.thingtrack.konekti.domain.Workshop;
import com.thingtrack.konekti.service.api.SequenceService;
import com.thingtrack.konekti.service.api.WorkshopService;

/**
 * @author Thingtrack S.L.
 *
 */
public class WorkshopServiceImpl implements WorkshopService {
	@Autowired
	private WorkshopDao workshopDao;

	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public List<Workshop> getAll() throws Exception {
		return this.workshopDao.getAll();
		
	}

	@Override
	public Workshop get(Integer workshopId) throws Exception {
		return this.workshopDao.get(workshopId);
		
	}

	@Override
	public Workshop getByCode(String code) throws Exception {
		return this.workshopDao.getByCode(code);
		
	}

	@Override
	public Workshop save(Workshop workshop) throws Exception {
		return this.workshopDao.save(workshop);
		
	}

	@Override
	public void delete(Workshop workshop) throws Exception {
		this.workshopDao.delete(workshop);
		
	}

	@Override
	public Workshop createEntity(Location location) throws Exception {
		Workshop workshop = new Workshop();
		
		workshop.setCode(sequenceService.setNextSequence(Sequence.CODE.WORKSHOP.name()));
		workshop.setLocation(location);	
		workshop.setActive(true);
		
		return workshop;
	}
	
}
