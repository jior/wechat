package com.glaf.wechat.sdk.message;

/**
 * location message
 * 
 */
public class LocationMessage extends Message {
	private static final long serialVersionUID = 1L;
	protected String locationX;
	protected String locationY;
	protected String scale;
	protected String label;

	public String getLabel() {
		return label;
	}

	public String getLocationX() {
		return locationX;
	}

	public String getLocationY() {
		return locationY;
	}

	public String getScale() {
		return scale;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

}
