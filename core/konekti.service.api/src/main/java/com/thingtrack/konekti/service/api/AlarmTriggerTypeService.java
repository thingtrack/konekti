package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.AlarmTriggerType;

public interface AlarmTriggerTypeService {
	public List<AlarmTriggerType> getAll() throws Exception;
	public AlarmTriggerType get( Integer alarmTriggerTypeId ) throws Exception;
	public AlarmTriggerType getByCode( String code ) throws Exception;
	public AlarmTriggerType save(AlarmTriggerType alarmTriggerType) throws Exception;
	public void delete(AlarmTriggerType alarmTriggerType) throws Exception;
}
