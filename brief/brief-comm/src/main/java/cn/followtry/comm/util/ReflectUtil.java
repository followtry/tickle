package cn.followtry.comm.util;

import cn.followtry.comm.spring.converter.TypeConverter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReflectUtil {
    private static final Logger logger = LoggerFactory.getLogger(ReflectUtil.class);

    public static Object getFieldValue(Object object, String fieldName) {
        Object obj = null;
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), object.getClass());
            Method method = pd.getReadMethod();
            obj = method.invoke(object);
        } catch (Exception e) {
            logger.error("通过反射获取字段失败", e);
        }
        return obj;
    }

    public static void setFieldValue(Object object, Class cls, String fieldName, Object value) {
        try {
            Field field = cls.getDeclaredField(fieldName);
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), cls);
            Method method = pd.getWriteMethod();
            method.invoke(object, value);
        } catch (Exception e) {
            logger.error("通过反射设置字段失败", e);
        }
    }

    public static String getMethodShortName(Method method) {
        String clazzName = method.getDeclaringClass().getName();
        if (StringUtils.contains(clazzName, ".")) {
            clazzName = StringUtils.substring(clazzName, StringUtils.lastIndexOf(clazzName, ".") + 1);
        }
        if (StringUtils.contains(clazzName, "$")) {
            clazzName = StringUtils.substring(clazzName, 0, StringUtils.indexOf(clazzName, "$"));
        }
        return clazzName + "." + method.getName();
    }

    public static Object invokeMethod(Object object, Method method, Map<String, String> params) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        return method.invoke(object, getMethodParams(method, params));
    }

    public static Object[] getMethodParams(Method method, Map<String, String> params) throws ClassNotFoundException {
        Parameter[] parameters = method.getParameters();
        List<Object> methodParams = new ArrayList<>(parameters.length);

        for (Parameter parameter : parameters) {
            String value = params.get(parameter.getName());
            if (value == null) {
                throw new RuntimeException("reflect error: param " + parameter.getName() + " not found");
            }
            Object targetParam = getParamByType(parameter.getType(), value);
            methodParams.add(targetParam);
        }
        return methodParams.toArray();
    }

    @SuppressWarnings("unchecked")
    private static Object getParamByType(Class<?> type, String value) throws ClassNotFoundException {

        Object targetParam = TypeConverter.convertJson(type, value, 1);
        return targetParam;
    }
}
