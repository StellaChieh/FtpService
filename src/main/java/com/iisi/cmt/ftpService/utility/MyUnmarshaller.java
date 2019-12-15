package com.iisi.cmt.ftpService.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iisi.cmt.ftpService.ftpRule.xml.Folders;
import com.iisi.cmt.ftpService.ftpRule.xml.Item;

public class MyUnmarshaller {

	private static JAXBContext jaxbContext;
	
	private static Logger logger= LoggerFactory.getLogger(MyUnmarshaller.class);
	
	static public Optional<Folders> unmarshall(File file) {
		try {
			jaxbContext = JAXBContext.newInstance(Folders.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return Optional.of((Folders)jaxbUnmarshaller.unmarshal(file));
		} catch (JAXBException e) {
			logger.warn(e.getMessage());
			return Optional.empty();
		}
	}
	
	public static List<Item> getItems(Folders folders) throws XmlWrongFormatException {
		
		List<Item> items = new ArrayList<>();
		
		try {
			folders.getFolder().forEach(folder -> {
				Optional<String> ftpOtherParameter = folder.getFtpOtherParameter() == null || folder.getFtpOtherParameter().isEmpty() ? 
														Optional.empty() : Optional.of(folder.getFtpOtherParameter());  
				Item item = new Item(folder.getSourceFolder(), folder.getFtpIp(), folder.getFtpTargetFolder(), folder.getFtpUsername(), folder.getFtpPassword(), ftpOtherParameter);
				
				folder.getFileNameChanges().getContent().forEach(content -> {
					// in case some source folders do not change filename, there will be no <file></file> element 
					if(content.getClass() == JAXBElement.class) { // check if there are any file element
						@SuppressWarnings("unchecked")
						JAXBElement<Folders.Folder.FileNameChanges.File> jaxbFile = (JAXBElement<Folders.Folder.FileNameChanges.File>)content;  // cast serialize to JAXBElement
						Folders.Folder.FileNameChanges.File xmlFile = jaxbFile.getValue();
						item.getFilenamePair().put(xmlFile.getSourceFilename(), xmlFile.getTargetFilename());
					}
				});
				
				items.add(item);
			});
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new XmlWrongFormatException(MyPath.FTP_RULE_FILE + " format is wrong.");
		}
		return items;
	
	}

}
