package com.eptd.dminer.core;

import java.util.ArrayList;

public class User {
	private long id;							//also id for data crawler
	private String login;						//backup id
	private String name;						//better id for reviewing
	private boolean isOrg;						//whether an organization
	private boolean isAssignee;					//whether an assignee for issue
	private int numOfPublicRepos;				//count of public repos
	private ArrayList<Repository> publicRepos;	//
	

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
		return numOfPublicRepos;
	}

	public void setNumOfPublicRepos(int numOfPublicRepos) {
		this.numOfPublicRepos = numOfPublicRepos;
	}

	public boolean isOrg() {
		return isOrg;
	}

	public void setOrg(boolean isOrg) {
		this.isOrg = isOrg;
	}

	public boolean isAssignee() {
		return isAssignee;
	}

	public void setAssignee(boolean isAssignee) {
		this.isAssignee = isAssignee;
	}

	public ArrayList<Repository> getPublicRepos() {
		return publicRepos;
	}

	public void setPublicRepos(ArrayList<Repository> publicRepos) {
		this.publicRepos = publicRepos;
	}
	
	
	
}
