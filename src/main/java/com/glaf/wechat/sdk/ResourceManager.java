package com.glaf.wechat.sdk;

import java.io.IOException;
import java.util.Properties;

/**
 * manage resource
 * 
 */
public class ResourceManager {

	private static final String RESOURCE_FILENAME = "wechat.properties";
	private static Properties props = new Properties();

	static {
		try {
			props.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(RESOURCE_FILENAME));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getValue(String key) {
		return props.getProperty(key);
	}

}
