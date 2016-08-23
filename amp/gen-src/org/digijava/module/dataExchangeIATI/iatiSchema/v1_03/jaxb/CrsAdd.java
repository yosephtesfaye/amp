//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.23 at 07:03:31 PM EEST 
//


package org.digijava.module.dataExchangeIATI.iatiSchema.v1_03.jaxb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="aidtype-flag">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute ref="{}code use="required""/>
 *                 &lt;attribute name="significance" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;anyAttribute processContents='lax' namespace='##other'/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="loan-terms">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="repayment-type" type="{}codeType"/>
 *                   &lt;element name="repayment-plan" type="{}codeType"/>
 *                   &lt;element name="commitment-date" type="{}dateType"/>
 *                   &lt;element name="repayment-first-date" type="{}dateType"/>
 *                   &lt;element name="repayment-final-date" type="{}dateType"/>
 *                   &lt;any processContents='lax' namespace='##other'/>
 *                 &lt;/choice>
 *                 &lt;attribute name="rate-1" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *                 &lt;attribute name="rate-2" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *                 &lt;anyAttribute processContents='lax' namespace='##other'/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="loan-status">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="interest-received" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="principal-outstanding" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="principal-arrears" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="interest-arrears" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;any processContents='lax' namespace='##other'/>
 *                 &lt;/choice>
 *                 &lt;attribute name="year" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *                 &lt;attribute ref="{}currency"/>
 *                 &lt;attribute ref="{}value-date use="required""/>
 *                 &lt;anyAttribute processContents='lax' namespace='##other'/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;any processContents='lax' namespace='##other'/>
 *       &lt;/choice>
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "aidtypeFlagOrLoanTermsOrLoanStatus"
})
@XmlRootElement(name = "crs-add")
public class CrsAdd {

    @XmlElementRefs({
        @XmlElementRef(name = "aidtype-flag", type = JAXBElement.class),
        @XmlElementRef(name = "loan-status", type = JAXBElement.class),
        @XmlElementRef(name = "loan-terms", type = JAXBElement.class)
    })
    @XmlAnyElement(lax = true)
    protected List<Object> aidtypeFlagOrLoanTermsOrLoanStatus;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the aidtypeFlagOrLoanTermsOrLoanStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aidtypeFlagOrLoanTermsOrLoanStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAidtypeFlagOrLoanTermsOrLoanStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link CrsAdd.AidtypeFlag }{@code >}
     * {@link Object }
     * {@link JAXBElement }{@code <}{@link CrsAdd.LoanTerms }{@code >}
     * {@link Element }
     * {@link JAXBElement }{@code <}{@link CrsAdd.LoanStatus }{@code >}
     * 
     * 
     */
    public List<Object> getAidtypeFlagOrLoanTermsOrLoanStatus() {
        if (aidtypeFlagOrLoanTermsOrLoanStatus == null) {
            aidtypeFlagOrLoanTermsOrLoanStatus = new ArrayList<Object>();
        }
        return this.aidtypeFlagOrLoanTermsOrLoanStatus;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
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
     *       &lt;sequence>
     *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute ref="{}code use="required""/>
     *       &lt;attribute name="significance" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *       &lt;anyAttribute processContents='lax' namespace='##other'/>
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
    public static class AidtypeFlag {

        @XmlMixed
        @XmlAnyElement(lax = true)
        protected List<Object> content;
        @XmlAttribute(name = "code", required = true)
        protected String code;
        @XmlAttribute(name = "significance", required = true)
        protected boolean significance;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

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
         * {@link Object }
         * {@link String }
         * {@link Element }
         * 
         * 
         */
        public List<Object> getContent() {
            if (content == null) {
                content = new ArrayList<Object>();
            }
            return this.content;
        }

        /**
         * Gets the value of the code property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCode() {
            return code;
        }

        /**
         * Sets the value of the code property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCode(String value) {
            this.code = value;
        }

        /**
         * Gets the value of the significance property.
         * 
         */
        public boolean isSignificance() {
            return significance;
        }

        /**
         * Sets the value of the significance property.
         * 
         */
        public void setSignificance(boolean value) {
            this.significance = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         * 
         * <p>
         * the map is keyed by the name of the attribute and 
         * the value is the string value of the attribute.
         * 
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         * 
         * 
         * @return
         *     always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

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
     *       &lt;choice maxOccurs="unbounded" minOccurs="0">
     *         &lt;element name="interest-received" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="principal-outstanding" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="principal-arrears" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="interest-arrears" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;any processContents='lax' namespace='##other'/>
     *       &lt;/choice>
     *       &lt;attribute name="year" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
     *       &lt;attribute ref="{}currency"/>
     *       &lt;attribute ref="{}value-date use="required""/>
     *       &lt;anyAttribute processContents='lax' namespace='##other'/>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "interestReceivedOrPrincipalOutstandingOrPrincipalArrears"
    })
    public static class LoanStatus {

        @XmlElementRefs({
            @XmlElementRef(name = "principal-arrears", type = JAXBElement.class),
            @XmlElementRef(name = "principal-outstanding", type = JAXBElement.class),
            @XmlElementRef(name = "interest-arrears", type = JAXBElement.class),
            @XmlElementRef(name = "interest-received", type = JAXBElement.class)
        })
        @XmlAnyElement(lax = true)
        protected List<Object> interestReceivedOrPrincipalOutstandingOrPrincipalArrears;
        @XmlAttribute(name = "year", required = true)
        protected BigDecimal year;
        @XmlAttribute(name = "currency")
        protected String currency;
        @XmlAttribute(name = "value-date", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar valueDate;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the interestReceivedOrPrincipalOutstandingOrPrincipalArrears property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the interestReceivedOrPrincipalOutstandingOrPrincipalArrears property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInterestReceivedOrPrincipalOutstandingOrPrincipalArrears().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
         * {@link Object }
         * {@link Element }
         * 
         * 
         */
        public List<Object> getInterestReceivedOrPrincipalOutstandingOrPrincipalArrears() {
            if (interestReceivedOrPrincipalOutstandingOrPrincipalArrears == null) {
                interestReceivedOrPrincipalOutstandingOrPrincipalArrears = new ArrayList<Object>();
            }
            return this.interestReceivedOrPrincipalOutstandingOrPrincipalArrears;
        }

        /**
         * Gets the value of the year property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getYear() {
            return year;
        }

        /**
         * Sets the value of the year property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setYear(BigDecimal value) {
            this.year = value;
        }

        /**
         * Gets the value of the currency property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCurrency() {
            return currency;
        }

        /**
         * Sets the value of the currency property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCurrency(String value) {
            this.currency = value;
        }

        /**
         * Gets the value of the valueDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getValueDate() {
            return valueDate;
        }

        /**
         * Sets the value of the valueDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setValueDate(XMLGregorianCalendar value) {
            this.valueDate = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         * 
         * <p>
         * the map is keyed by the name of the attribute and 
         * the value is the string value of the attribute.
         * 
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         * 
         * 
         * @return
         *     always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

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
     *       &lt;choice maxOccurs="unbounded" minOccurs="0">
     *         &lt;element name="repayment-type" type="{}codeType"/>
     *         &lt;element name="repayment-plan" type="{}codeType"/>
     *         &lt;element name="commitment-date" type="{}dateType"/>
     *         &lt;element name="repayment-first-date" type="{}dateType"/>
     *         &lt;element name="repayment-final-date" type="{}dateType"/>
     *         &lt;any processContents='lax' namespace='##other'/>
     *       &lt;/choice>
     *       &lt;attribute name="rate-1" type="{http://www.w3.org/2001/XMLSchema}decimal" />
     *       &lt;attribute name="rate-2" type="{http://www.w3.org/2001/XMLSchema}decimal" />
     *       &lt;anyAttribute processContents='lax' namespace='##other'/>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "repaymentTypeOrRepaymentPlanOrCommitmentDate"
    })
    public static class LoanTerms {

        @XmlElementRefs({
            @XmlElementRef(name = "repayment-final-date", type = JAXBElement.class),
            @XmlElementRef(name = "repayment-first-date", type = JAXBElement.class),
            @XmlElementRef(name = "commitment-date", type = JAXBElement.class),
            @XmlElementRef(name = "repayment-plan", type = JAXBElement.class),
            @XmlElementRef(name = "repayment-type", type = JAXBElement.class)
        })
        @XmlAnyElement(lax = true)
        protected List<Object> repaymentTypeOrRepaymentPlanOrCommitmentDate;
        @XmlAttribute(name = "rate-1")
        protected BigDecimal rate1;
        @XmlAttribute(name = "rate-2")
        protected BigDecimal rate2;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the repaymentTypeOrRepaymentPlanOrCommitmentDate property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the repaymentTypeOrRepaymentPlanOrCommitmentDate property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRepaymentTypeOrRepaymentPlanOrCommitmentDate().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link JAXBElement }{@code <}{@link DateType }{@code >}
         * {@link JAXBElement }{@code <}{@link DateType }{@code >}
         * {@link JAXBElement }{@code <}{@link DateType }{@code >}
         * {@link JAXBElement }{@code <}{@link CodeType }{@code >}
         * {@link Object }
         * {@link JAXBElement }{@code <}{@link CodeType }{@code >}
         * {@link Element }
         * 
         * 
         */
        public List<Object> getRepaymentTypeOrRepaymentPlanOrCommitmentDate() {
            if (repaymentTypeOrRepaymentPlanOrCommitmentDate == null) {
                repaymentTypeOrRepaymentPlanOrCommitmentDate = new ArrayList<Object>();
            }
            return this.repaymentTypeOrRepaymentPlanOrCommitmentDate;
        }

        /**
         * Gets the value of the rate1 property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getRate1() {
            return rate1;
        }

        /**
         * Sets the value of the rate1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setRate1(BigDecimal value) {
            this.rate1 = value;
        }

        /**
         * Gets the value of the rate2 property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getRate2() {
            return rate2;
        }

        /**
         * Sets the value of the rate2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setRate2(BigDecimal value) {
            this.rate2 = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         * 
         * <p>
         * the map is keyed by the name of the attribute and 
         * the value is the string value of the attribute.
         * 
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         * 
         * 
         * @return
         *     always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

    }

}
