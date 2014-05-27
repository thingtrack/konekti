package com.thingtrack.konekti.schedule.internal;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.ServiceReference;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.thingtrack.konekti.domain.Job;
import com.thingtrack.konekti.schedule.JobApi;
import com.thingtrack.konekti.schedule.JobManagerService;
import com.thingtrack.konekti.schedule.ScheduleActivator;

public class JobManagerServiceImpl implements JobManagerService {
	private BundleContext context;
	
	public JobManagerServiceImpl(BundleContext context) {
		this.context = context;
	}
	
	@Override
	public void configure(Job job) throws Exception {		
		// unschedule job
		deleteJob(job);
				
		// schedule job
		scheduleJob(job);
		
	}

	@Override
	public void resumeJob(Job job) throws Exception {
		String quartzJobName = job.getArea().getAreaId() + ScheduleActivator.JOB_SEPARATOR + job.getJobName();
		String quartzJobGroup = job.getArea().getAreaId() + ScheduleActivator.JOB_SEPARATOR + job.getJobGroup();
					
		// check if the job with this name, group is scheduled
		JobKey jobKey = new JobKey(quartzJobName, quartzJobGroup);			
		JobDetail jobDetail = ScheduleActivator.getScheduler().getJobDetail(jobKey);
		
		if (jobDetail == null)
			throw new Exception("¡El job seleccionado no está planificado!");				
		
		ScheduleActivator.getScheduler().resumeJob(jobKey);
		
	}

	@Override
	public void pauseJob(Job job) throws Exception {
		String quartzJobName = job.getArea().getAreaId() + ScheduleActivator.JOB_SEPARATOR + job.getJobName();
		String quartzJobGroup = job.getArea().getAreaId() + ScheduleActivator.JOB_SEPARATOR + job.getJobGroup();
					
		// check if the job with this name, group is scheduled
		JobKey jobKey = new JobKey(quartzJobName, quartzJobGroup);			
		JobDetail jobDetail = ScheduleActivator.getScheduler().getJobDetail(jobKey);
		
		if (jobDetail == null)
			throw new Exception("¡El job seleccionado no está planificado!");
		
		ScheduleActivator.getScheduler().pauseJob(jobKey);
		
	}

	@Override
	public void deleteJob(Job job) throws Exception {
		String quartzJobName = job.getArea().getAreaId() + ScheduleActivator.JOB_SEPARATOR + job.getJobName();
		String quartzJobGroup = job.getArea().getAreaId() + ScheduleActivator.JOB_SEPARATOR + job.getJobGroup();
					
		// check if the job with this name, group is scheduled
		JobKey jobKey = new JobKey(quartzJobName, quartzJobGroup);			
		JobDetail jobDetail = ScheduleActivator.getScheduler().getJobDetail(jobKey);
		
		if (jobDetail == null)
			throw new Exception("¡El job seleccionado no está planificado!");
		
		ScheduleActivator.getScheduler().deleteJob(jobKey);
		
	}

	@Override
	public void scheduleJob(Job job) throws Exception {
		// get JopApi filtered from registry	
		Filter filter = context.createFilter("(&(name=" + job.getJobName() + ")(group=" + job.getJobGroup() + "))");
		
		ServiceReference[] jobServiceReferences = context.getServiceReferences(JobApi.class.getName(), filter.toString());
		
		if (jobServiceReferences.length == 0)
			throw new Exception("Not exist any reference for this job");
		
		if (jobServiceReferences.length > 1)
			throw new Exception("Exist more than one reference for this job");
		
		JobApi jobQuartz = JobApi.class.cast(context.getService(jobServiceReferences[0]));
					
		// construct unique job and group name
    	String quartzJobName = job.getArea().getAreaId() + ScheduleActivator.JOB_SEPARATOR + job.getJobName();
    	String quartzJobGroup = job.getArea().getAreaId() + ScheduleActivator.JOB_SEPARATOR + job.getJobGroup();
    	
    	// create quartz job
    	JobDetail jobDetail = JobBuilder.newJob(jobQuartz.getClass())
    			    .withIdentity(quartzJobName, quartzJobGroup)
    				.usingJobData("areaId", job.getArea().getAreaId())
    				.build();
    			
		// reconfigure job
		Trigger trigger = configureTriggerJob(quartzJobName, quartzJobGroup, job);
				
		// schedule the job associated to the trigger
    	ScheduleActivator.getScheduler().scheduleJob(jobDetail, trigger);
		
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
			 
			 if (job.getJobInterval() != null) {
				 if (job.getJobIntervalType().getCode().equals(Job.JOB_INTERVAL_TYPE.SECOND.name()))
					 simpleScheduleBuilder.withIntervalInSeconds(job.getJobInterval());
				 else if (job.getJobIntervalType().getCode().equals(Job.JOB_INTERVAL_TYPE.MINUTE.name()))
					 simpleScheduleBuilder.withIntervalInMinutes(job.getJobInterval());
				 else if (job.getJobIntervalType().getCode().equals(Job.JOB_INTERVAL_TYPE.HOUR.name()))
					 simpleScheduleBuilder.withIntervalInHours(job.getJobInterval());
				 else if (job.getJobIntervalType().getCode().equals(Job.JOB_INTERVAL_TYPE.DAY.name()))
					 simpleScheduleBuilder.withIntervalInHours(job.getJobInterval() * 24);
				 else if (job.getJobIntervalType().getCode().equals(Job.JOB_INTERVAL_TYPE.WEEK.name()))
					 simpleScheduleBuilder.withIntervalInHours(job.getJobInterval() * 24 * 7);
			 }
			
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
