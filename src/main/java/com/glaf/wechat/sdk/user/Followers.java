package com.glaf.wechat.sdk.user;

import java.util.*;

public class Followers implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected int total;

	protected int count;

	protected String nextOpenId;

	protected List<User> users = new java.util.concurrent.CopyOnWriteArrayList<User>();

	public Followers() {

	}

	public int getCount() {
		return count;
	}

	public String getNextOpenId() {
		return nextOpenId;
	}

	public int getTotal() {
		return total;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setNextOpenId(String nextOpenId) {
		this.nextOpenId = nextOpenId;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
