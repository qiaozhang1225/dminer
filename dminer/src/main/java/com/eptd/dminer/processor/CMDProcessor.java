package com.eptd.dminer.processor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CMDProcessor {
	private static Logger logger = LoggerFactory.getLogger(CMDProcessor.class);
	
	private Process p;
	private PrintWriter stdin;
	private ArrayList<String> commands;
	private final String CMD = "cmd";
	
	public CMDProcessor(){
		commands = new ArrayList<String>();
	}
	
	public boolean addCommand(String commandLine){
		return commands.add(commandLine);
	}
	
	/**
	 * @throws IOException If error occurs during process initialization
	 * @throws InterruptedException If process is interrupted accidentally
	 * @return 0 if command prompt is closed normally; -1 if command execution is interrupted accidentally.
	 */
	public int execute(){
		try {
			//initialize
			p = Runtime.getRuntime().exec(CMD);
			new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
			new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
			stdin = new PrintWriter(p.getOutputStream());
			//execute appended commands
			if(!commands.isEmpty())
				for(int i=0;i<commands.size();i++)
					stdin.println(commands.get(i));
			commands.clear();
			stdin.close();
			return p.waitFor();
		} catch (InterruptedException e) {
			logger.error("CMD Execution Error: ", e);
		} catch (IOException e) {
			logger.error("CMD Execution Error: ", e);
		}
		return -1;
	}
}
