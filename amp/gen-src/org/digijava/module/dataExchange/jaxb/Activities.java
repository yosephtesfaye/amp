//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.23 at 07:03:29 PM EEST 
//


package org.digijava.module.dataExchange.jaxb;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="activity" type="{}activityType" maxOccurs="unbounded"/>
 *         &lt;element name="spider" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="next_block" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *                 &lt;attribute name="maxBlockSize" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *                 &lt;attribute name="startWith" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="startAfter" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    "activity",
    "spider"
})
@XmlRootElement(name = "activities")
public class Activities {

    @XmlElement(required = true)
    protected List<ActivityType> activity;
    protected Activities.Spider spider;

    /**
     * Gets the value of the activity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the activity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActivity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActivityType }
     * 
     * 
     */
    public List<ActivityType> getActivity() {
        if (activity == null) {
            activity = new ArrayList<ActivityType>();
        }
        return this.activity;
    }

    /**
     * Gets the value of the spider property.
     * 
     * @return
     *     possible object is
     *     {@link Activities.Spider }
     *     
     */
    public Activities.Spider getSpider() {
        return spider;
    }

    /**
     * Sets the value of the spider property.
     * 
     * @param value
     *     allowed object is
     *     {@link Activities.Spider }
     *     
     */
    public void setSpider(Activities.Spider value) {
        this.spider = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="next_block" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
     *       &lt;attribute name="maxBlockSize" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
     *       &lt;attribute name="startWith" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="startAfter" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Spider {

        @XmlAttribute(name = "next_block")
        @XmlSchemaType(name = "anyURI")
        protected String nextBlock;
        @XmlAttribute(name = "maxBlockSize")
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger maxBlockSize;
        @XmlAttribute(name = "startWith")
        protected String startWith;
        @XmlAttribute(name = "startAfter")
        protected String startAfter;

        /**
         * Gets the value of the nextBlock property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNextBlock() {
            return nextBlock;
        }

        /**
         * Sets the value of the nextBlock property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNextBlock(String value) {
            this.nextBlock = value;
        }

        /**
         * Gets the value of the maxBlockSize property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getMaxBlockSize() {
            return maxBlockSize;
        }

        /**
         * Sets the value of the maxBlockSize property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setMaxBlockSize(BigInteger value) {
            this.maxBlockSize = value;
        }

        /**
         * Gets the value of the startWith property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStartWith() {
            return startWith;
        }

        /**
         * Sets the value of the startWith property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStartWith(String value) {
            this.startWith = value;
        }

        /**
         * Gets the value of the startAfter property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStartAfter() {
            return startAfter;
        }

        /**
         * Sets the value of the startAfter property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStartAfter(String value) {
            this.startAfter = value;
        }

    }

}
