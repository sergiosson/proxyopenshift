package com.gos.veleta;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class Action_1_5 {

	static Logger log = Logger.getLogger(Action_1_5.class);

	public static void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		if ("Mfsd_2489hf*__6".equals(req.getParameter("token"))) {
			// TODO Auto-generated method stub
			String ipAddress = Util.getIp(req);

			InetAddress byName = InetAddress.getByName(ipAddress);

			String xml = null;

			String lat = req.getParameter("lat");
			if (lat != null) {
				String lon = req.getParameter("lon");
				log.info("Action_1_5 loc");
				xml = WeatherSingleton.INSTANCE.getByLocation(lat, lon);

			} else if (byName instanceof Inet6Address) {

				log.info("Action_1_5 inet6");
				String ipv6Message = "Cannot get your location. try again after activationg gps."
						+ " Or contact info@haveanapp.com";
				xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><veleta><error><message>"
						+ ipv6Message + "</message></error></veleta>";
			} else {
				log.info("Action_1_5 ip");
				xml = WeatherSingleton.INSTANCE.getByIp(ipAddress);
			}
			resp.setContentType("text/xml");
			
			resp.getWriter().println(xml);
			resp.getWriter().flush();
		}

	}

}
