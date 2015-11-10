/*package com.gos.veleta;

import java.util.Random;

import org.junit.Test;

public class CacheTest extends Thread {

	final static int THREADS = 10000;
	final static int KEY_RANGE = 1000;

	final static long MAX_TIME = 5;
	final static long CLEAN_TIME = 10;

	Random ran = new Random();

	@Test
	public void test() {
		Cache.MAX_TIME = MAX_TIME;
		Cache.CLEAN_TIME = CLEAN_TIME;

		for (int i = 0; i < THREADS; i++) {
			Thread t = new CacheTest();
			t.start();
		}

	}

	@Override
	public void run() {

		for (int i = 0; i < 10000; i++) {

			Cache.put(getRandomKey(), ran.nextLong() + "_pepe");

			System.out.println(Cache.get(getRandomKey()));
		}
	}

	private String getRandomKey() {
		return String.valueOf(ran.nextInt(KEY_RANGE));
	}

}
*/