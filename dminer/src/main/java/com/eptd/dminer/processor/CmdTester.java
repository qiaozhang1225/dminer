package com.eptd.dminer.processor;

import java.util.ArrayList;

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
	    SonarAnalysisProcessor writer = new SonarAnalysisProcessor(
	    		URL,filePath,projectID,projectName,login,userType,language,version);
	    writer.process();
	    
		SonarResultExtractor sonarAnalyzer = new SonarResultExtractor("org.apache:tajo");
	    sonarAnalyzer.addMetrics("ncloc");
	    sonarAnalyzer.addMetrics("sqale_rating");
	    sonarAnalyzer.addMetrics("sqale_debt_ratio");
	    sonarAnalyzer.addMetrics("sqale_index");
	    sonarAnalyzer.addMetrics("duplicated_lines");
	    ArrayList<SonarMetrics> metricsValues = sonarAnalyzer.process();
	    for(int i=0;i<metricsValues.size();i++)
	    	System.out.println(
	    			metricsValues.get(i).getKey() + ": " +
	    			metricsValues.get(i).getValue() + " (" +
	    			metricsValues.get(i).getFormattedValue() + ")");
	}
}
