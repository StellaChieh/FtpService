package com.iisi.cmt.ftpService.config;

import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.apache.camel.builder.DeadLetterChannelBuilder;
import org.apache.camel.processor.errorhandler.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.iisi.cmt.ftpService.ftpRule.xml.Folders;
import com.iisi.cmt.ftpService.ftpRule.xml.Item;
import com.iisi.cmt.ftpService.utility.MyPath;
import com.iisi.cmt.ftpService.utility.MyUnmarshaller;
import com.iisi.cmt.ftpService.utility.XmlWrongFormatException;

@Configuration
public class MySpringConfiguration {

	@Value("${app.file.binary.extension}")
	public List<String> binaryFiles;

	@Bean(name="binaryFiles")
	List<String> binaryFiles() {
		return binaryFiles;
	}
	
	
	@Bean
	List<Item> getItems() throws XmlWrongFormatException {
		Optional<Folders> folders = MyUnmarshaller.unmarshall(new java.io.File(
				Paths.get(MyPath.MONITOR_DIR, MyPath.FTP_RULE_FILE).toString()));
		if(folders.isPresent()) {
			return MyUnmarshaller.getItems(folders.get());
		} else {
			throw new XmlWrongFormatException(MyPath.FTP_RULE_FILE + " format is wrong.");
		}
	}
	
	@Bean
	DeadLetterChannelBuilder deadLetterChannelBuilder() {
		RedeliveryPolicy policy = new RedeliveryPolicy();
		// Sets whether retry attempts should be logged or not
		policy.setLogRetryAttempted(true);
		policy.setMaximumRedeliveries(3);
		// Sets the initial redelivery delay in milliseconds
//		policy.setRedeliveryDelay(10); 
//		policy.setRedeliveryDelay(10000);
		DeadLetterChannelBuilder dlcb = new DeadLetterChannelBuilder();
//		dlcb.useOriginalMessage();
		dlcb.setRedeliveryPolicy(policy);
		dlcb.logStackTrace(true);
		dlcb.setDeadLetterUri(MyCamelRouteBuilder.DIRECT_FTP_STATUS);
		return dlcb;
	}
	
}
