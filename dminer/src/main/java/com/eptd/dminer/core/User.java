package com.eptd.dminer.core;

public class User {
	private long id;					//also id for data crawler
	private String login;				//backup id
	private String name;				//better id for reviewing
	private boolean isOrg;				//whether an organization
	private int NumOfPublicRepos;		//count of public repos
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumOfPublicRepos() {
		return NumOfPublicRepos;
	}

	public void setNumOfPublicRepos(int numOfPublicRepos) {
		NumOfPublicRepos = numOfPublicRepos;
	}

	public boolean isOrg() {
		return isOrg;
	}

	public void setOrg(boolean isOrg) {
		this.isOrg = isOrg;
	}
	
	
	
}
