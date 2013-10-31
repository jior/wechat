package com.glaf.wechat.test;

public class MD5Test {

	public static void main(String[] args){
		System.out.println(org.apache.commons.codec.digest.DigestUtils.md5Hex("admin"));
		System.out.println(org.apache.commons.codec.digest.DigestUtils.md5Hex("111111"));
		System.out.println(org.apache.commons.codec.digest.DigestUtils.md5Hex("liao"));
		System.out.println(org.apache.commons.codec.digest.DigestUtils.md5Hex("888888"));
	}
}
