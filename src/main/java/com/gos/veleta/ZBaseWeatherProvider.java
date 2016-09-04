package com.gos.veleta;

import java.util.List;

import javax.servlet.ServletException;

import org.json.JSONObject;
import org.json.XML;

public abstract class ZBaseWeatherProvider implements WeatherProvider {


	protected TimeController timer;

	public ZBaseWeatherProvider(){
		
	}

	protected abstract String getIpUrl(String ip);
	
	protected abstract String getLocationUrl(String lat, String lon);
	
	@Override
	public boolean isAvailable(String mode) {
		boolean b = false;
		if(getModes().contains(mode)) {
			b = timer.isAvailable();
		}
		return b;
	}

	protected abstract List<String> getModes();

	@Override
	public String getByLocation(String lat, String lon) throws ServletException {
		String url = getLocationUrl(lat, lon);
		return getAndConvert(url);
	}

	@Override
	public String getByIp(String ip) throws ServletException {
		String url = getIpUrl(ip);
		return getAndConvert(url);
	}

	private String getAndConvert(String url) throws ServletException {
		timer.addRequest();
		String xml = Util.getResponseFromWeatherApi(url);
		if(xml.startsWith("{")){
			JSONObject json = new JSONObject(xml);
			xml = XML.toString(json);
		}
		return Util.convertApi(xml, getMappings());
		//return getMockResponse();
	}

	protected String getMockResponse() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><data><current_condition><windspeedKmph>5</windspeedKmph><windspeedMiles>5</windspeedMiles><winddirDegree>310</winddirDegree></current_condition><nearest_area><areaName>Beato</areaName><country>PT</country><region>PT</region></nearest_area><provider>wunderground.com</provider></data>";
	}
	
	public TimeController getTimer() {
		return timer;
	}

	public void setTimer(TimeController timer) {
		this.timer = timer;
	}

	protected abstract String[][] getMappings();
}
