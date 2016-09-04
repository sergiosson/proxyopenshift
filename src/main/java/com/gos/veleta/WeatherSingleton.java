package com.gos.veleta;

import javax.servlet.ServletException;

public enum WeatherSingleton implements WeatherProvider{

	INSTANCE;

	Weather weather = new Weather();
	private WeatherSingleton() {
		
		
		weather.addProvider(new ZOpenWeatherProvider());
		
		weather.addProvider(new ZWunderGroundProvider());
		
//		provider = new ZWorldWeatherProvider();
//		provider.setMappings(null);
//		provider.setTimer(new TimeController(0, 0));
//		weather.addProvider(provider);

	}
	@Override
	public boolean isAvailable(String mode) {
		return true;
	}
	@Override
	public String getByLocation(String lat, String lon) throws ServletException {
		return weather.getByLocation(lat, lon);
	}
	@Override
	public String getByIp(String ip) throws ServletException {
		return weather.getByIp(ip);
	}
}
