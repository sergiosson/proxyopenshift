package com.gos.veleta2;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

public enum Weather {

	INST;
	
	static Logger log = Logger.getLogger(Weather.class);
	
	
	List<IWeatherProvider> providersIp = new ArrayList<IWeatherProvider>();
	
	private Weather() {
		this.providers.add(e);
	}

	
	public String getByLocation(String lat, String lon) throws ServletException {
		
		synchronized (this) {
			try {
				return getAvailableProvider("location").getByLocation(lat,lon);
			} catch(Exception e){
				log.error("SGOWeather Error, retrying  ", e);
				try {
					return getAvailableProvider("location").getByLocation(lat,lon);
				}catch(Exception e2){
					log.error("SGOWeather Error again" ,e2);
					throw new ServletException(e2);
				}
			}
		}
	}
	
	private synchronized WeatherProvider getAvailableProvider(String mode) {
		WeatherProvider provider = null;
		
		for(int count = 0; provider == null && count < providers.size(); count ++){
			
			WeatherProvider p = providers.remove(0);
			providers.add(p);
			
			if(p.isAvailable(mode)){
				provider = p;
			} 
		}
		if(provider == null) {
			log.error("No providers available");
			throw new RuntimeException("No weather providers available");
		}
		return provider;
		
	}

	
	@Override
	public String getByIp(String ip) throws ServletException {
		synchronized (this) {
			try {
				return getAvailableProvider("ip").getByIp(ip);
			} catch (Exception e){
				log.error("SGOWeather Error, retry..." , e);
				try {
					return getAvailableProvider("ip").getByIp(ip);
				} catch (Exception e1) {
					log.error("SGOWeather Error again", e1);
					throw new ServletException(e1);
				}
			}
			
		}
	}

	@Override
	public boolean isAvailable(String mode) {
		
		return true;
	}
	
	
}
