//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.4-b18-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.07.15 at 03:49:20 GMT+04:00 
//


package org.digijava.module.message.jaxb;


/**
 * Java content class for anonymous complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/C:/java/PowerDot/source/harvester2/src/conf/org/digijava/module/message/amp-message-templates.xsd line 4)
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TemplatesList" type="{}templatesList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface MessagingType {


    /**
     * Gets the value of the templatesList property.
     * 
     * @return
     *     possible object is
     *     {@link org.digijava.module.message.jaxb.TemplatesList}
     */
    org.digijava.module.message.jaxb.TemplatesList getTemplatesList();

    /**
     * Sets the value of the templatesList property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.digijava.module.message.jaxb.TemplatesList}
     */
    void setTemplatesList(org.digijava.module.message.jaxb.TemplatesList value);

}
