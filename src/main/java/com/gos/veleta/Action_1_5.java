package com.gos.veleta;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Action_1_5 {
	private static final String weatherURl = "http://api.worldweatheronline.com/free/v2/weather.ashx"
			+ "?fx=no"
			+ "&includeLocation=yes"
			+ "&show_comments=no"
			+ "&format=xml"
			+ "&key=ee0c108d51c15e501093ca29f58a32d2116790ab"
			+ "&q=";

	public static void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		if ("Mfsd_2489hf*__6".equals(req.getParameter("token"))) {
			// TODO Auto-generated method stub
			String ipAddress = req.getRemoteAddr();

			InetAddress byName = InetAddress.getByName(ipAddress);

			String xml = null;

			String lat = req.getParameter("lat");
			if (lat != null) {
				String lon = req.getParameter("lon");

				xml = Util.getResponseFromWeatherApi(weatherURl + lat + ","
						+ lon);

			} else if (byName instanceof Inet6Address) {

				String ipv6Message = "Cannot get your location. try again after activationg gps."
						+ " Or contact info@haveanapp.com";
				xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><veleta><error><message>"
						+ ipv6Message + "</message></error></veleta>";
			} else {

				xml = Util.getResponseFromWeatherApi(weatherURl + ipAddress);
			}
			resp.setContentType("text/xml");
			resp.getWriter().println(xml);
			resp.getWriter().flush();
		}

	}

}
