//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.23 at 07:03:33 PM EEST 
//


package org.digijava.module.xmlpatcher.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for conditionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="conditionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="custom"/>
 *     &lt;enumeration value="dbName"/>
 *     &lt;enumeration value="appliedPatch"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "conditionType")
@XmlEnum
public enum ConditionType {


    /**
     * The most generic condition type, to which all the
     * 						rest transform to.
     * 						It provides definition space for a series of variables defined using
     * 						script entities plus
     * 						a test. The test entity is a BSH expression that can make use of
     * 						the previously defined variables     				
     *     				
     * 
     */
    @XmlEnumValue("custom")
    CUSTOM("custom"),

    /**
     * This condition type tests if the current schema
     * 						(database) name equals the specify value. This condition works for
     * 						any type of database in AMP as it has specific callers
     * 						to identify the current schema based on which database is currently
     * 						used.
     * 
     */
    @XmlEnumValue("dbName")
    DB_NAME("dbName"),

    /**
     * This condition tests if the specified patch file
     * 						name has already been applied.
     * 						Bear in mind the patcher will try to re-apply all non-applied patches
     * 						until either all patches are applied or no patch has been applied
     * 						in the previous call due to restrictions
     * 
     */
    @XmlEnumValue("appliedPatch")
    APPLIED_PATCH("appliedPatch");
    private final String value;

    ConditionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ConditionType fromValue(String v) {
        for (ConditionType c: ConditionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
