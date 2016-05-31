package com.eptd.dminer.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RepositoryProcessor {
	private static Logger logger = LoggerFactory.getLogger(RepositoryProcessor.class);
	
	private String repositoryURL;
	private String filePath;
	private long projectID;
	private String projectName;
	private String login;
	private String userType;
	private String language;
	private String version;
	
	/**
	 * Construct a repository processor for SonarQube analysis without specified release version
	 * @param repositoryURL The GitHub URL of repository to be processed 
	 * @param filePath The directory of root of cloned local repository
	 * @param projectID The ID of repository 
	 * @param projectName The name of repository
	 * @param login The name of repository owner
	 * @param userType The type of repository owner either user or organization
	 * @param language The primary programming language
	 */
	public RepositoryProcessor(
			String repositoryURL, String filePath, long projectID, String projectName,
			String login, String userType, String language){
		this.repositoryURL = repositoryURL;
		this.filePath = filePath;
		this.projectID = projectID;
		this.projectName = projectName;
		this.login = login;
		this.userType = userType;
		this.language = language;
		this.version = "1.0.0";
	}
	
	/**
	 * Construct a repository processor for SonarQube analysis with specified release version
	 * @param repositoryURL The GitHub URL of repository to be processed 
	 * @param filePath The directory of root of cloned local repository
	 * @param projectID The ID of repository 
	 * @param projectName The name of repository
	 * @param login The name of repository owner
	 * @param userType The type of repository owner either user or organization
	 * @param language The primary programming language
	 * @param version The name of latest release tag
	 */
	public RepositoryProcessor(
			String repositoryURL, String filePath, long projectID, String projectName,
			String login, String userType, String language, String version){
		this.repositoryURL = repositoryURL;
		this.filePath = filePath;
		this.projectID = projectID;
		this.projectName = projectName;
		this.login = login;
		this.userType = userType;
		this.language = language;
		this.version = version;
	}
	
	/**
	 * @return True if the GitHub repository has been processed successfully including three subtasks: 
	 * (1) clone remote repository to local drive, (2) create properties file for sonar project, and 
	 * (3) run SonarQube analysis; false if one of three subtasks failed.
	 */
	public boolean process(){
		CMDProcessor cmd = new CMDProcessor();
		//step 1: clone repository
		cmd.addCommand("git clone "+ repositoryURL + " " + filePath);
		if(cmd.execute() == 0){
			System.out.println();
			logger.info("Project " + projectName + " has been successfully cloned to path \"" 
					+ filePath + "\"");
		}else{
			logger.error("Project " + projectName + " failed to be cloned to path \"" 
					+ filePath + "\"");
			return false;
		}
		//step 2: write properties file
		SonarPropertiesWriter writer = new SonarPropertiesWriter(
	    		projectID,projectName,login,userType,language,version,filePath);
		if(writer.write()){
			System.out.println();
			logger.info("Project properties file for SonarQube analysis has been created successfully to path \""
					+ filePath + "\"");
		}else{
			logger.info("Project properties file for SonarQube analysis failed to be created to path \""
					+ filePath + "\"");
			return false;
		}
		//step 3: run SonarQube analysis
		cmd.addCommand("cd " + filePath);
	    cmd.addCommand("sonar-runner");
	    if(cmd.execute() == 0){
	    	System.out.println();
			logger.info("Project " + projectName + " has been successfully analyzed by SonarQube");
	    }else{
			logger.error("Project " + projectName + " failed to be analyzed by SonarQube");
			return false;
		}
		return true;
	}
}
