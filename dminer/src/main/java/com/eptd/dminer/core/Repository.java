package com.eptd.dminer.core;

import java.util.ArrayList;
import java.util.Date;

public class Repository {
	private long id;
	private User owner;
	private long size;
	private Languages languages;
	private long stargazersCount;
	private long subscribersCount;
	private long forksCount;
	private ArrayList<User> assignees;
	private ArrayList<User> contributors;
	private Date created;
	
	//sonar evaluation data
	private long LOC;
	private char SQALErating;
	private double TDratio;
	
}
