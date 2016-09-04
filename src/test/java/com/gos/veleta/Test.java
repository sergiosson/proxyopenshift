package com.gos.veleta;

public class Test {

	public static void main(String[] args) throws Exception {
		
		for (int i=0; i<10000; i++) {
			String xml = WeatherSingleton.INSTANCE.getByLocation("38.738838", "-9.124308");
		//	System.out.println(xml);
//			String xmlip = WeatherSingleton.INSTANCE.getByIp("38.738838");
//			System.out.println(xmlip);
			
		}
	}
}
