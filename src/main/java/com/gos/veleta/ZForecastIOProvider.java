//package com.gos.veleta;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Arrays;
//import java.util.List;
//
//import org.json.JSONObject;
//import org.json.XML;
//
//public class ZForecastIOProvider extends ZBaseWeatherProvider {
//
//	public static void main(String[] args) throws IOException {
//		byte[] encoded = Files.readAllBytes(Paths.get("/tmp/json"));
//		String str = new String(encoded, "UTF-8");
//		JSONObject json = new JSONObject(str);
//		String xml = XML.toString(json);
//		System.out.println(xml);
//	}
//
//	final String[][] mappings = new String[][] { { "windspeedKmph", "" }, { "windspeedMiles", "" },
//			{ "winddirDegree", "" }, { "areaName", "" }, { "region", "" }, { "country", "" }
//
//	};
//
//	private List<String> modes = Arrays.asList(new String[] { "location" });
//
//	public ZForecastIOProvider() {
//		this.timer = new TimeController(1000, 1000 * 60 * 60 * 24);
//	}
//
//	@Override
//	protected String getIpUrl(String ip) {
//		throw new RuntimeException("Not implemented");
//	}
//
//	@Override
//	protected String getLocationUrl(String lat, String lon) {
//		return "https://api.forecast.io/forecast/38e23535c96834d06b2a79afec8c9f9b/" + lat + "," + lon;
//	}
//
//	@Override
//	protected String[][] getMappings() {
//
//		return mappings;
//	}
//
//	@Override
//	protected List<String> getModes() {
//
//		return modes;
//	}
//
//}
