package com.gos.veleta;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.gos.veleta.ErrorInfo;
import com.gos.veleta.RestClient;

public class Util {

	// private static final String HTTP_ICANHAZIP_COM = "http://icanhazip.com/";
	private static final String TAG_WINDSPEED_KMPH = "/data/current_condition/windspeedKmph";
	private static final String TAG_WINDSPEED_MILES = "/data/current_condition/windspeedMiles";

	private static final String TAG_WINDDIR_DEGREE = "/data/current_condition/winddirDegree";

	private static final String TAG_AREA_NAME = "/data/nearest_area/areaName";
	private static final String TAG_COUNTRY = "/data/nearest_area/country";
	private static final String TAG_REGION = "/data/nearest_area/region";
	private static final String TAG_PROVIDER = "/data/provider";
	private static final String TAG_ERROR = "/veleta/error/message";

	static Logger log = Logger.getLogger(Util.class);

	Map<String, String> cacheMap = new HashMap<String, String>();

	public static String getResponseFromWeatherApi(String url, TimeController timer)
			throws ServletException {

		String response = Cache.get(url);
		log.info(Cache.kRequests + ":" + Cache.kfoundInCache + "+" + Cache.kNotFoundInCache);
		if (response != null) {
			return response;
		}

		log.info("Calling URL " + url);
		RestClient rc = new RestClient(url);

		try {
			timer.addRequest();
			rc.executeRequest();
		} catch (ErrorInfo e) {
			log.error(e);
			throw new ServletException("Error getting info from weather api");
		}

		response = rc.getResponse();
		if (response != null) {
			response = response.trim();
		}
		// log.info("Response: " + response);
		Cache.put(url, response);
		return response;
	}

	public static String convertApi(String xml, String[][] mappings) {

		String template = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><data><current_condition><windspeedKmph>##windspeedKmph</windspeedKmph><windspeedMiles>##windspeedMiles</windspeedMiles><winddirDegree>##winddirDegree</winddirDegree></current_condition>"
				+ "<nearest_area><areaName>##areaName</areaName><country>##country</country><region>##region</region></nearest_area><provider>##provider</provider></data>";
		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Document document = null;
		try {
			db = dbf.newDocumentBuilder();
			document = db.parse(new ByteArrayInputStream(xml.getBytes()));
		} catch (ParserConfigurationException e) {

			return "<veleta><error><message>" + "Error xml"
					+ "</message></error></veleta>";

		} catch (SAXException e) {
			return "<veleta><error><message>" + "Error xml"
					+ "</message></error></veleta>";

		} catch (IOException e) {
			return "<veleta><error><message>" + "Error xml"
					+ "</message></error></veleta>";

		}

		String error = getFirstTag(TAG_ERROR, document, xPath);
		if (error != null && error.length() > 0) {
			return "<veleta><error><message>" + error
					+ "</message></error></veleta>";
		}

		for (int i = 0; i < mappings.length; i++) {
			String tag = mappings[i][0];
			String xpath = mappings[i][1];

			String value = getFirstTag(xpath, document, xPath);
			if (tag.equals("windspeedKmph") || tag.equals("windspeedMiles")
					|| tag.equals("winddirDegree")) {
				if (value != null && value.length() > 0) {
					value = Double.valueOf(value).intValue() + "";
				}
			}

			template = template.replace("##" + tag, value);
		}
		template = template.replace("Rabassa", "Esos torreznos de Almagro ..., ");
		template = template.replace("##provider", "openweathermap.org");

		return template;
	}

	private static String getFirstTag(String xpathExpr, Document document,
			XPath xPath) {
		String result;

		try {
			return xPath.evaluate(xpathExpr, document);
		} catch (XPathExpressionException e) {
			result = "";
		}

		return result;
	}

	public static String getIp(HttpServletRequest request) {

		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		log.info("IP is " + ip);
		return ip;
	}


}
