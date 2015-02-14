package com.gos.veleta;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProxyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		String version = req.getParameter("v");

		if ("1.6".equals(version) || "1.5".equals(version)) {
			Action_1_5.doGet(req, resp);
		} else {
			Action_1_4.doGet(req, resp);
		}

	}

}
