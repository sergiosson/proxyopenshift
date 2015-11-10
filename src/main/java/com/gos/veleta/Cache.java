package com.gos.veleta;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

public class Cache {
	
	static long MINUTE = 1000 * 60;
	static long MAX_TIME = 5 * MINUTE;

	static long CLEAN_TIME = 30 * MINUTE;
	
	static long lastTimeCleaned = System.currentTimeMillis();
		
	static Map<String, CacheElement> map = new ConcurrentHashMap<String, CacheElement>(); 

	static Logger log = Logger.getLogger(Cache.class);

	public static void put(String url, String response) {
		
		synchronized (map) {
			
			cleanCache();
		
			CacheElement element = new CacheElement(response);
			map.put(url, element);
		}
		
		
	}
	private static void cleanCache() {
		long timePassedSinceLastClean = System.currentTimeMillis() - lastTimeCleaned;
		
		if(timePassedSinceLastClean > CLEAN_TIME) {
			
			Set<String> valuesToRemove = getOldValues();
			log.info("removing values " + valuesToRemove.size());
			for (String key: valuesToRemove) {
				map.remove(key);
			}
			lastTimeCleaned = System.currentTimeMillis();
		}
	}
	private static Set<String> getOldValues() {
		Set<String> valuesToRemove = new HashSet<String>();
		for(String key: map.keySet()){
			CacheElement cacheElement = map.get(key);
			if(isExpired(cacheElement)){
				valuesToRemove.add(key);
			}
		}
		return valuesToRemove;
	}
	public static String get(String key) {
		
		synchronized (map) {
		
			CacheElement cacheElement = map.get(key);
			
			if(cacheElement == null) {
				return null;
			} else if(isExpired(cacheElement)){
				map.remove(key);
				return null;
			} else {
				return cacheElement.value;
			}
		}
	}
	private static boolean isExpired(CacheElement cacheElement) {
		
		long age = System.currentTimeMillis() - cacheElement.timestamp;
		return age > MAX_TIME;
	}
}
