package com.iisi.cmt.ftpService.processor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iisi.cmt.ftpService.utility.MyCamelHeader;
import com.iisi.cmt.ftpService.utility.MyDao;
import com.iisi.cmt.ftpService.utility.MyPath;

@Component
public class FileBackupProcessor implements Processor {

	@Autowired
	private MyDao myDao;
	
	private static Logger logger= LoggerFactory.getLogger(FileBackupProcessor.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		String sourceFilePath = (String) exchange.getIn().getHeader(MyCamelHeader.LOCAL_ABSOLUTE_PATH);
		String targetFilename = (String) exchange.getIn().getHeader(MyCamelHeader.TARGET_FILENAME);
		String sourceFolder = (String) exchange.getIn().getHeader(MyCamelHeader.SOURCE_FOLDER);
		boolean status = getFtpStatus(exchange); 
		LocalDateTime updatetime = (LocalDateTime)exchange.getIn().getHeader(MyCamelHeader.UPDATETIME);
		long dbRecordIndex = (Long)exchange.getIn().getHeader(MyCamelHeader.DB_RECORD_INDEX);
		
		Path targetFilePath = status ? 
				Paths.get(MyPath.BACKUP_DONE_FOLDER, sourceFolder, getDateFolder(updatetime), targetFilename) :
				Paths.get(MyPath.BACKUP_FAILED_FOLDER, sourceFolder, getDateFolder(updatetime), targetFilename);
		
		Files.createDirectories(targetFilePath.getParent());
		Files.copy(Paths.get(sourceFilePath), targetFilePath, StandardCopyOption.REPLACE_EXISTING);
		logger.info("Backup " + Paths.get(sourceFilePath).toAbsolutePath() + " to " + targetFilePath.toAbsolutePath());
		
		// "\" and "\\" can not wirte to db. Change to use "/" since this app will deploy on Linux.
		String operatinSystemBackPath = FilenameUtils.separatorsToUnix(targetFilePath.toAbsolutePath().toString());
		myDao.updateBackupPath(dbRecordIndex, operatinSystemBackPath);
		
	}
	
	private String getDateFolder(LocalDateTime updatetime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		return updatetime.format(formatter);
	}
	
	private boolean getFtpStatus(Exchange exchange) {
		if ((exchange.getIn().getHeader(MyCamelHeader.FTP_STATUS) == null) || 
			!(Boolean)(exchange.getIn().getHeader(MyCamelHeader.FTP_STATUS)) ) {
			return false;
		}	
		
		return true;
	}

}
