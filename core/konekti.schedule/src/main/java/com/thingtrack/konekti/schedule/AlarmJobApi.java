package com.thingtrack.konekti.schedule;

import org.quartz.Job;

public interface AlarmJobApi extends Job {
	public String getName();
	public String getGroup();
	
}
