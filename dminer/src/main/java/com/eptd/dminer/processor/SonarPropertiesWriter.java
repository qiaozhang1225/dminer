package com.eptd.dminer.processor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.apache.commons.lang3.text.WordUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SonarPropertiesWriter {
	private static Logger logger = LoggerFactory.getLogger(SonarPropertiesWriter.class);
	
	private Writer writer;
	private String filePath;
	
	//comments
	private final String COMMENT_METADATA = "# Required metadata";
	private final String COMMENT_SOURCES = "# Comma-separated paths to directories with sources (required)";
	private final String COMMENT_LANGUAGE = "# Language";
	private final String COMMENT_ENCODING = "# Encoding of the source files";
	
	//sonar project properties
	private final String SONAR_PROJECT_KEY = "sonar.projectKey=";
	private final String SONAR_PROJECT_NAME = "sonar.projectName=";
	private final String SONAR_PROJECT_VERSION = "sonar.projectVersion=";
	private final String SONAR_SOURCES = "sonar.sources=";
	private final String SONAR_LANGUAGE = "sonar.language=";
	private final String SONAR_ENCODING = "sonar.sourceEncoding=";
	
	//Written file name
	private final String FILENAME = "sonar-project.properties";
	
	//values of sonar project  properties
	private String projectKey;
	private String projectName;
	private String projectVersion;
	private String sources;
	private String language;
	private String sourceEncoding;
	
	/**
	 * Construct a sonar properties file writer
	 * @param projectID The id of repository
	 * @param projectName The name of repository
	 * @param login The name of repository owner
	 * @param userType The type of repository owner either user or organization
	 * @param language The primary programming language
	 * @param version The name of latest release tag
	 * @param filePath The file path of cloned repository root directory
	 */
	public SonarPropertiesWriter(long projectID, String projectName,String login, String userType, String language, String version, String filePath){
		constructor(projectID,projectName,login,userType,language,version,filePath);
		this.setSourceEncoding("utf-8");
	}
	
	/**
	 * Construct a sonar properties file writer with specified encoding
	 * @param projectID The id of repository
	 * @param projectName The name of repository
	 * @param login The name of repository owner
	 * @param userType The type of repository owner either user or organization
	 * @param language The primary programming language
	 * @param version The name of latest release tag
	 * @param filePath The file path of cloned repository root directory
	 * @param encoding The specific encoding of source files
	 */
	public SonarPropertiesWriter(long projectID, String projectName,String login, String userType, String language, String version, String filePath, String encoding){
		constructor(projectID,projectName,login,userType,language,version,filePath);
		this.setSourceEncoding(encoding);
	}
	
	private void constructor(long projectID, String projectName, String login, String userType, String language, String version, String filePath){
		this.projectKey = userType + "." + login + ":" + projectName;
		this.projectName = WordUtils.capitalize(language + " :: " + projectName + " :: " + projectID);
		this.projectVersion = version;
		this.sources = ".";
		this.setLanguage(language);
		this.filePath = filePath + "\\" + FILENAME;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setSourceEncoding(String sourceEncoding) {
		this.sourceEncoding = sourceEncoding;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath + "\\" + FILENAME;
	}
	
	/**
	 * Write the sonar-project.properties file into filePath for further reference of SonarQube analysis
	 * @throws IOException If errors occur during file writing process
	 * @throws FileNotFoundException If no file exists on the abstract path
	 * @throws UnsupportedEncodingException If file encoding is not supported
	 * @return True if sonar-project.properties file is created successfully; false if a duplicate creation is requested.
	 */
	public boolean write(){
		File file;
		try {
			file = new File(filePath);
			
			if(file.exists()){
				logger.error("Duplicate creation of sonar-project.properties file");
				return false;
			}				
			
			//create writer
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), sourceEncoding));
		
			//write properties
			writer.write(COMMENT_METADATA);
			writer.write("\n");
			writer.write(SONAR_PROJECT_KEY + projectKey);
			writer.write("\n");
			writer.write(SONAR_PROJECT_NAME + projectName);
			writer.write("\n");
			writer.write(SONAR_PROJECT_VERSION + projectVersion);
			writer.write("\n");
			writer.write("\n");
			writer.write(COMMENT_SOURCES);
			writer.write("\n");
			writer.write(SONAR_SOURCES + sources);
			writer.write("\n");
			writer.write("\n");
			writer.write(COMMENT_LANGUAGE);
			writer.write("\n");
			writer.write(SONAR_LANGUAGE + language);
			writer.write("\n");
			writer.write("\n");
			writer.write(COMMENT_ENCODING);
			writer.write("\n");
			writer.write(SONAR_ENCODING + sourceEncoding);			
			writer.flush();
			writer.close();
		} catch (UnsupportedEncodingException e) {
			logger.error("Sonar Properties Error: ", e);
		} catch (FileNotFoundException e) {
			logger.error("Sonar Properties Error: ", e);
		} catch (IOException e) {
			logger.error("Sonar Properties Error: ", e);
		}
		return true;
	}
}
