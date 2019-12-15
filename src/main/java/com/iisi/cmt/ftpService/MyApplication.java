package com.iisi.cmt.ftpService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iisi.cmt.ftpService.utility.MyPath;

public class MyApplication {
	
	private static final Path monitoredPath = Paths.get(MyPath.MONITOR_DIR);
	private static final String monitoredFilename = MyPath.FTP_RULE_FILE;
	private static Logger logger= LoggerFactory.getLogger(MyApplication.class);
	private Notified mySpringCamel;
	
	public void startApp() {
		logger.info("Start MyApplication...");
		mySpringCamel = new MySpringCamel();
		
		mySpringCamel.start();
		
		try {
			logger.info("Start to monitor " +  monitoredFilename);
			new MonitorFile(monitoredPath, monitoredFilename, (Notified)mySpringCamel).monitor();	
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
		
	public void stopApp() {
		mySpringCamel.stop();
	}
	
	public static void main(String[] args) {
		MyApplication app = new MyApplication();
		app.startApp();
	}

}
