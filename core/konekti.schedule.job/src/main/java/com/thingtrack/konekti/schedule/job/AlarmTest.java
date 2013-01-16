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
import com.thingtrack.konekti.domain.Location;
import com.thingtrack.konekti.schedule.AlarmJobApi;
import com.thingtrack.konekti.service.api.AlarmService;
import com.thingtrack.konekti.service.api.AlarmStatusService;
import com.thingtrack.konekti.service.api.AlarmTypeService;
import com.thingtrack.konekti.service.api.LocationService;

@Configurable
public class AlarmTest implements AlarmJobApi {
	private static final String ALARM_JOB_NAME = "test";
	private static final String ALARM_JOB_GROUP = "thingtrack";
	
	@Autowired(required=false)
	private AlarmService alarmService;

	@Autowired(required=false)
	private AlarmTypeService alarmTypeService;
	
	@Autowired(required=false)
	private AlarmStatusService alarmStatusService;
	
	@Autowired(required=false)
	private LocationService locationService;
	
    public AlarmTest() {
  	
    }

	@Override
	public String getName() {
		return ALARM_JOB_NAME;
	}

	@Override
	public String getGroup() {
		return ALARM_JOB_GROUP;
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Location location;
		
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();

	    Integer locationId = dataMap.getInt("locationId");
	    
		try {
			if (locationId != null)
				location = locationService.get(locationId);				
			else
				location = locationService.get(1);
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
		
		alarm.setLocation(location);
		alarm.setAlarmName(ALARM_JOB_NAME);
		alarm.setAlarmGroup(ALARM_JOB_GROUP);
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
