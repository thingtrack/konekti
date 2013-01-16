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

import com.thingtrack.konekti.domain.AlarmJob;

public class ScheduleServiceTracker extends ServiceTracker {
	public ScheduleServiceTracker(BundleContext context) {
		super(context, AlarmJobApi.class.getName(), null);
		
	}	

	@Override
	public Object addingService(ServiceReference reference) {
		AlarmJobApi alarmJobImpl = (AlarmJobApi) super.addingService(reference);
		
	    if (alarmJobImpl == null)
	      return null;
	 	    
	    System.out.println("schedulling alarm job ...");
	      
	    // get alarm job configuration
		AlarmJob alarmJob = null;
		
	    try {
	    	alarmJob = ScheduleActivator.getAlarmJob(alarmJobImpl.getGroup(), alarmJobImpl.getName());
	    }
	    catch(Exception ex) {
	    	ex.getMessage();
	    	  
	    	return null;
	    }
	      
	    JobDetail jobDetail = null;
	    Integer locationId = null;
	    
	    if (alarmJob.getLocation() != null)
	    	locationId = alarmJob.getLocation().getLocationId();	    
	    	
	    try {
	    	// create alarm job passing Location Id
	    	jobDetail = JobBuilder.newJob(alarmJobImpl.getClass()).withIdentity(alarmJob.getAlarmName(), alarmJob.getAlarmGroup())
	    				.usingJobData("locationId", locationId)
	    				.build();
	      
	    	// create alarm job trigger
	    	Trigger trigger = configureTriggerJob(alarmJob);
	    		      
	    	// schedule the alarm job associated to the trigger
	    	ScheduleActivator.getScheduler().scheduleJob(jobDetail, trigger);
	     
	    	// add alarm job to scheduler collection
	    	ScheduleActivator.getJobDetails().put(alarmJobImpl, jobDetail);	      
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	
	    	return null;
	    }
	 	    
    	System.out.println("alarm job scheduled ...");
    	
	    return alarmJobImpl;
	 }
	
	 public void removedService(ServiceReference reference, Object service) {
		 AlarmJobApi alarmJob = (AlarmJobApi) service;
		 
		 System.out.println("Unscheduling alarm job ");
		    
	     JobDetail alarmJobDetail = ScheduleActivator.getJobDetails().get(alarmJob);
	    
	     if (alarmJobDetail == null)
	    	return;
	    
	     try {
	    	 ScheduleActivator.getScheduler().deleteJob(alarmJobDetail.getKey());
	     } catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	     }
	 
	    super.removedService(reference, service);
	  }
	 	
	 private Trigger configureTriggerJob(AlarmJob alarmJob) {
		 TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
		 
		 // base trigger configuration 
		 triggerBuilder.withIdentity(alarmJob.getAlarmName(), alarmJob.getAlarmGroup());
		 
		 if (alarmJob.getStartTime() != null)
			 triggerBuilder.startAt(alarmJob.getStartTime());
		 else
			 triggerBuilder.startNow();

		 if (alarmJob.getEndTime() != null)
			 triggerBuilder.endAt(alarmJob.getEndTime());		 
			 
		 triggerBuilder.withPriority(alarmJob.getAlarmTriggerPriority());
		 
		 // propietary trigger configuration
		 if (alarmJob.getAlarmTriggerType().getCode().equals(AlarmJob.ALARM_TRIGGER_TYPE.SIMPLE.name())) {
			 SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
			 
			 if (alarmJob.getAlarmInterval() != null)
				 simpleScheduleBuilder.withIntervalInSeconds(alarmJob.getAlarmInterval());
			
			 if (alarmJob.getRepeatCount() != null)
				 simpleScheduleBuilder.withRepeatCount(alarmJob.getRepeatCount());
			 else
				 simpleScheduleBuilder.repeatForever();
			 
		     triggerBuilder.withSchedule(simpleScheduleBuilder);
				 
		 }
		 else if (alarmJob.getAlarmTriggerType().getCode().equals(AlarmJob.ALARM_TRIGGER_TYPE.CRON.name())) {
			 CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(alarmJob.getCronExpression());
			 
			 triggerBuilder.withSchedule(cronScheduleBuilder);
		 }
		 			 
		 return triggerBuilder.build();
	 }
}
