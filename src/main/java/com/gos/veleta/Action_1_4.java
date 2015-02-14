package com.gos.veleta;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Action_1_4 {

	static Random ran = new Random();
	private static final String weatherURl = "http://api.worldweatheronline.com/free/v2/weather.ashx"
			+ "?fx=no"
			+ "&includeLocation=yes"
			+ "&show_comments=no"
			+ "&format=xml"
			+ "&key=ee0c108d51c15e501093ca29f58a32d2116790ab"
			+ "&q=";

	public static void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String ipAddress = req.getRemoteAddr();

		InetAddress byName = InetAddress.getByName(ipAddress);

		String xml = null;
		if (byName instanceof Inet6Address) {
			String ipv6Message = "Since you use IPV6, what is good, you need to update this application from Google Play. For questions contact info@haveanapp.com";
			xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><veleta><error><message>"
					+ ipv6Message + "</message></error></veleta>";

		} else if ("Mfsd_2489hf*__6".equals(req.getParameter("token"))) {

			if (ran.nextBoolean() && ran.nextBoolean() && ran.nextBoolean()) {
				xml = getUpdateYourAppXml();
			} else {
				xml = Util.getResponseFromWeatherApi(weatherURl + ipAddress);
			}
		} else {
			xml = getUpdateYourAppXml();
		}
		resp.setContentType("text/xml");
		resp.getWriter().println(xml);
		resp.getWriter().flush();

	}

	private static String getUpdateYourAppXml() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><data><nearest_area><areaName>Please </areaName><region> update </region><country>this app in google play: https://play.google.com/store/apps/details?id=com.gos.veleta </country></nearest_area><current_condition><windspeedMiles>0</windspeedMiles><windspeedKmph>0</windspeedKmph><winddirDegree>0</winddirDegree></current_condition></data>";
	}

}
