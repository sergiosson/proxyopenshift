package com.gos.veleta;

class CacheElement {
	public long timestamp;
	public String value;
	
	public CacheElement(String value){
		this.value = value;
		this.timestamp = System.currentTimeMillis();
	}
}