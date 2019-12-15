package com.iisi.cmt.ftpService.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.camel.builder.DeadLetterChannelBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iisi.cmt.ftpService.ftpRule.xml.Item;
import com.iisi.cmt.ftpService.processor.FTPProcessor;
import com.iisi.cmt.ftpService.processor.FileBackupProcessor;
import com.iisi.cmt.ftpService.processor.FtpStatusProcessor;
import com.iisi.cmt.ftpService.utility.MyCamelHeader;
import com.iisi.cmt.ftpService.utility.MyPath;

@Component
public class MyCamelRouteBuilder extends RouteBuilder {

	
	public final static String DIRECT_FTP_STATUS = "direct:ftpStatus";  
	private final static String DIRECT_FILE_BACKUP = "direct:fileBackup";
	private final static String DIRECT_LOG_ERROR = "direct:logError";
	
	@Autowired
	private FTPProcessor ftpProcessor;
	
	@Autowired
	private FtpStatusProcessor ftpStatusProcessor;
	
	@Autowired
	private FileBackupProcessor fileBackupProcessor;
	
	@Autowired
	private List<Item> itemList;
	
	
	@Autowired
	private DeadLetterChannelBuilder deadLetterChannelBuilder;
	
	public MyCamelRouteBuilder(List<Item> itemList) {
		this.itemList = itemList;
	}
		
	@Override
	public void configure() throws Exception {
		
		// global error handling
		errorHandler(deadLetterChannel(DIRECT_LOG_ERROR));
		
		from(DIRECT_LOG_ERROR).to("log:" + "com.iisi.cmt.ftpService.config.MyCamelRouteBuilder" + "?level=DEBUG");

		itemList.forEach(item -> {
			// delete file after all routes finish
			from("file:" + MyPath.PARENT_FTP_SOURCE_FOLDER + "/" + item.getSourceFolder() + "?delete=true")  
			.errorHandler(deadLetterChannelBuilder)
			.setHeader(MyCamelHeader.ITEM, ()-> item)
			.log("${file:name} arrived at " + item.getSourceFolder() + " folder.")
			.process(ftpProcessor)
			.to(DIRECT_FTP_STATUS);   
		});
		
		// record ftp result
		from(DIRECT_FTP_STATUS).process(ftpStatusProcessor).to(DIRECT_FILE_BACKUP);
		
		// move to DONE/FAILED folder;
		from(DIRECT_FILE_BACKUP).process(fileBackupProcessor);
	
	}
	
	@PostConstruct // called after spring bean is initiated
	private void cleanFtpSourceFolder() {
		if(Files.exists(Paths.get(MyPath.PARENT_FTP_SOURCE_FOLDER))) {
			try {
				Files.walk(Paths.get(MyPath.PARENT_FTP_SOURCE_FOLDER).toAbsolutePath())
					.map(Path::toFile)
					.filter(file-> file.isDirectory() && file.list().length == 0)
					.forEach(File::delete);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	 

}
