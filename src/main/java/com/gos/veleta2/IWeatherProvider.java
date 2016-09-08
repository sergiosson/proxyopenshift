package com.gos.veleta2;

public interface IWeatherProvider {

	public boolean isAvailable();
	
	public void doRequestLocation();
	
	public boolean isError();
	
	public String getError();
	
	public WeatherData getData();

}
