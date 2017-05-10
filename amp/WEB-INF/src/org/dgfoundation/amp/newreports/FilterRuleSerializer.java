package org.dgfoundation.amp.newreports;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.std.SerializerBase;
import org.digijava.module.aim.util.AmpMath;

/**
 * @author Octavian Ciubotaru
 */
public class FilterRuleSerializer extends SerializerBase<FilterRule> {

    public FilterRuleSerializer() {
        super(FilterRule.class);
    }

    @Override
    public void serialize(FilterRule rule, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        switch (rule.filterType) {
        case SINGLE_VALUE:
            writeValue(rule, jgen);
            break;
        case VALUES:
            writeValues(rule, jgen);
            break;
        case RANGE:
            writeRange(rule, jgen);
            break;
        default:
            throw new RuntimeException("Unsupported filter type: " + rule.filterType);
        }
    }

    private void writeValue(FilterRule rule, JsonGenerator jgen) throws IOException {
        writeCorrectType(rule.value, jgen);
    }

    private void writeValues(FilterRule rule, JsonGenerator jgen) throws IOException {
        if (rule.values == null) {
            jgen.writeNull();
        } else {
            jgen.writeStartArray();
            for (String value : rule.values) {
                writeCorrectType(value, jgen);
            }
            jgen.writeEndArray();
        }
    }

    private void writeCorrectType(String value, JsonGenerator jgen) throws IOException {
        if (value.chars().allMatch(Character::isDigit)) {
            if (AmpMath.isLong(value)) {
                jgen.writeNumber(Long.valueOf(value));
            } else {
                jgen.writeNumber(Integer.valueOf(value));
            }
        } else {
            jgen.writeString(value);
        }
    }

    private void writeCorrectTypeField(String name, String value, JsonGenerator jgen) throws IOException {
        if (value.chars().allMatch(Character::isDigit)) {
            if (AmpMath.isLong(value)) {
                jgen.writeNumberField(name, Long.valueOf(value));
            } else {
                jgen.writeNumberField(name, Integer.valueOf(value));
            }
        } else {
            jgen.writeStringField(name, value);
        }
    }

    private void writeRange(FilterRule rule, JsonGenerator jgen) throws IOException {
        if (rule.min == null && rule.max == null) {
            jgen.writeNull();
        } else {
            jgen.writeStartObject();
            if (rule.min != null && rule.valueToName.get(rule.min) != null) {
                writeCorrectTypeField("start", rule.valueToName.get(rule.min), jgen);
            }
            if (rule.max != null && rule.valueToName.get(rule.max) != null) {
                writeCorrectTypeField("end", rule.valueToName.get(rule.max), jgen);
            }
            jgen.writeEndObject();
        }
    }
}