package com.thingtrack.konekti.schedule;

import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import com.thingtrack.konekti.domain.Job;

public class JobServiceTracker extends ServiceTracker {
	private JobManagerService jobManagerService;
	
	public JobServiceTracker(BundleContext context, JobManagerService jobManagerService) {				
		super(context, JobApi.class.getName(), null);
		
		this.jobManagerService = jobManagerService;
		
	}	

	@Override
	public Object addingService(ServiceReference reference) {
		super.addingService(reference);
		
		JobApi jobQuartz = (JobApi)context.getService(reference);
				
		System.out.println("Schedulling jobs ...");
		
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
		    	jobManagerService.scheduleJob(job);
		    			     				    	
		    	// check initial job status management
				if (!job.getActive())
					jobManagerService.pauseJob(job);
				 
		    } catch (Exception ex) {
		    	ex.printStackTrace();
		    	
		    }
	    }
    	
	    System.out.println("Jobs schedulled ...");
	    
	    return jobQuartz;
	 }
	
	 public void removedService(ServiceReference reference, Object service) {
		 JobApi jobQuartz = (JobApi) service;
		 
		 System.out.println("Unscheduling job ...");
		 
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
		    	jobManagerService.deleteJob(job);
		    			    	
		    } catch (Exception ex) {
		    	ex.printStackTrace();
		    	
		    }
	     }
	 
	    System.out.println("Jobs unscheduled ...");
	     
	    super.removedService(reference, service);
	    
	  }	 		
	 	
}
