package com.thingtrack.konekti.schedule;

import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.thingtrack.konekti.domain.Job;
import com.thingtrack.konekti.schedule.internal.JobManagerServiceImpl;
import com.thingtrack.konekti.service.api.JobService;

public class ScheduleActivator implements BundleActivator {
	private BundleContext bundleContext;	
	private ServiceRegistration schedulerServiceRegistration;
	private ServiceRegistration jobManagerServiceRegistration;
	private JobServiceTracker jobServiceTracker;
	
	private static JobService jobService;	
	private static Scheduler scheduler;
	private static JobManagerService jobManagerService;
	
	private static final String JOB_LISTENER_NAME = "thingtrack_listener";
	public static final String JOB_SEPARATOR = "@";
	
	private static final boolean OK = false;
	private static final boolean ERROR = true;
	
	public void start(BundleContext context) throws Exception {
		this.bundleContext = context;
		
		// get job service
		jobService = getJobService();
		
		// create Factory Scheduler
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		
		try {
			// create scheduler from factory
			scheduler = schedulerFactory.getScheduler();
			
			// implements scheduler listener
			scheduler.getListenerManager().addJobListener(new JobListener() {				
				@Override
				public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
					String jobAreaName = context.getJobDetail().getKey().getName();
					String jobAreaGroup = context.getJobDetail().getKey().getGroup();
					
					Integer areaId = Integer.parseInt(jobAreaName.split(JOB_SEPARATOR)[0]);
					String jobName = jobAreaName.split(JOB_SEPARATOR)[1].toString();
					String jobGroup = jobAreaGroup.split(JOB_SEPARATOR)[1].toString();
					
					// get job executed from scheduler
					Job job = null;
					try {
						job = getJob(areaId, jobGroup, jobName);
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
					JobExecutionContext value = context;
					
				}
				
				@Override
				public void jobExecutionVetoed(JobExecutionContext context) {
					// TODO Auto-generated method stub
					JobExecutionContext value = context;
					
				}
				
				@Override
				public String getName() {
					return JOB_LISTENER_NAME;
				}
			});
			
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
			
		}
		
		// create Job manager service and register
		jobManagerService = new JobManagerServiceImpl(context);
		
		context.registerService("com.thingtrack.konekti.schedule.JobManagerService", jobManagerService, null);
		
		// create Job Service Tracker and register
		jobServiceTracker = new JobServiceTracker(context, jobManagerService);
		jobServiceTracker.open();
		
		schedulerServiceRegistration = context.registerService("org.quartz.Scheduler", scheduler, null);
		
	}

	public void stop(BundleContext context) throws Exception {
		if (jobManagerServiceRegistration != null) 
			jobManagerServiceRegistration.unregister();	
		
		if (schedulerServiceRegistration != null) 
			schedulerServiceRegistration.unregister();		

		if (scheduler != null)
			scheduler.shutdown();
		
		if (jobServiceTracker != null)
			jobServiceTracker.close();


		jobManagerServiceRegistration = null;
		schedulerServiceRegistration = null;
		scheduler = null;
		jobServiceTracker = null;
	}
	
	public static Scheduler getScheduler() {
		return scheduler;
		
	}
		 
	public static List<Job> getJobs(String group, String name) throws Exception {
		return jobService.getByGroupName(group, name);
		 		 
	}
	
	public static Job getJob(Integer areaId, String group, String name) throws Exception {
		return jobService.getByGroupNameAndArea(areaId, group, name);
		 		 
	}
	
	private JobService getJobService() {
		ServiceReference jobServiceReference = bundleContext.getServiceReference(JobService.class.getName());
		JobService jobService = (JobService)bundleContext.getService(jobServiceReference);
		
		return jobService;
				 
	}
}
