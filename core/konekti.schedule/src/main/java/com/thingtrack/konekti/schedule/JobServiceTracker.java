package com.thingtrack.konekti.schedule;

import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.thingtrack.konekti.domain.Job;

public class JobServiceTracker extends ServiceTracker {
	private BundleContext context;
	
	public JobServiceTracker(BundleContext context) {				
		super(context, JobApi.class.getName(), null);
		
		this.context = context;
		
	}	

	@Override
	public Object addingService(ServiceReference reference) {
		super.addingService(reference);
		
		JobApi jobQuartz = (JobApi)context.getService(reference);
				
		System.out.println("schedulling jobs ...");
		
		// get jobs configuration from name and group quartz job
		List<Job> jobs = null;		
	    try {
	    	jobs = ScheduleActivator.getJobs(jobQuartz.getGroup(), jobQuartz.getName());
	    }
	    catch(Exception ex) {
	    	ex.printStackTrace();
	    	
	    	return jobQuartz;
	    }
		
	    for (Job job : jobs) {				    	   				    	
		    try {
		    	// construct unique job and group name
		    	Integer areaId = job.getArea().getAreaId();
		    	String jobName = areaId + ScheduleActivator.JOB_SEPARATOR + job.getJobName();
		    	String jobGroup = areaId + ScheduleActivator.JOB_SEPARATOR + job.getJobGroup();
		    	
		    	// create quartz job
		    	JobDetail jobDetail = JobBuilder.newJob(jobQuartz.getClass())
		    			    .withIdentity(jobName, jobGroup)
		    				.usingJobData("areaId", areaId)
		    				.build();
		      
		    	// create quartz job trigger
		    	Trigger trigger = configureTriggerJob(jobName, jobGroup, job);
		    		      
		    	// schedule the job associated to the trigger
		    	ScheduleActivator.getScheduler().scheduleJob(jobDetail, trigger);
		     				    	
		    	// check initial job status management
				if (!job.getActive())
					ScheduleActivator.getScheduler().pauseJob(new JobKey(jobName, jobGroup));
				 
		    } catch (Exception ex) {
		    	ex.printStackTrace();
		    	
		    }
	    }
    	
	    return jobQuartz;
	 }
	
	 public void removedService(ServiceReference reference, Object service) {
		 JobApi jobQuartz = (JobApi) service;
		 
		 System.out.println("Unscheduling job ");
		 
		 // get jobs configuration from name and group quartz job
		 List<Job> jobs = null;
		
	     try {
	    	jobs = ScheduleActivator.getJobs(jobQuartz.getGroup(), jobQuartz.getName());
	     }
	     catch(Exception ex) {
	    	 ex.printStackTrace();
	    	
	    	 return;
	     }
		    
	     for (Job job : jobs) {				    	   				    	
		    try {
		    	// construct unique job and group name
		    	Integer areaId = job.getArea().getAreaId();
		    	String jobName = areaId + ScheduleActivator.JOB_SEPARATOR + job.getJobName();
		    	String jobGroup = areaId + ScheduleActivator.JOB_SEPARATOR + job.getJobGroup();
		    	
		    	JobKey jobKey = new JobKey(jobName, jobGroup);
		    	JobDetail jobDetail = ScheduleActivator.getScheduler().getJobDetail(jobKey);
		    	
		    	if (jobDetail == null)
			    	return;
		    	
		    	ScheduleActivator.getScheduler().deleteJob(jobDetail.getKey());
		    	
		    } catch (Exception ex) {
		    	ex.printStackTrace();
		    	
		    }
	     }
	 
	    System.out.println("Jobs unscheduled ...");
	     
	    super.removedService(reference, service);
	    
	  }	 		
	 	 
	 private Trigger configureTriggerJob(String jobName, String jobGroup, Job job) {
		 TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
		 		 
		 // base trigger configuration 
		 triggerBuilder.withIdentity(jobName, jobGroup);
		 
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
