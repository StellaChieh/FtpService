//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2019.11.19 於 04:59:12 PM CST 
//


package com.iisi.cmt.ftpService.ftpRule.xml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.iisi.cmt.ftpService.ftpRule.xml package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FoldersFolderFileNameChangesFile_QNAME = new QName("", "file");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.iisi.cmt.ftpService.ftpRule.xml
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Folders }
     * 
     */
    public Folders createFolders() {
        return new Folders();
    }

    /**
     * Create an instance of {@link Folders.Folder }
     * 
     */
    public Folders.Folder createFoldersFolder() {
        return new Folders.Folder();
    }

    /**
     * Create an instance of {@link Folders.Folder.FileNameChanges }
     * 
     */
    public Folders.Folder.FileNameChanges createFoldersFolderFileNameChanges() {
        return new Folders.Folder.FileNameChanges();
    }

    /**
     * Create an instance of {@link Folders.Folder.FileNameChanges.File }
     * 
     */
    public Folders.Folder.FileNameChanges.File createFoldersFolderFileNameChangesFile() {
        return new Folders.Folder.FileNameChanges.File();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Folders.Folder.FileNameChanges.File }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "file", scope = Folders.Folder.FileNameChanges.class)
    public JAXBElement<Folders.Folder.FileNameChanges.File> createFoldersFolderFileNameChangesFile(Folders.Folder.FileNameChanges.File value) {
        return new JAXBElement<Folders.Folder.FileNameChanges.File>(_FoldersFolderFileNameChangesFile_QNAME, Folders.Folder.FileNameChanges.File.class, Folders.Folder.FileNameChanges.class, value);
    }

}
