/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.glaf.wechat.sdk.util;

public class LocationUtils {

	private final static double EARTH_RADIUS = 6378.137;

	/**
	 * 获取两地距离
	 * 
	 * @param lat1
	 *            A地纬度
	 * @param lng1
	 *            A地经度
	 * @param lat2
	 *            B地纬度
	 * @param lng2
	 *            B地经度
	 * @return
	 */
	public static double getDistance(double lat1, double lng1, double lat2,
			double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = s * 1000;
		return s;
	}

	/**
	 * 获取两地距离
	 * 
	 * @param lat1
	 *            A地纬度
	 * @param lng1
	 *            A地经度
	 * @param lat2
	 *            B地纬度
	 * @param lng2
	 *            B地经度
	 * @return
	 */
	public static double getKilometreDistance(double lat1, double lng1,
			double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	public static void main(String[] args) {
		System.out.println(getDistance(23.134507, 113.372508, 23.13073,
				113.402195));
	}

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

}
