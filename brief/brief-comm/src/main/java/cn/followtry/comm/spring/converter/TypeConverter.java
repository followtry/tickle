package cn.followtry.comm.spring.converter;


import cn.followtry.comm.spring.BeanInvokeMethodParam;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: Class 转换器
 * 对于泛型的集合/对象需要单独处理
 * @author: haozhongweng
 * @create: 2018/6/29 12:00
 */
public class TypeConverter {

    private final Map<Class<?>, ITypeConverter<?>> converterMap = new HashMap<Class<?>, ITypeConverter<?>>();
    private final Map<String, Class<?>> stringToClassMap = new HashMap<>();

    private final static TypeConverter me = new TypeConverter();

    private TypeConverter() {
        // String的单独处理
        regist(Integer.class, new ConverterFactory.IntegerTypeConverter());
        regist(int.class, new ConverterFactory.IntegerTypeConverter());
        regist(Long.class, new ConverterFactory.LongTypeConverter());
        regist(long.class, new ConverterFactory.LongTypeConverter());
        regist(Double.class, new ConverterFactory.DoubleTypeConverter());
        regist(double.class, new ConverterFactory.DoubleTypeConverter());
        regist(Float.class, new ConverterFactory.FloatTypeConverter());
        regist(float.class, new ConverterFactory.FloatTypeConverter());
        regist(Boolean.class, new ConverterFactory.BooleanTypeConverter());
        regist(boolean.class, new ConverterFactory.BooleanTypeConverter());
        regist(java.util.Date.class, new ConverterFactory.DateTypeConverter());
        regist(java.sql.Date.class, new ConverterFactory.SqlDateTypeConverter());
        regist(java.sql.Time.class, new ConverterFactory.TimeTypeConverter());
        regist(java.sql.Timestamp.class, new ConverterFactory.TimestampTypeConverter());
        regist(java.math.BigDecimal.class, new ConverterFactory.BigDecimalTypeConverter());
        regist(java.math.BigInteger.class, new ConverterFactory.BigIntegerTypeConverter());
        regist(byte[].class, new ConverterFactory.ByteArrayTypeConverter());

        regist(Short.class, new ConverterFactory.ShortTypeConverter());
        regist(short.class, new ConverterFactory.ShortTypeConverter());
        regist(Byte.class, new ConverterFactory.ByteTypeConverter());
        regist(byte.class, new ConverterFactory.ByteTypeConverter());
        regist(Class.class, new ConverterFactory.ClassTypeConverter());
        // 带有泛型的集合、对象单独处理

        registStringClass("Integer", Integer.class);
        registStringClass("String", String.class);
        registStringClass("Long", Long.class);
        registStringClass("Double", Double.class);
        registStringClass("Float", Float.class);
        registStringClass("Boolean", Boolean.class);
        registStringClass("Short", Short.class);
        registStringClass("Byte", Byte.class);
        registStringClass("Class", Class.class);

        registStringClass("List", List.class);
        registStringClass("Set", Set.class);
        registStringClass("Map", Map.class);

        regist(AtomicInteger.class, new ConverterFactory.AtomicIntegerTypeConverter());
        regist(AtomicLong.class, new ConverterFactory.AtomicLongTypeConverter());
    }

    public static TypeConverter me() {
        return me;
    }

    public <T> void regist(Class<T> type, ITypeConverter<T> converter) {
        converterMap.put(type, converter);
    }

    public <T> void registStringClass(String s, Class<T> c) {
        stringToClassMap.put(s, c);
    }

    public Class<?> fetchTypeClass(String typeStr) throws ClassNotFoundException {
        Class<?> c = stringToClassMap.get(typeStr);
        if (Objects.isNull(c)) {
            c = Class.forName(typeStr);
        }
        return c;
    }

    /**
     * 将 String 数据转换为指定的类型
     *
     * @param type 需要转换成为的数据类型
     * @param s    被转换的 String 类型数据，注意： s 参数不接受 null 值，否则会抛出异常
     *
     * @return 转换成功的数据
     */
    private final Object convertBaseType(Class<?> type, String s) {

        if (StringUtils.isBlank(s) || s.trim().equals("null")) {
            return null;
        }
        
        //String.class提前处理
        if (type == String.class) {
            return StringUtils.isBlank(s) ? null : s;
        }

        ITypeConverter<?> converter = converterMap.get(type);
        if (converter != null) {
            // 普通类型
            try {
                return converter.convert(s);
            } catch (Exception e) {
                throw new RuntimeException("reflect convertBase error " + s + " " + e.getMessage());
            }

        } else {
            // 类型不匹配, 则认为是字符串
            return s;
        }
    }

    /**
     * PS: type 是基础类型，但是string 是json格式的字符串
     * @param type
     * @param s
     * @return
     */
    private final Object convertBaseJson(Class<?> type, String s) {

        if (type == String.class) {
            return StringUtils.isBlank(s) ? null : s;
        }

        ITypeConverter<?> converter = converterMap.get(type);
        if (converter != null) {
            // 普通类型
            try {
                return converter.convert(s);
            } catch (Exception e) {
                throw new RuntimeException("reflect convertBase error " + s + " " + e.getMessage());
            }

        } else if (Collection.class.isAssignableFrom(type)) {
            // 集合类
            List list = JSONArray.parseArray(s);
            return type.isAssignableFrom(Set.class) ? new HashSet<>(list) : list;

        } else {
            // Map or Object
            try {
                JSONObject jsonObject = JSONObject.parseObject(s);
                return jsonObject.toJavaObject(type);

            } catch (Exception e) {
                throw new RuntimeException("reflect for JSONObject error " + s + " " + e.getMessage());
            }
        }
    }


    public final Object convertCollect(Class<?> type, String s, Type typeParam) {

        if (Collection.class.isAssignableFrom(type)) {
            if (type.isAssignableFrom(Set.class)) {
                return JSON.<HashSet>parseObject(s, typeParam);
            } else if (type.isAssignableFrom(List.class)){
                return JSON.<List>parseObject(s, typeParam);
            } else {
                return JSON.parseObject(s, typeParam);
            }
        }
        throw new RuntimeException(String.format("type[%s] not from Collect", type.getName()));
    }

    public final Object convertParameterizedObj(Class<?> type, String s, Type typeParam) {

        if (Collection.class.isAssignableFrom(type)) {
            return convertCollect(type, s, typeParam);
        }
        return JSON.parseObject(s, typeParam);
    }


    public static Object convertJson(Class<?> type, String value, int cntRecursion) throws ClassNotFoundException {
        // 限制递归深度
        if (cntRecursion > 20) {
            throw new RuntimeException("recursion too deeply,now is "+cntRecursion);
        }
        Object targetParam = null;
        if (type.isEnum()) {
            for (Object constant : type.getEnumConstants()) {
                if (constant.toString().equalsIgnoreCase(value)) {
                    targetParam = constant;
                    break;
                }
            }
            if (targetParam == null) {
                throw new RuntimeException("reflect enum error: for class error " + value + " require: " + type);
            }
        } else if (notJsonString(value)) {
            // 非json格式 则是基础类型, 默认处理
            targetParam = TypeConverter.me().convertBaseType(type, value);

        } else {
            JSONObject jsonObject = JSONObject.parseObject(value);
            String clazz = jsonObject.getString("_class");

            if (Objects.isNull(clazz)) {
                // 没有自定义类型 则默认处理
                targetParam = TypeConverter.me().convertBaseJson(type, value);

            } else if (clazz.substring(clazz.lastIndexOf(".") + 1).startsWith("Atomic")) {
                //原子类型的数值操作
                String directValue = jsonObject.getString("_value");
                targetParam = TypeConverter.me().convertBaseJson(type, directValue);
            } else {
                // 采用自定义类型
                String directValue = jsonObject.getString("_value");
                BeanInvokeMethodParam mdlParam = new BeanInvokeMethodParam(clazz, directValue);
                Pair<ParameterizedType, Class> typeT2 = mdlParam.fetchParameterizedType();
                if (Objects.isNull(typeT2.getLeft())) {
                    // Class
                    Class customClazz = typeT2.getRight();
                    targetParam = convertJson(customClazz, mdlParam.getValue(), cntRecursion++);

                } else {
                    // 泛型Class, 采用定制的rawType
                    ParameterizedTypeImpl paramType = (ParameterizedTypeImpl) typeT2.getLeft();
                    Class rawType = paramType.getRawType();
                    targetParam = TypeConverter.me().convertParameterizedObj(rawType, mdlParam.getValue(), paramType);
                }
            }
        }
        return targetParam;
    }

    /**
     * 非严格检测
     *
     * @param str
     *
     * @return
     */
    public static boolean isJsonString(String str) {
        boolean result = false;
        if (StringUtils.isNotBlank(str)) {
            str = str.trim();
            if (str.startsWith("{") && str.endsWith("}")) {
                result = true;
            } else if (str.startsWith("[") && str.endsWith("]")) {
                result = true;
            }
        }
        return result;
    }

    public static boolean notJsonString(String stri) {
        return !isJsonString(stri);
    }
}







