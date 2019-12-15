package com.iisi.cmt.ftpService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinuxDaemonMainClass {
	
	private static Logger logger= LoggerFactory.getLogger(LinuxDaemonMainClass.class);
	
	private static MyApplication myApp;

	public static void init(String[] args) {
		logger.info("CMTFtpService Linux Daemon inits!");
	}

    public static void destroy() {
    	logger.info("CMTFtpService Linux Daemon destroies!");
    }

    public static void start() {
    	logger.info("CMTFtpService Linux Daemon starts...");
    	if(myApp == null) {
    		myApp = new MyApplication();
    	}
    	myApp.startApp();
    }

    public static void stop() {
    	logger.info("CMTFtpService Linux Daemon stopped...");
    	myApp.stopApp();
    	myApp = null;
    	System.exit(0);
    }
}
