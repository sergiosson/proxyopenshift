package com.gos.veleta;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ProxyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static Logger log = Logger.getLogger(ProxyServlet.class);
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {


		doLog(req);
		String version = req.getParameter("v");

		if ("1.6".equals(version) || "1.5".equals(version)) {
			Action_1_5.doGet(req, resp);
		} else {
			Action_1_4.doGet(req, resp);
		}

	}

	private void doLog(HttpServletRequest req) {
		try {
			
			String msg = "time:"+ new Date()+
					"v:"+ req.getParameter("v")+
					"token" + req.getParameter("token") +
					"lat" + req.getParameter("lat") +
					"lon" + req.getParameter("lon") +
					"ip:" + req.getRemoteAddr() ;
			log.info(msg);

		} catch (Exception e) {
			return;
		}
	}

}
