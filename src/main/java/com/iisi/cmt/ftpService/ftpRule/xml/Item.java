package com.iisi.cmt.ftpService.ftpRule.xml;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Item {
	
	private String sourceFolder;
	private String ftpIp;
	private String targetFolder;
	private String ftpUsername;
	private String ftpPassword;
	private Optional<String> ftpOtherParameter;
	private Map<String, String> filenamePair = new HashMap<>();
	
	/**
	 * 
	 * @param sourceFolder
	 * @param ftpIp
	 * @param targetFolder
	 * @param ftpUsername
	 * @param ftpPassword
	 */
	public Item(String sourceFolder, String ftpIp, String targetFolder, String ftpUsername, String ftpPassword, Optional<String> ftpOtherParameter) {
		super();
		this.sourceFolder = sourceFolder;
		this.ftpIp = ftpIp;
		this.targetFolder = targetFolder;
		this.ftpUsername = ftpUsername;
		this.ftpPassword = ftpPassword;
		this.ftpOtherParameter = ftpOtherParameter;
	}
	public String getSourceFolder() {
		return sourceFolder;
	}
	public String getFtpIp() {
		return ftpIp;
	}
	public String getTargetFolder() {
		return targetFolder;
	}
	public String getFtpUsername() {
		return ftpUsername;
	}
	public String getFtpPassword() {
		return ftpPassword;
	}
	public Map<String, String> getFilenamePair() {
		return filenamePair;
	}
	public Optional<String> getFtpOtherParameter() {
		return ftpOtherParameter;
	}
		
}
