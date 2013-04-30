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

import com.thingtrack.konekti.domain.Job;
import com.thingtrack.konekti.service.api.JobService;

public class ScheduleActivator implements BundleActivator {
	private BundleContext bundleContext;
	private static JobService jobService;
	
	private static Scheduler scheduler;
	private ScheduleServiceTracker scheduleServiceTracker;
	
	private ServiceRegistration schedulerService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static HashMap<JobApi, JobDetail> jobdetails = new HashMap();
	
	private static boolean OK = false;
	private static boolean ERROR = true;
	
	private static String JOBLISTENER_NAME = "thingtrack_listener";
	
	public void start(BundleContext context) throws Exception {
		this.bundleContext = context;
		
		// get job service
		jobService = getJobService();
		
		// create scheduler 
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		
		try {
			scheduler = schedulerFactory.getScheduler();
			
			// implements scheduler listener
			scheduler.getListenerManager().addJobListener(new JobListener() {				
				@Override
				public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
					// get job executed from scheduler
					Job job = null;
					try {
						job = getJob(context.getJobDetail().getKey().getGroup(), context.getJobDetail().getKey().getName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
						return;
					}
					
					// set last execution and control if exist any error
					try {
						if (jobException == null)
							jobService.setLastExecution(job, OK);
						else
							jobService.setLastExecution(job, ERROR);
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

	public static HashMap<JobApi, JobDetail> getJobDetails() {
		return jobdetails;
		
	}
	
	 private JobService getJobService() {
		 ServiceReference jobServiceReference = bundleContext.getServiceReference(JobService.class.getName());
		 
		 if(jobServiceReference != null) {
			 JobService jobService = (JobService)bundleContext.getService(jobServiceReference);
			 if(jobService != null)
			 	return jobService;
				 
		 }
		 
		 return null;
	 }
	 
	 public static Job getJob(String group, String name) throws Exception {
		 return jobService.getByGroupName(group, name);
		 		 
	 }
}
