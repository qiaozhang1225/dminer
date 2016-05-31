package com.eptd.dminer.processor;

import java.util.ArrayList;

import org.sonar.wsclient.Host;
import org.sonar.wsclient.Sonar;
import org.sonar.wsclient.connectors.HttpClient4Connector;
import org.sonar.wsclient.services.Resource;
import org.sonar.wsclient.services.ResourceQuery;

public class CmdTester {
	public static void main(String[] args) {
	    String URL = "https://github.com/apache/tajo";
	    String filePath = "E:\\Github\\apache\\tajo";
	    long projectID = 17971138;
	    String projectName = "tajo";
	    String login = "apache";
	    String userType = "org";
	    String language = "java";
	    String version = "release-0.11.3-rc0";
	    RepositoryProcessor writer = new RepositoryProcessor(
	    		URL,filePath,projectID,projectName,login,userType,language,version);
	    writer.process();
	    
		SonarAnalysisExtractor sonarAnalyzer = new SonarAnalysisExtractor("org.apache:tajo");
	    sonarAnalyzer.addMetrics("ncloc");
	    sonarAnalyzer.addMetrics("sqale_rating");
	    sonarAnalyzer.addMetrics("sqale_debt_ratio");
	    ArrayList<SonarMetrics> metricsValues = sonarAnalyzer.process();
	    for(int i=0;i<metricsValues.size();i++)
	    	System.out.println(
	    			metricsValues.get(i).getKey() + ": " +
	    			metricsValues.get(i).getValue() + " (" +
	    			metricsValues.get(i).getFormattedValue() + ")");
	}
}
