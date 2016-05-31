package com.eptd.dminer.processor;

import java.util.ArrayList;

import org.sonar.wsclient.Host;
import org.sonar.wsclient.Sonar;
import org.sonar.wsclient.connectors.HttpClient4Connector;
import org.sonar.wsclient.services.Resource;
import org.sonar.wsclient.services.ResourceQuery;

public class SonarResultExtractor {
	private final String localServerURL;
	private final String login;
	private final String password;
	private String projectKey;	
	private Sonar sonar;
	private ArrayList<String> metricsKeys;
	private ArrayList<SonarMetrics> metricsValues;
	
	/**
	 * Construct a SonarQube analysis result extractor with default SonarQube configuration.
	 * @param projectKey The project identifier in SonarQube database
	 */
	public SonarResultExtractor(String projectKey){
		this.localServerURL = "http://localhost:9000";
		this.login = "admin";
		this.password = "admin";
		this.projectKey = projectKey;
		this.metricsKeys = new ArrayList<String>();
	}
	
	/**
	 * Construct a SonarQube analysis result extractor with customized SonarQube configuration. For default configuration, use constructor with single parameter of projectKey.
	 * @param localServerURL The specifically configured SonarQube server URL (default: http://localhost:9000)
	 * @param login The username of administrator account of SonarQube (default: admin)
	 * @param password The password of administrator account of SonarQube (default: admin)
	 * @param projectKey The project identifier in SonarQube database
	 */
	public SonarResultExtractor(String localServerURL, String login, String password, String projectKey){
		this.localServerURL = localServerURL;
		this.login = login;
		this.password = login;
		this.projectKey = projectKey;
		this.metricsKeys = new ArrayList<String>();
	}
	
	/**
	 * Add a single metrics to SonarQube analysis result search query
	 * @param metrics A specific metrics of metrics list http://localhost:9000/api/metrics
	 * @return True if metrics is added successfully; false if otherwise
	 */
	public boolean addMetrics(String metrics){
		return this.metricsKeys.add(metrics);
	}
	
	/**
	 * Process SonarQube analysis result extraction
	 * @return A list of SonarMetrics classes that contains all metrics value information
	 */
	public ArrayList<SonarMetrics> process(){
		sonar = new Sonar(new HttpClient4Connector(new Host(localServerURL, login, password)));
		Resource struts = sonar.find(ResourceQuery.createForMetrics(projectKey, metricsKeys.toArray(new String[metricsKeys.size()])));
		metricsValues = new ArrayList<SonarMetrics>();
		for(int i=0;i<metricsKeys.size();i++)
			metricsValues.add(new SonarMetrics(
					metricsKeys.get(i),
					struts.getMeasure(metricsKeys.get(i)).getValue(),
					struts.getMeasure(metricsKeys.get(i)).getFormattedValue()));	
		return metricsValues;
	}
	
	/**
	 * Get single metrics value information
	 * @param metrics A specific metrics of metrics listed by last query
	 * @return A single SonarMetrics classes that contains the metrics value information
	 */
	public SonarMetrics getMetrics(String metrics){
		for(int i=0;i<metricsValues.size();i++)
			if(metricsValues.get(i).getKey() == metrics)
				return metricsValues.get(i);
		return null;
	}
	
	/**
	 * Get all metrics value information
	 * @return A list of SonarMetrics classes that contains all metrics value information
	 */
	public ArrayList<SonarMetrics> getAllMetrics(){
		return this.metricsValues;
	}
}
