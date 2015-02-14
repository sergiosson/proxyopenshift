package com.gos.veleta;

import javax.servlet.ServletException;

import com.gos.veleta.ErrorInfo;
import com.gos.veleta.RestClient;

public class Util {

	public static String getResponseFromWeatherApi(String url)
			throws ServletException {
		RestClient rc = new RestClient(url);

		try {
			rc.executeRequest();
		} catch (ErrorInfo e) {
			throw new ServletException("Error getting info from weather api");
		}
		return rc.getResponse();
	}
}
