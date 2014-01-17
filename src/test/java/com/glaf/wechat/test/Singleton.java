package com.glaf.wechat.test;

public class Singleton {

	private static class SingletonHolder {
		public static Singleton instance = new Singleton();
	}

	public static Singleton getInstance() {
		return SingletonHolder.instance;
	}

	private Singleton() {

	}
}