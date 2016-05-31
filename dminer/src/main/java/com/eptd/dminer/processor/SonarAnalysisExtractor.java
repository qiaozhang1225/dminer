package com.eptd.dminer.processor;

import java.util.ArrayList;

import org.sonar.wsclient.Host;
import org.sonar.wsclient.Sonar;
import org.sonar.wsclient.connectors.HttpClient4Connector;
import org.sonar.wsclient.services.Resource;
import org.sonar.wsclient.services.ResourceQuery;

public class SonarAnalysisExtractor {
	private final String repositoryURL;
	private final String login;
	private final String password;
	private String projectKey;	
	private Sonar sonar;
	private ArrayList<String> metricsKeys;
	private ArrayList<SonarMetrics> metricsValues;
	
	public SonarAnalysisExtractor(String projectKey){
		this.repositoryURL = "http://localhost:9000";
		this.login = "admin";
		this.password = "admin";
		this.projectKey = projectKey;
		this.metricsKeys = new ArrayList<String>();
	}
	
	public SonarAnalysisExtractor(String repositoryURL, String login, String password, String projectKey){
		this.repositoryURL = repositoryURL;
		this.login = login;
		this.password = login;
		this.projectKey = projectKey;
		this.metricsKeys = new ArrayList<String>();
	}
	
	public boolean addMetrics(String metrics){
		return this.metricsKeys.add(metrics);
	}
	
	public ArrayList<SonarMetrics> process(){
		sonar = new Sonar(new HttpClient4Connector(new Host(repositoryURL, login, password)));
		Resource struts = sonar.find(ResourceQuery.createForMetrics(projectKey, metricsKeys.toArray(new String[metricsKeys.size()])));
		metricsValues = new ArrayList<SonarMetrics>();
		for(int i=0;i<metricsKeys.size();i++)
			metricsValues.add(new SonarMetrics(
					metricsKeys.get(i),
					struts.getMeasure(metricsKeys.get(i)).getValue(),
					struts.getMeasure(metricsKeys.get(i)).getFormattedValue()));	
		return metricsValues;
	}
	
	public SonarMetrics getMetrics(String metrics){
		for(int i=0;i<metricsValues.size();i++)
			if(metricsValues.get(i).getKey() == metrics)
				return metricsValues.get(i);
		return null;
	}
	
	public ArrayList<SonarMetrics> getAllMetrics(){
		return this.metricsValues;
	}
}
