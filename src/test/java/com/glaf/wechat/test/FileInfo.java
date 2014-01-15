package com.glaf.wechat.test;

public class FileInfo implements java.io.Serializable {
 
	private static final long serialVersionUID = 1L;

	protected int sortNo;
	
	protected String path;
	
	protected String name;
	
	public FileInfo(){
		
	}

	public int getSortNo() {
		return sortNo;
	}

	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
