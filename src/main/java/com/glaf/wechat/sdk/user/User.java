package com.glaf.wechat.sdk.user;

public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String openId;

	protected String nickname;

	protected int subscribe;

	protected int sex;

	protected String city;

	protected String country;

	protected String province;

	protected String language;

	protected String headimgurl;

	protected long subscribeTime;

	public User() {

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (openId == null) {
			if (other.openId != null)
				return false;
		} else if (!openId.equals(other.openId))
			return false;
		return true;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public String getLanguage() {
		return language;
	}

	public String getNickname() {
		return nickname;
	}

	public String getOpenId() {
		return openId;
	}

	public String getProvince() {
		return province;
	}

	public int getSex() {
		return sex;
	}

	public int getSubscribe() {
		return subscribe;
	}

	public long getSubscribeTime() {
		return subscribeTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((openId == null) ? 0 : openId.hashCode());
		return result;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	public void setSubscribeTime(long subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

}
