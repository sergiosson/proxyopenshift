package com.gos.veleta;

import javax.servlet.ServletException;

public interface WeatherProvider {

	boolean isAvailable(String mode);

	String getByLocation(String lat, String lon) throws ServletException;

	String getByIp(String ip) throws ServletException;

}
