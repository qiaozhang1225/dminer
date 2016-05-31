package com.eptd.dminer.processor;

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
	}
}
