package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.AlarmTriggerTypeDao;
import com.thingtrack.konekti.domain.AlarmTriggerType;
import com.thingtrack.konekti.service.api.AlarmTriggerTypeService;

public class AlarmTriggerTypeServiceImpl implements AlarmTriggerTypeService {
	@Autowired
	private  AlarmTriggerTypeDao alarmTriggerTypeDao;
	
	@Override
	public List<AlarmTriggerType> getAll() throws Exception {
		return this.alarmTriggerTypeDao.getAll();
	}

	@Override
	public AlarmTriggerType get(Integer alarmTriggerTypeId) throws Exception {
		return alarmTriggerTypeDao.get(alarmTriggerTypeId);
	}

	@Override
	public AlarmTriggerType getByCode(String code) throws Exception {
		return alarmTriggerTypeDao.getByCode(code);
	}

	@Override
	public AlarmTriggerType save(AlarmTriggerType alarmTriggerType) throws Exception {
		return this.alarmTriggerTypeDao.save(alarmTriggerType);
	}

	@Override
	public void delete(AlarmTriggerType alarmTriggerType) throws Exception {
		this.alarmTriggerTypeDao.delete(alarmTriggerType);
		
	}

}
