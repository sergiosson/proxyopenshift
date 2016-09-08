package com.gos.veleta;

import javax.servlet.ServletException;

public enum WeatherSingleton {

	INSTANCE;

	Weather weather = new Weather();
	private WeatherSingleton() {
		
		weather.addProvider(new ZOpenWeatherProvider());
		weather.addProvider(new ZWunderGroundProvider());
		
	}

	public String getByLocation(String lat, String lon) throws ServletException {
		return weather.getByLocation(lat, lon);
	}
	
	public String getByIp(String ip) throws ServletException {
		return weather.getByIp(ip);
	}
}
