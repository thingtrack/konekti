package com.thingtrack.konekti.schedule;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.util.ObjectUtils;

import com.thingtrack.konekti.domain.Job;

public class ScheduleServiceTracker extends ServiceTracker {
	private TaskExecutor executor;
	
	public ScheduleServiceTracker(BundleContext context) {
		super(context, JobApi.class.getName(), null);
		
	}	

	@Override
	public Object addingService(ServiceReference reference) {
		JobApi jobImpl = (JobApi) super.addingService(reference);
			 	
		executor = createDefaultTaskExecutor();
		executor.execute(new JobThread(jobImpl));
    	
	    return jobImpl;
	 }
	
	 public void removedService(ServiceReference reference, Object service) {
		 JobApi job = (JobApi) service;
		 
		 System.out.println("Unscheduling job ");
		    
	     JobDetail jobDetail = ScheduleActivator.getJobDetails().get(job);
	    
	     if (jobDetail == null)
	    	return;
	    
	     try {
	    	 ScheduleActivator.getScheduler().deleteJob(jobDetail.getKey());
	     } catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	     }
	 
	    super.removedService(reference, service);
	  }
	 	
	 private Trigger configureTriggerJob(Job job) {
		 TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
		 
		 // base trigger configuration 
		 triggerBuilder.withIdentity(job.getJobName(), job.getJobGroup());
		 
		 if (job.getStartTime() != null)
			 triggerBuilder.startAt(job.getStartTime());
		 else {
			 if (job.getActive())
				 triggerBuilder.startNow();
		 }

		 if (job.getEndTime() != null)
			 triggerBuilder.endAt(job.getEndTime());		 
			 
		 triggerBuilder.withPriority(job.getJobTriggerPriority());
		 
		 // propietary trigger configuration
		 if (job.getJobTriggerType().getCode().equals(Job.JOB_TRIGGER_TYPE.SIMPLE.name())) {
			 SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
			 
			 if (job.getJobInterval() != null)
				 simpleScheduleBuilder.withIntervalInSeconds(job.getJobInterval());
			
			 if (job.getRepeatCount() != null)
				 simpleScheduleBuilder.withRepeatCount(job.getRepeatCount());
			 else
				 simpleScheduleBuilder.repeatForever();
			 
		     triggerBuilder.withSchedule(simpleScheduleBuilder);
				 
		 }
		 else if (job.getJobTriggerType().getCode().equals(Job.JOB_TRIGGER_TYPE.CRON.name())) {
			 CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
			 
			 triggerBuilder.withSchedule(cronScheduleBuilder);
		 }
		 						 
		 return triggerBuilder.build();
	 }
	 
	 private TaskExecutor createDefaultTaskExecutor() {
		// create thread-pool for starting contexts
		ThreadGroup threadGroup = new ThreadGroup("spring-osgi-extender[" + ObjectUtils.getIdentityHexString(this) + "]-threads");
		threadGroup.setDaemon(false);

		SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
		taskExecutor.setThreadGroup(threadGroup);
		taskExecutor.setThreadNamePrefix("SpringOsgiExtenderThread-");

		return taskExecutor;
	 }
	 
	 //public static class JobThread extends Thread {
	 private class JobThread implements Runnable {
		    private final JobApi service;

		    public JobThread(JobApi service) {
		      this.service = service;
		      
		    }

		    public void run() {
			    System.out.println("schedulling job ...");
			    
		    	// get job configuration from konekti
				Job job = null;
				
			    try {
			    	job = ScheduleActivator.getJob(service.getGroup(), service.getName());
			    }
			    catch(Exception ex) {
			    	ex.getMessage();
			    	
			    	return;
			    }
			      
			    Integer areaId = null;
			    JobDetail jobDetail = null;
			    Trigger trigger = null;
			    
			    if (job.getArea() != null)
			    	areaId = job.getArea().getAreaId();	    
			    	
			    try {
			    	// create job from konekti configuration
			    	jobDetail = JobBuilder.newJob(service.getClass()).withIdentity(job.getJobName(), job.getJobGroup())
			    				.usingJobData("areaId", areaId)
			    				.build();
			      
			    	// create job trigger
			    	trigger = configureTriggerJob(job);
			    		      
			    	// schedule the job associated to the trigger
			    	ScheduleActivator.getScheduler().scheduleJob(jobDetail, trigger);
			     
			    	// add job to scheduler collection
			    	ScheduleActivator.getJobDetails().put(service, jobDetail);	
			    	
			    	// initial job active status management
					if (!job.getActive())
						ScheduleActivator.getScheduler().pauseJob(new JobKey(job.getJobName(), job.getJobGroup()));
					 
			    } catch (Exception ex) {
			    	ex.printStackTrace();
			    	
			    }
			 	    
		    	System.out.println("job scheduled ...");
		    
		    }
		 
	 }
	 
}
