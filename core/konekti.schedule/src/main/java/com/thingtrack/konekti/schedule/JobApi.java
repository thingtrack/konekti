package com.thingtrack.konekti.schedule;

import org.quartz.Job;

public interface JobApi extends Job {
	public String getName();
	public String getGroup();
	
}
