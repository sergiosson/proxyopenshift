package com.gos.veleta;

import java.util.HashMap;
import java.util.Map;

public class Cache {
	
	static long MINUTES = 5;
	static long MAX_TIME = 1000 * 60 * MINUTES;
	
	static Map<String, CacheElement> map = new HashMap<String, CacheElement>(); 

	public static void put(String url, String response) {
		CacheElement element = new CacheElement(response);
		map.put(url, element);
	}
	public static String get(String url) {
		
		CacheElement cacheElement = map.get(url);
		
		if(cacheElement == null) {
			return null;
		} else if(isExpired(cacheElement)){
			map.remove(url);
			return null;
		} else {
			return cacheElement.value;
		}
	}
	private static boolean isExpired(CacheElement cacheElement) {
		
		long age = System.currentTimeMillis() - cacheElement.timestamp;
		return age > MAX_TIME;
	}
}
