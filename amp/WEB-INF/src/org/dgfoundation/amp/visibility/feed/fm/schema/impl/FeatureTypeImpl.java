//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.07.04 at 11:30:59 PM EEST 
//


package org.dgfoundation.amp.visibility.feed.fm.schema.impl;

public class FeatureTypeImpl implements org.dgfoundation.amp.visibility.feed.fm.schema.FeatureType, com.sun.xml.bind.JAXBObject, org.dgfoundation.amp.visibility.feed.fm.schema.impl.runtime.UnmarshallableObject, org.dgfoundation.amp.visibility.feed.fm.schema.impl.runtime.XMLSerializable, org.dgfoundation.amp.visibility.feed.fm.schema.impl.runtime.ValidatableObject
{

    protected boolean has_Visible;
    protected boolean _Visible;
    protected com.sun.xml.bind.util.ListImpl _Field;
    protected java.lang.String _Name;
    public final static java.lang.Class version = (org.dgfoundation.amp.visibility.feed.fm.schema.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (org.dgfoundation.amp.visibility.feed.fm.schema.FeatureType.class);
    }

    public boolean isVisible() {
        return _Visible;
    }

    public void setVisible(boolean value) {
        _Visible = value;
        has_Visible = true;
    }

    protected com.sun.xml.bind.util.ListImpl _getField() {
        if (_Field == null) {
            _Field = new com.sun.xml.bind.util.ListImpl(new java.util.ArrayList());
        }
        return _Field;
    }

    public java.util.List getField() {
        return _getField();
    }

    public java.lang.String getName() {
        return _Name;
    }

    public void setName(java.lang.String value) {
        _Name = value;
    }

    public org.dgfoundation.amp.visibility.feed.fm.schema.impl.runtime.UnmarshallingEventHandler createUnmarshaller(org.dgfoundation.amp.visibility.feed.fm.schema.impl.runtime.UnmarshallingContext context) {
        return new org.dgfoundation.amp.visibility.feed.fm.schema.impl.FeatureTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(org.dgfoundation.amp.visibility.feed.fm.schema.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_Visible) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "Visible"));
        }
        int idx2 = 0;
        final int len2 = ((_Field == null)? 0 :_Field.size());
        while (idx2 != len2) {
            context.startElement("http://dgfoundation.org/amp/visibility/feed/fm/schema.xml", "field");
            int idx_0 = idx2;
            context.childAsURIs(((com.sun.xml.bind.JAXBObject) _Field.get(idx_0 ++)), "Field");
            context.endNamespaceDecls();
            int idx_1 = idx2;
            context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _Field.get(idx_1 ++)), "Field");
            context.endAttributes();
            context.childAsBody(((com.sun.xml.bind.JAXBObject) _Field.get(idx2 ++)), "Field");
            context.endElement();
        }
    }

    public void serializeAttributes(org.dgfoundation.amp.visibility.feed.fm.schema.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_Visible) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "Visible"));
        }
        int idx2 = 0;
        final int len2 = ((_Field == null)? 0 :_Field.size());
        context.startAttribute("", "name");
        try {
            context.text(((java.lang.String) _Name), "Name");
        } catch (java.lang.Exception e) {
            org.dgfoundation.amp.visibility.feed.fm.schema.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endAttribute();
        context.startAttribute("", "visible");
        try {
            context.text(javax.xml.bind.DatatypeConverter.printBoolean(((boolean) _Visible)), "Visible");
        } catch (java.lang.Exception e) {
            org.dgfoundation.amp.visibility.feed.fm.schema.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endAttribute();
        while (idx2 != len2) {
            idx2 += 1;
        }
    }

    public void serializeURIs(org.dgfoundation.amp.visibility.feed.fm.schema.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_Visible) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "Visible"));
        }
        int idx2 = 0;
        final int len2 = ((_Field == null)? 0 :_Field.size());
        while (idx2 != len2) {
            idx2 += 1;
        }
    }

    public java.lang.Class getPrimaryInterface() {
        return (org.dgfoundation.amp.visibility.feed.fm.schema.FeatureType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
+"expandedExpq\u0000~\u0000\u0002xpppsq\u0000~\u0000\u0000ppsr\u0000\u001dcom.sun.msv.grammar.ChoiceEx"
+"p\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsr\u0000 com.sun.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.msv.grammar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000"
+"\u0002xq\u0000~\u0000\u0003sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000\'com.s"
+"un.msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001f"
+"Lcom/sun/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv.grammar.Eleme"
+"ntExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000\fcontentModel"
+"q\u0000~\u0000\u0002xq\u0000~\u0000\u0003q\u0000~\u0000\rp\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\u000epp\u0000sq\u0000~\u0000\u0007ppsq\u0000~\u0000\tq\u0000~\u0000\rpsr\u0000 c"
+"om.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tname"
+"Classq\u0000~\u0000\u000fxq\u0000~\u0000\u0003q\u0000~\u0000\rpsr\u00002com.sun.msv.grammar.Expression$Any"
+"StringExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003sq\u0000~\u0000\f\u0001psr\u0000 com.sun.msv.gra"
+"mmar.AnyNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun.msv.grammar.NameClas"
+"s\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.sun.msv.grammar.Expression$EpsilonExpr"
+"ession\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003q\u0000~\u0000\u001apsr\u0000#com.sun.msv.grammar.SimpleN"
+"ameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNamet\u0000\u0012Ljava/lang/String;L\u0000\fnames"
+"paceURIq\u0000~\u0000!xq\u0000~\u0000\u001ct\u00008org.dgfoundation.amp.visibility.feed.fm"
+".schema.FieldTypet\u0000+http://java.sun.com/jaxb/xjc/dummy-eleme"
+"ntssq\u0000~\u0000\u0007ppsq\u0000~\u0000\u0016q\u0000~\u0000\rpsr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000"
+"\u0004namet\u0000\u001dLcom/sun/msv/util/StringPair;xq\u0000~\u0000\u0003ppsr\u0000\"com.sun.msv"
+".datatype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.x"
+"sd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd."
+"ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDataty"
+"peImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUriq\u0000~\u0000!L\u0000\btypeNameq\u0000~\u0000!L\u0000\nwhit"
+"eSpacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceProcessor;xpt\u0000 "
+"http://www.w3.org/2001/XMLSchemat\u0000\u0005QNamesr\u00005com.sun.msv.data"
+"type.xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun."
+"msv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.sun"
+".msv.grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003pp"
+"sr\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000!L"
+"\u0000\fnamespaceURIq\u0000~\u0000!xpq\u0000~\u00002q\u0000~\u00001sq\u0000~\u0000 t\u0000\u0004typet\u0000)http://www.w3"
+".org/2001/XMLSchema-instanceq\u0000~\u0000\u001fsq\u0000~\u0000 t\u0000\u0005fieldt\u00009http://dgf"
+"oundation.org/amp/visibility/feed/fm/schema.xmlq\u0000~\u0000\u001fsq\u0000~\u0000\u0016pp"
+"sq\u0000~\u0000\'ppsr\u0000#com.sun.msv.datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000"
+"\risAlwaysValidxq\u0000~\u0000,q\u0000~\u00001t\u0000\u0006stringsr\u00005com.sun.msv.datatype.x"
+"sd.WhiteSpaceProcessor$Preserve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u00004\u0001q\u0000~\u00007sq\u0000~\u00008"
+"q\u0000~\u0000Dq\u0000~\u00001sq\u0000~\u0000 t\u0000\u0004namet\u0000\u0000sq\u0000~\u0000\u0016ppsq\u0000~\u0000\'ppsr\u0000$com.sun.msv.da"
+"tatype.xsd.BooleanType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000,q\u0000~\u00001t\u0000\u0007booleanq\u0000~\u00005q"
+"\u0000~\u00007sq\u0000~\u00008q\u0000~\u0000Oq\u0000~\u00001sq\u0000~\u0000 t\u0000\u0007visibleq\u0000~\u0000Jsr\u0000\"com.sun.msv.gra"
+"mmar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/gra"
+"mmar/ExpressionPool$ClosedHash;xpsr\u0000-com.sun.msv.grammar.Exp"
+"ressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006"
+"parentt\u0000$Lcom/sun/msv/grammar/ExpressionPool;xp\u0000\u0000\u0000\b\u0001pq\u0000~\u0000\u0015q\u0000"
+"~\u0000\u000bq\u0000~\u0000%q\u0000~\u0000\u0006q\u0000~\u0000\u0012q\u0000~\u0000\bq\u0000~\u0000\u0005q\u0000~\u0000\u0014x"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends org.dgfoundation.amp.visibility.feed.fm.schema.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(org.dgfoundation.amp.visibility.feed.fm.schema.impl.runtime.UnmarshallingContext context) {
            super(context, "----------");
        }

        protected Unmarshaller(org.dgfoundation.amp.visibility.feed.fm.schema.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return org.dgfoundation.amp.visibility.feed.fm.schema.impl.FeatureTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  0 :
                        attIdx = context.getAttribute("", "name");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 3;
                            eatText1(v);
                            continue outer;
                        }
                        break;
                    case  9 :
                        if (("field" == ___local)&&("http://dgfoundation.org/amp/visibility/feed/fm/schema.xml" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 7;
                            return ;
                        }
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  6 :
                        if (("field" == ___local)&&("http://dgfoundation.org/amp/visibility/feed/fm/schema.xml" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 7;
                            return ;
                        }
                        state = 9;
                        continue outer;
                    case  3 :
                        attIdx = context.getAttribute("", "visible");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 6;
                            eatText2(v);
                            continue outer;
                        }
                        break;
                    case  7 :
                        attIdx = context.getAttribute("", "visible");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().enterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        break;
                }
                super.enterElement(___uri, ___local, ___qname, __atts);
                break;
            }
        }

        private void eatText1(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Name = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Visible = javax.xml.bind.DatatypeConverter.parseBoolean(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
                has_Visible = true;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        public void leaveElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  0 :
                        attIdx = context.getAttribute("", "name");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 3;
                            eatText1(v);
                            continue outer;
                        }
                        break;
                    case  9 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  8 :
                        if (("field" == ___local)&&("http://dgfoundation.org/amp/visibility/feed/fm/schema.xml" == ___uri)) {
                            context.popAttributes();
                            state = 9;
                            return ;
                        }
                        break;
                    case  6 :
                        state = 9;
                        continue outer;
                    case  3 :
                        attIdx = context.getAttribute("", "visible");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 6;
                            eatText2(v);
                            continue outer;
                        }
                        break;
                    case  7 :
                        attIdx = context.getAttribute("", "visible");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveElement(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                }
                super.leaveElement(___uri, ___local, ___qname);
                break;
            }
        }

        public void enterAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  0 :
                        if (("name" == ___local)&&("" == ___uri)) {
                            state = 1;
                            return ;
                        }
                        break;
                    case  9 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  6 :
                        state = 9;
                        continue outer;
                    case  3 :
                        if (("visible" == ___local)&&("" == ___uri)) {
                            state = 4;
                            return ;
                        }
                        break;
                    case  7 :
                        if (("visible" == ___local)&&("" == ___uri)) {
                            _getField().add(((org.dgfoundation.amp.visibility.feed.fm.schema.impl.FieldTypeImpl) spawnChildFromEnterAttribute((org.dgfoundation.amp.visibility.feed.fm.schema.impl.FieldTypeImpl.class), 8, ___uri, ___local, ___qname)));
                            return ;
                        }
                        break;
                }
                super.enterAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void leaveAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  0 :
                        attIdx = context.getAttribute("", "name");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 3;
                            eatText1(v);
                            continue outer;
                        }
                        break;
                    case  9 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  2 :
                        if (("name" == ___local)&&("" == ___uri)) {
                            state = 3;
                            return ;
                        }
                        break;
                    case  5 :
                        if (("visible" == ___local)&&("" == ___uri)) {
                            state = 6;
                            return ;
                        }
                        break;
                    case  6 :
                        state = 9;
                        continue outer;
                    case  3 :
                        attIdx = context.getAttribute("", "visible");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            state = 6;
                            eatText2(v);
                            continue outer;
                        }
                        break;
                    case  7 :
                        attIdx = context.getAttribute("", "visible");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveAttribute(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                }
                super.leaveAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void handleText(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                try {
                    switch (state) {
                        case  0 :
                            attIdx = context.getAttribute("", "name");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                state = 3;
                                eatText1(v);
                                continue outer;
                            }
                            break;
                        case  9 :
                            revertToParentFromText(value);
                            return ;
                        case  6 :
                            state = 9;
                            continue outer;
                        case  1 :
                            state = 2;
                            eatText1(value);
                            return ;
                        case  3 :
                            attIdx = context.getAttribute("", "visible");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                state = 6;
                                eatText2(v);
                                continue outer;
                            }
                            break;
                        case  4 :
                            state = 5;
                            eatText2(value);
                            return ;
                        case  7 :
                            attIdx = context.getAttribute("", "visible");
                            if (attIdx >= 0) {
                                context.consumeAttribute(attIdx);
                                context.getCurrentHandler().text(value);
                                return ;
                            }
                            break;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

    }

}
