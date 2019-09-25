package cn.followtry.comm.spring;

import cn.followtry.comm.util.ApplicationUtil;
import cn.followtry.comm.util.ReflectUtil;
import cn.followtry.comm.util.ServletUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 注：Spring启动时需注入ApplicationUtil
 *
 */
public class SpringService {

    private static final Logger log = LoggerFactory.getLogger(SpringService.class);

    @SuppressWarnings("unchecked")
    public static Object callBeanMethod(String bean, String method, HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Object beanObj = ApplicationUtil.getBean(bean);

        Object target;
        try {
            target = getTarget(beanObj);
        } catch (Exception e) {
            log.error("getTarget error", e);
            return e.getMessage();
        }
        if (target == null) {
            target = beanObj;
        }

        Method[] beanMethods = target.getClass().getDeclaredMethods();
        List<Method> runMethods = new ArrayList<>();
        for (Method m : beanMethods) {
            if (StringUtils.equals(m.getName(), method) && m.getParameters().length == parameterMap.size()) {
                runMethods.add(m);
            }
        }
        if (runMethods.size() == 0) {
            return "error: runMethod " + method + " not found";
        }
        Map<String, String> paramMap = ServletUtil.getParamMap(parameterMap);
        Method runMethod = runMethods.get(0);
        //有多态方法
        if (runMethods.size() > 1) {
            for (Method m : runMethods) {
                ArrayList<String> params = new ArrayList<>(paramMap.values());
                Parameter[] methods = m.getParameters();
                for (int i = 0; i < methods.length ; i++) {
                    Parameter parameter = methods[i];
                    Class<?> type = parameter.getType();
                    String json = params.get(i);
                    try {
                        JSONObject jsonObject = JSONObject.parseObject(json);
                        if (type != Object.class && type != Class.class && jsonObject.containsKey("_class")) {
                            if (!StringUtils.equals( type.getName(), jsonObject.getString("_class"))) {
                                continue;
                            }
                        }
                        runMethod = m;
                    } catch (Exception ignored) {
                    }
                }
            }
        }

        try {
            Object[] params = ReflectUtil.getMethodParams(runMethod, paramMap);
            runMethod.setAccessible(true);
            return runMethod.invoke(target, params);
        } catch (InvocationTargetException e) {
            log.error("Invocation error: ", e.getTargetException());
            return "Invocation error: " + e.getTargetException();
        } catch (Exception e) {
            log.error("spring bean run error: ", e);
            return "spring bean run error: " + e;
        }
    }

    public static Object getTarget(Object proxy) throws Exception {
        if (!AopUtils.isAopProxy(proxy)) {
            return proxy;//不是代理对象
        }
        if (AopUtils.isJdkDynamicProxy(proxy)) {
            return getJdkDynamicProxyTargetObject(proxy);
        } else { //cglib
            return getCglibProxyTargetObject(proxy);
        }
    }

    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        return ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
    }


    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);
        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        return ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
    }
}
