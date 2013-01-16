package com.thingtrack.konekti.schedule;

import java.util.HashMap;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.thingtrack.konekti.domain.AlarmJob;
import com.thingtrack.konekti.service.api.AlarmJobService;

public class ScheduleActivator implements BundleActivator {
	private BundleContext bundleContext;
	private static AlarmJobService alarmJobService;
	
	private static Scheduler scheduler;
	private ScheduleServiceTracker scheduleServiceTracker;
	
	private ServiceRegistration schedulerService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static HashMap<AlarmJobApi, JobDetail> jobdetails = new HashMap();
	
	private static String JOBLISTENER_NAME = "thingtrack_listener";
	
	public void start(BundleContext context) throws Exception {
		this.bundleContext = context;
		
		// get alarm job service
		alarmJobService = getAlarmJobService();
		
		// create scheduler 
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		
		try {
			scheduler = schedulerFactory.getScheduler();
			
			// implements scheduler listener
			scheduler.getListenerManager().addJobListener(new JobListener() {				
				@Override
				public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
					// get alarm job executed from scheduler
					AlarmJob alarmJob = null;
					try {
						alarmJob = getAlarmJob(context.getJobDetail().getKey().getGroup(), context.getJobDetail().getKey().getName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
						return;
					}
					
					// set last execution and control if exist any error
					try {
						if (jobException == null)
							alarmJobService.setLastExecution(alarmJob, false);
						else
							alarmJobService.setLastExecution(alarmJob, true);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				@Override
				public void jobToBeExecuted(JobExecutionContext context) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void jobExecutionVetoed(JobExecutionContext context) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public String getName() {
					return JOBLISTENER_NAME;
				}
			});
			
			scheduler.start();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		scheduleServiceTracker = new ScheduleServiceTracker(context);
		scheduleServiceTracker.open();
		
		// register scheduler
		schedulerService = context.registerService("org.quartz.Scheduler", scheduler, null);
				
	}

	public void stop(BundleContext context) throws Exception {
		if (schedulerService != null) {
			schedulerService.unregister();
			schedulerService = null;
		}
		
		scheduler.shutdown();
		scheduler = null;
		
		scheduleServiceTracker.close();
		scheduleServiceTracker = null;
		
	}
	
	public static Scheduler getScheduler() {
		return scheduler;
		
	}

	public static HashMap<AlarmJobApi, JobDetail> getJobDetails() {
		return jobdetails;
		
	}
	
	 private AlarmJobService getAlarmJobService() {
		 ServiceReference alarmJobServiceReference = bundleContext.getServiceReference(AlarmJobService.class.getName());
		 
		 if(alarmJobServiceReference != null) {
			 AlarmJobService alarmJobService = (AlarmJobService)bundleContext.getService(alarmJobServiceReference);
			 if(alarmJobService != null)
			 	return alarmJobService;
				 
		 }
		 
		 return null;
	 }
	 
	 public static AlarmJob getAlarmJob(String group, String name) throws Exception {
		 return alarmJobService.getByGroupName(group, name);
		 		 
	 }
}
