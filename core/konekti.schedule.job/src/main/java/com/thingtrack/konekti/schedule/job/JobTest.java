package com.thingtrack.konekti.schedule.job;

import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.thingtrack.konekti.domain.Alarm;
import com.thingtrack.konekti.domain.AlarmStatus;
import com.thingtrack.konekti.domain.AlarmType;
import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.schedule.JobApi;
import com.thingtrack.konekti.service.api.AlarmService;
import com.thingtrack.konekti.service.api.AlarmStatusService;
import com.thingtrack.konekti.service.api.AlarmTypeService;
import com.thingtrack.konekti.service.api.AreaService;

@Configurable
public class JobTest implements JobApi {
	private static final String JOB_NAME = "test";
	private static final String JOB_GROUP = "thingtrack";
	
	@Autowired(required=false)
	private AlarmService alarmService;

	@Autowired(required=false)
	private AlarmTypeService alarmTypeService;
	
	@Autowired(required=false)
	private AlarmStatusService alarmStatusService;
	
	@Autowired(required=false)
	private AreaService areaService;
	
    public JobTest() {
  	
    }

	@Override
	public String getName() {
		return JOB_NAME;
	}

	@Override
	public String getGroup() {
		return JOB_GROUP;
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {		
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();

	    Integer areaId = dataMap.getInt("areaId");
	    
		Area area = null;
		try {
			if (areaId != null)
				area = areaService.get(areaId);
		}
		catch (Exception e) {
			throw new JobExecutionException(e.getMessage());
		}
		
		AlarmType alarmType;
		
		try {
			alarmType = alarmTypeService.get(1);
		}
		catch (Exception e) {
			throw new JobExecutionException(e.getMessage());
		}
		
		AlarmStatus alarmStatus;
		
		try {
			alarmStatus = alarmStatusService.get(1);
		}
		catch (Exception e) {
			throw new JobExecutionException(e.getMessage());
		}
		
		Alarm alarm = new Alarm();
		
		alarm.setArea(area);
		alarm.setAlarmName(JOB_NAME);
		alarm.setAlarmGroup(JOB_GROUP);
		alarm.setAlarmType(alarmType);
		alarm.setAlarmStatus(alarmStatus);
		alarm.setAlarmDate(new Date());
		alarm.setMessage("Hello! Alarm Test is executing.");
			      
		try {
			alarmService.save(alarm);
		} catch (Exception e) {
			throw new JobExecutionException(e.getMessage());
		}
		
	}
	
}
