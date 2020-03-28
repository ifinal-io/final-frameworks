package org.finalframework.json.jackson.serializer;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.finalframework.data.util.Enums;
import org.finalframework.data.annotation.EnumValue;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @author likly
 * @version 1.0
 * @date 2020-03-25 10:33:52
 * @since 1.0
 */
public class EnumValueDescSerializer extends JsonSerializer<Object> {

    private final EnumValue enumValue;

    public EnumValueDescSerializer(EnumValue enumValue) {
        this.enumValue = enumValue;
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
            return;
        }
        Enum anEnum = Enums.findEnum(enumValue, value);
        Field field = ReflectionUtils.findField(enumValue.value(), enumValue.desc());
        field.setAccessible(true);
        Object description = ReflectionUtils.getField(field, anEnum);
        gen.writeString(description.toString());
    }
}
