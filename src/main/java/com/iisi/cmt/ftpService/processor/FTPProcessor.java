package com.iisi.cmt.ftpService.processor;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.iisi.cmt.ftpService.ftpRule.xml.Item;
import com.iisi.cmt.ftpService.utility.MyCamelHeader;
import com.iisi.cmt.ftpService.utility.MyPath;

@Service
public class FTPProcessor implements Processor {

	private static String UTF8_CHARSET = "UTF-8";

	// FTP default protocol encoding iso-8859-1
	private static String FTP_DEFAULT_CHARSET = "ISO-8859-1";
	
	@Autowired
	@Qualifier("binaryFiles")
	private List<String> binaryFile;
	
	private static Logger logger= LoggerFactory.getLogger(FTPProcessor.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		Item item = (Item)exchange.getIn().getHeader(MyCamelHeader.ITEM);
		String host = item.getFtpIp();
		String user = item.getFtpUsername();
		String password = item.getFtpPassword();
		String sourceFolder = item.getSourceFolder();
		String sourceFilename = (String)exchange.getIn().getHeader(Exchange.FILE_NAME);
		String targetFolder = item.getTargetFolder();
		String targetFilename = getTargetFilename(item, sourceFilename);
		boolean isPassiveMode = isPassiveMode(item);
		boolean isBinary = isBinary(sourceFilename);
		boolean ftpResultStatus = transmitFileWithFTP(host, user, password, sourceFolder, sourceFilename, targetFolder, targetFilename, isPassiveMode, isBinary);
		
		exchange.getIn().setHeader(MyCamelHeader.FTP_STATUS, ftpResultStatus);
		exchange.getIn().setHeader(MyCamelHeader.SOURCE_FOLDER, sourceFolder);
		exchange.getIn().setHeader(MyCamelHeader.SOURCE_FILENAME, sourceFilename);
		exchange.getIn().setHeader(MyCamelHeader.TARGET_FILENAME, targetFilename);
	}
	
	private boolean transmitFileWithFTP(String host, String username, String password
										, String sourceFolder, String sourceFilename, String targetFolder, String targetFilename
										, boolean isPassiveMode, boolean isBinary) {
	
		FTPClient ftpClient = new FTPClient();
		boolean ftpResultStatus = false;
		try {
			ftpClient.setAutodetectUTF8(true);
			ftpClient.setControlEncoding(UTF8_CHARSET);
			ftpClient.connect(host);
			if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				if (ftpClient.login(username, password)) {
					String remoteCharset = FTP_DEFAULT_CHARSET;
					String targetFilenameInEncoding = targetFilename;
					// turn on FTP to use UTF-8 encoding, if UTF-8 support can not be turned on, we use default FTP encoding  
					if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {
						
					} else {
						remoteCharset = FTP_DEFAULT_CHARSET;
						targetFilenameInEncoding = new String(targetFilename.getBytes(UTF8_CHARSET), remoteCharset);
					}
					// passiveMode or activeMode
					if(isPassiveMode) {
						ftpClient.enterLocalPassiveMode();
					}
					// if the file should be transfered in binary transmission type
					if(isBinary) {
						ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
					} else {
						ftpClient.setFileType(FTPClient.ASCII_FILE_TYPE);
					}
					// change directory
					if(targetFolder!= null && !targetFolder.isEmpty() && !targetFolder.equals("/")) { // need to change dir
						targetFolder = targetFolder.substring(1);
						if(ftpClient.changeWorkingDirectory(targetFolder)) {
							logger.info("Change " + host + " working directory to " + targetFolder + " succeed.");
						} else {
							logger.info("Change " + host + " working directory to " + targetFolder + " failed.");
						}
					} 
					// wirte file
					FileInputStream inputStream = new FileInputStream(Paths.get(MyPath.PARENT_FTP_SOURCE_FOLDER, sourceFolder, sourceFilename).toFile());
					ftpResultStatus = ftpClient.storeFile(targetFilenameInEncoding, inputStream);
					String sFtpResultStatus = ftpResultStatus ? "SUCCEED" : "FAILED";
					logger.info("Write " + targetFilename + " to " + host + " " + targetFolder + ": " + sFtpResultStatus + ".");
					inputStream.close();	
					ftpClient.logout();
					return ftpResultStatus;
				} else {
					logger.info("Can not log in to " + host + " with username " + username + ".");
					return ftpResultStatus;
				}
			} else {
				logger.info("Can not connect to " + host + ". Please check config/camel/ftpRule.xml file.");
				return ftpResultStatus;
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			return ftpResultStatus;
		} finally {
			if(ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					logger.info("Can not disconnect to Ftp Server " + host);
					logger.error(e.getMessage());
				} 
			}
		}
	}
	
	private boolean isPassiveMode(Item item) {
		if(item.getFtpOtherParameter().isPresent()) {
			return item.getFtpOtherParameter().get().contains("passiveMode=true");
		} else {
			return false;
		}
	}
	
	private boolean isBinary(String filename) {
		String extension = FilenameUtils.getExtension(filename);
		if(binaryFile.contains(extension)) {
			return true;
		}
		return false;
	} 
	
	private String getTargetFilename(Item item, String sourceFilename) {
		String targetFilename;
		if(!item.getFilenamePair().isEmpty()) {
			targetFilename = item.getFilenamePair().get(sourceFilename);
			if(targetFilename == null) {
				return sourceFilename;
			} else {
				return getTargetFilenameWithTime(targetFilename);
			}
		} else {
			return sourceFilename;
		}
	}
	
	private String getTargetFilenameWithTime(String ori) {
		if (ori.contains("${date:now:")) {
			int start = ori.lastIndexOf(":");
			int end = ori.lastIndexOf("}");
			String timePattern = ori.substring(start+1, end);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timePattern);
			String time = LocalDateTime.now().format(formatter);
			int sStart = ori.lastIndexOf("$");
			String prefix = ori.substring(0, sStart);
			String suffix = ori.substring(end+1, ori.length());
			return prefix + time + suffix;
		} else {
			return ori;
		} 
	} 

	
}
