package com.iisi.cmt.ftpService.processor;

import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iisi.cmt.ftpService.utility.MyCamelHeader;
import com.iisi.cmt.ftpService.utility.MyDao;

@Component
public class FtpStatusProcessor implements Processor {

	@Autowired
	private MyDao myDao;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		LocalDateTime now = LocalDateTime.now();
	
		String sourceFolder = (String) exchange.getIn().getHeader(MyCamelHeader.SOURCE_FOLDER);
		String sourceFilename = (String) exchange.getIn().getHeader(MyCamelHeader.SOURCE_FILENAME);
		String filename = (String) exchange.getIn().getHeader(MyCamelHeader.TARGET_FILENAME);
		boolean status = getFtpStatus(exchange);
		String extension = FilenameUtils.getExtension(filename);
		long dbRecordIndex;
		
		if (status) {
			dbRecordIndex = myDao.writeFtpSuccess(filename, now, sourceFolder, sourceFilename, extension);
		} else {
			dbRecordIndex = myDao.writeFtpFailed(filename, now, sourceFolder, sourceFilename, extension);
		}
		
		exchange.getIn().setHeader(MyCamelHeader.UPDATETIME, now);
		exchange.getIn().setHeader(MyCamelHeader.DB_RECORD_INDEX, dbRecordIndex);
	}
	
	private boolean getFtpStatus(Exchange exchange) {
		if ((exchange.getIn().getHeader(MyCamelHeader.FTP_STATUS) == null) || 
			!(Boolean)(exchange.getIn().getHeader(MyCamelHeader.FTP_STATUS)) ) {
			return false;
		}	
		return true;
	}
	
}
