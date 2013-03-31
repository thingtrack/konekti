package com.thingtrack.konekti.schedule;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.thingtrack.konekti.domain.Job;

public class ScheduleServiceTracker extends ServiceTracker {
	public ScheduleServiceTracker(BundleContext context) {
		super(context, JobApi.class.getName(), null);
		
	}	

	@Override
	public Object addingService(ServiceReference reference) {
		JobApi jobImpl = (JobApi) super.addingService(reference);
		
	    if (jobImpl == null)
	      return null;
	 	    
	    System.out.println("schedulling job ...");
	      
	    // get job configuration
		Job job = null;
		
	    try {
	    	job = ScheduleActivator.getJob(jobImpl.getGroup(), jobImpl.getName());
	    }
	    catch(Exception ex) {
	    	ex.getMessage();
	    	  
	    	return null;
	    }
	      
	    JobDetail jobDetail = null;
	    Integer areaId = null;
	    
	    if (job.getArea() != null)
	    	areaId = job.getArea().getAreaId();	    
	    	
	    try {
	    	// create job passing area Id
	    	jobDetail = JobBuilder.newJob(jobImpl.getClass()).withIdentity(job.getJobName(), job.getJobGroup())
	    				.usingJobData("areaId", areaId)
	    				.build();
	      
	    	// create job trigger
	    	Trigger trigger = configureTriggerJob(job);
	    		      
	    	// schedule the job associated to the trigger
	    	ScheduleActivator.getScheduler().scheduleJob(jobDetail, trigger);
	     
	    	// add job to scheduler collection
	    	ScheduleActivator.getJobDetails().put(jobImpl, jobDetail);	      
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	
	    	return null;
	    }
	 	    
    	System.out.println("job scheduled ...");
    	
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
}
