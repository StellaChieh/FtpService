//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2019.11.19 於 04:59:12 PM CST 
//


package com.iisi.cmt.ftpService.ftpRule.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type 的 Java 類別.
 * 
 * <p>下列綱要片段會指定此類別中包含的預期內容.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="folder" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="sourceFolder" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ftpIp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ftpTargetFolder" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ftpUsername" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ftpPassword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ftpOtherParameter" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="fileNameChanges">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="file" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="sourceFilename" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="targetFilename" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "folder"
})
@XmlRootElement(name = "folders")
public class Folders {

    protected List<Folders.Folder> folder;

    /**
     * Gets the value of the folder property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the folder property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFolder().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Folders.Folder }
     * 
     * 
     */
    public List<Folders.Folder> getFolder() {
        if (folder == null) {
            folder = new ArrayList<Folders.Folder>();
        }
        return this.folder;
    }


    /**
     * <p>anonymous complex type 的 Java 類別.
     * 
     * <p>下列綱要片段會指定此類別中包含的預期內容.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="sourceFolder" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ftpIp" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ftpTargetFolder" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ftpUsername" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ftpPassword" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ftpOtherParameter" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="fileNameChanges">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="file" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="sourceFilename" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="targetFilename" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "sourceFolder",
        "ftpIp",
        "ftpTargetFolder",
        "ftpUsername",
        "ftpPassword",
        "ftpOtherParameter",
        "fileNameChanges"
    })
    public static class Folder {

        @XmlElement(required = true)
        protected String sourceFolder;
        @XmlElement(required = true)
        protected String ftpIp;
        @XmlElement(required = true)
        protected String ftpTargetFolder;
        @XmlElement(required = true)
        protected String ftpUsername;
        @XmlElement(required = true)
        protected String ftpPassword;
        @XmlElement(required = true)
        protected String ftpOtherParameter;
        @XmlElement(required = true)
        protected Folders.Folder.FileNameChanges fileNameChanges;

        /**
         * 取得 sourceFolder 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSourceFolder() {
            return sourceFolder;
        }

        /**
         * 設定 sourceFolder 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSourceFolder(String value) {
            this.sourceFolder = value;
        }

        /**
         * 取得 ftpIp 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFtpIp() {
            return ftpIp;
        }

        /**
         * 設定 ftpIp 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFtpIp(String value) {
            this.ftpIp = value;
        }

        /**
         * 取得 ftpTargetFolder 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFtpTargetFolder() {
            return ftpTargetFolder;
        }

        /**
         * 設定 ftpTargetFolder 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFtpTargetFolder(String value) {
            this.ftpTargetFolder = value;
        }

        /**
         * 取得 ftpUsername 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFtpUsername() {
            return ftpUsername;
        }

        /**
         * 設定 ftpUsername 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFtpUsername(String value) {
            this.ftpUsername = value;
        }

        /**
         * 取得 ftpPassword 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFtpPassword() {
            return ftpPassword;
        }

        /**
         * 設定 ftpPassword 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFtpPassword(String value) {
            this.ftpPassword = value;
        }

        /**
         * 取得 ftpOtherParameter 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFtpOtherParameter() {
            return ftpOtherParameter;
        }

        /**
         * 設定 ftpOtherParameter 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFtpOtherParameter(String value) {
            this.ftpOtherParameter = value;
        }

        /**
         * 取得 fileNameChanges 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link Folders.Folder.FileNameChanges }
         *     
         */
        public Folders.Folder.FileNameChanges getFileNameChanges() {
            return fileNameChanges;
        }

        /**
         * 設定 fileNameChanges 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link Folders.Folder.FileNameChanges }
         *     
         */
        public void setFileNameChanges(Folders.Folder.FileNameChanges value) {
            this.fileNameChanges = value;
        }


        /**
         * <p>anonymous complex type 的 Java 類別.
         * 
         * <p>下列綱要片段會指定此類別中包含的預期內容.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="file" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="sourceFilename" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="targetFilename" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "content"
        })
        public static class FileNameChanges {

            @XmlElementRef(name = "file", type = JAXBElement.class, required = false)
            @XmlMixed
            protected List<Serializable> content;

            /**
             * Gets the value of the content property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the content property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getContent().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link JAXBElement }{@code <}{@link Folders.Folder.FileNameChanges.File }{@code >}
             * {@link String }
             * 
             * 
             */
            public List<Serializable> getContent() {
                if (content == null) {
                    content = new ArrayList<Serializable>();
                }
                return this.content;
            }


            /**
             * <p>anonymous complex type 的 Java 類別.
             * 
             * <p>下列綱要片段會指定此類別中包含的預期內容.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="sourceFilename" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="targetFilename" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "sourceFilename",
                "targetFilename"
            })
            public static class File {

                @XmlElement(required = true)
                protected String sourceFilename;
                @XmlElement(required = true)
                protected String targetFilename;

                /**
                 * 取得 sourceFilename 特性的值.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getSourceFilename() {
                    return sourceFilename;
                }

                /**
                 * 設定 sourceFilename 特性的值.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setSourceFilename(String value) {
                    this.sourceFilename = value;
                }

                /**
                 * 取得 targetFilename 特性的值.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getTargetFilename() {
                    return targetFilename;
                }

                /**
                 * 設定 targetFilename 特性的值.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setTargetFilename(String value) {
                    this.targetFilename = value;
                }

            }

        }

    }

}
