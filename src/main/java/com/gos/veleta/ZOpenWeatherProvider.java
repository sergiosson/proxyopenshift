package com.gos.veleta;

import java.util.Arrays;
import java.util.List;

public class ZOpenWeatherProvider extends ZBaseWeatherProvider{
	
	final String [][] mappings = new String[][]{
		{ "windspeedKmph", "/current/wind/speed/@value" },
		{ "windspeedMiles", "/current/wind/speed/@value" },
		{ "winddirDegree", "/current/wind/direction/@value" },
		{ "areaName",
				"/current/city/@name" },
		{ "region",
				"/current/city/country" },
		{ "country",
				"/current/city/country" }
		
	};
	
	private List<String> modes = Arrays.asList(new String[]{"location"});
	
	
	public ZOpenWeatherProvider() {
		this.timer = new TimeController(60, 60*1000);
	}
	
	@Override
	protected List<String> getModes() {
		
		return modes;
	}
	
	@Override
	protected String getIpUrl(String ip) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	protected String getLocationUrl(String lat, String lon) {
		return  
				"http://api.openweathermap.org/data/2.5/weather?q&mode=xml"
				+ "&APPID=d7c567102323e6d813dd9cb06d7a2408&" 
				+ "lat=" + lat 
				+ "&lon=" + lon  ;
	}

	@Override
	protected String[][] getMappings() {
		
		return mappings;
	}

}
