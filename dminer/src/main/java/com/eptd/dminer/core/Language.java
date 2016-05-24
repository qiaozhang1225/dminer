package com.eptd.dminer.core;

public class Language {
	private String type;
	private long LOC;
	
	public Language(String type, long LOC){
		this.type = type;
		this.LOC = LOC;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public long getLOC() {
		return LOC;
	}
	
	public void setLOC(long lOC) {
		LOC = lOC;
	}
}
