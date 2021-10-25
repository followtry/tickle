package cn.followtry.common.utils;

import cn.followtry.common.exception.JsonParseInvalidException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author followtry
 * @since 2021/10/25 8:51 下午
 */
public class JsonUtils {

    public static final ObjectMapper objectMapper = new ObjectMapper();


    public static <T> T parseObject(String value, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(value,typeReference);
        } catch (JsonProcessingException e) {
            throw new JsonParseInvalidException(e);
        }
    }

    public static String toJson(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new JsonParseInvalidException(e);
        }
    }
}
