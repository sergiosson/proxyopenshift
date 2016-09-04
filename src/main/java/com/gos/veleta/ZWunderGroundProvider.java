package com.gos.veleta;

import java.util.Arrays;
import java.util.List;

public class ZWunderGroundProvider extends ZBaseWeatherProvider {

	String baseUrl = "http://api.wunderground.com/api/5ff47a62760ecb31/conditions/lang:EN/q/";
	
	private String[][] mappings = new String[][] {
			{ "windspeedKmph", "/response/current_observation/wind_kph" },
			{ "windspeedMiles", "/response/current_observation/wind_mph" },
			{ "winddirDegree", "/response/current_observation/wind_degrees" },
			{ "areaName",
					"/response/current_observation/observation_location/city" },
			{ "region",
					"/response/current_observation/observation_location/state" },
			{ "country",
					"/response/current_observation/observation_location/country" },
			{"provider","/response/current_observation/image/title"}
	};
	
	private List<String> modes = Arrays.asList(new String[]{"location", "ip"});
	
	
	public ZWunderGroundProvider() {
		this.timer = new TimeController(500, 1000 * 60 * 60 * 24);
	}
	
	@Override
	protected List<String> getModes() {
		
		return modes;
	}
	
	@Override
	protected String getIpUrl(String ip) {
		return baseUrl + "autoip.xml?geo_ip="+ip;
	}

	@Override
	protected String getLocationUrl(String lat, String lon) {
		return baseUrl + lat + "," + lon + ".xml";
	}

	@Override
	protected String[][] getMappings() {
		
		return mappings ;
	}

	
}
