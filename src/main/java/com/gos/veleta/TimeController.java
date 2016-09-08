package com.gos.veleta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

public class TimeController {
	

	static Logger log = Logger.getLogger(TimeController.class);
	
	
	protected int maxRequests;
	protected long timePeriod;

	public TimeController(int maxRequests, long timePeriod){
		this.maxRequests = maxRequests;
		this.timePeriod = timePeriod;
	}
	
	List<Long> times = new ArrayList<Long>();
	
	public boolean isAvailable() {
		if(times.size() >= maxRequests) {
			Set<Long> expired = new HashSet<Long>();
			
			for(Long l : times){
				if(l < System.currentTimeMillis() - timePeriod){
					expired.add(l);
				}
			}
			times.removeAll(expired);
		}
		log.info("TimeController " + maxRequests+ " " + times.size());
		return times.size() < maxRequests;
	}

	public void addRequest(){
		times.add(System.currentTimeMillis());
	}
}
