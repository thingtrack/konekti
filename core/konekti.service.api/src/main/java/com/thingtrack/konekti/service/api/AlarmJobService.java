package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.AlarmJob;

public interface AlarmJobService {
	public List<AlarmJob> getAll() throws Exception;
	public AlarmJob get( Integer alarmJobId ) throws Exception;
	public AlarmJob save(AlarmJob alarmJob) throws Exception;
	public void delete(AlarmJob alarmJob) throws Exception;
	public AlarmJob getByGroupName(String group, String name) throws Exception;
	public void setLastExecution(AlarmJob alarmJob) throws Exception;
	public void setLastExecution(AlarmJob alarmJob, Boolean error) throws Exception;
	public void setOkStatus(AlarmJob alarmJob) throws Exception;
	public void setErrorStatus(AlarmJob alarmJob) throws Exception;
}
