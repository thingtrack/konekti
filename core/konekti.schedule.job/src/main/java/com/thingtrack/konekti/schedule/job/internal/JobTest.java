package com.thingtrack.konekti.schedule.job.internal;

import java.util.Date;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.thingtrack.konekti.domain.Alarm;
import com.thingtrack.konekti.domain.AlarmStatus;
import com.thingtrack.konekti.domain.AlarmType;
import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.schedule.JobApi;
import com.thingtrack.konekti.service.api.AlarmService;
import com.thingtrack.konekti.service.api.AlarmStatusService;
import com.thingtrack.konekti.service.api.AlarmTypeService;
import com.thingtrack.konekti.service.api.AreaService;

//@Configurable
public class JobTest implements JobApi {
	private static final String JOB_NAME = "test";
	private static final String JOB_GROUP = "konekti";

	private AlarmService alarmService;

	//@Autowired(required=false)
	private AlarmTypeService alarmTypeService;

	//@Autowired(required=false)
	private AlarmStatusService alarmStatusService;

	//@Autowired(required=false)
	private AreaService areaService;

    public JobTest() {
    	getServices();
    	
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

	    // get Job Area
	    Area area = null;
		try {
			if (areaId != null)
				area = areaService.get(areaId);				
		}
		catch (Exception e) {
			throw new JobExecutionException(e.getMessage());
		}

		// get Alarm Type
		AlarmType alarmType;	
		try {
			alarmType = alarmTypeService.getByCode(Alarm.TYPE.DEFAULT.name());
		}
		catch (Exception e) {
			throw new JobExecutionException(e.getMessage());
		}

		// get Alarm Confirm status
		AlarmStatus alarmStatus;
		try {
			alarmStatus = alarmStatusService.getByCode(Alarm.STATUS.CONFIRM.name());
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
		alarm.setMessage("Hello! Alarm Test is executing from " + area.getDescription() + "Area");

		try {
			alarmService.save(alarm);
		} catch (Exception e) {
			throw new JobExecutionException(e.getMessage());
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void getServices() {
		try {
			BundleContext bundleContext = FrameworkUtil.getBundle(JobTest.class).getBundleContext();
			
			ServiceReference areaServiceReference = bundleContext.getServiceReference(AreaService.class.getName());
			areaService = AreaService.class.cast(bundleContext.getService(areaServiceReference));
			
			ServiceReference alarmServiceReference = bundleContext.getServiceReference(AlarmService.class.getName());
			alarmService = AlarmService.class.cast(bundleContext.getService(alarmServiceReference));

			ServiceReference alarmTypeServiceReference = bundleContext.getServiceReference(AlarmTypeService.class.getName());
			alarmTypeService = AlarmTypeService.class.cast(bundleContext.getService(alarmTypeServiceReference));
			
			ServiceReference alarmStatusServiceReference = bundleContext.getServiceReference(AlarmStatusService.class.getName());
			alarmStatusService = AlarmStatusService.class.cast(bundleContext.getService(alarmStatusServiceReference));
		}
		catch (Exception e) {
			e.getMessage();
			
		}
		
	}
}