package cn.followtry.common.utils;

import cn.followtry.common.exception.JsonException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static java.time.format.DateTimeFormatter.ofPattern;

/**
 *
 *
 * @author jingzhongzhi
 * @since 2022/9/22
 */
public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static final DateTimeFormatter FORMATTER = ofPattern("yyyy-MM-dd HH:mm:ss");

    private JsonUtil() {
    }

    static {
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MAPPER.enable(WRITE_DATES_AS_TIMESTAMPS);
        MAPPER.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        MAPPER.enable(DeserializationFeature.USE_LONG_FOR_INTS);

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateSerializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateDeserializer());

        MAPPER.registerModule(javaTimeModule);
    }

    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        try {
            return MAPPER.readValue(jsonString, clazz);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <T> List<T> fromJson(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return Lists.newArrayList();
        }
        try {
            return MAPPER.readValue(jsonString, new TypeReference<List<T>>() {
            });
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <T> List<T> fromJsonList(String jsonString, Class<T> clazz) {
        if (StringUtils.isBlank(jsonString)) {
            return Lists.newArrayList();
        }
        try {
            CollectionType t = MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);
            return MAPPER.readValue(jsonString, t);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <T> Map<String, T> fromJsonToMap(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return Maps.newHashMap();
        }
        try {
            return MAPPER.readValue(jsonString, new TypeReference<Map<String, T>>() {
            });
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <T> T fromJson(String jsonString, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return MAPPER.readValue(jsonString, typeReference);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    static class LocalDateSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value == null) {
                return;
            }
            gen.writeNumber(value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        }
    }

    static class LocalDateDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return LocalDateTime.parse(p.getValueAsString(), FORMATTER);
        }
    }

}
