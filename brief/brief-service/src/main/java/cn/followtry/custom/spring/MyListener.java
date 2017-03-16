package cn.followtry.custom.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/1/18.
 */
public class MyListener implements BeanPostProcessor,InitializingBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyListener.class);

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
		if (null != methods) for (Method method : methods) {
			RequestMapping annotation = AnnotationUtils.findAnnotation(method, RequestMapping.class);
			if (null != annotation){
				LOGGER.info("自定义的控制器【{}】", bean.getClass().getCanonicalName());
			}
		}
		return bean;
	}

	public MyListener(){
		LOGGER.info("");
		System.out.println("cn.followtry.custom.MyListener.MyListener()");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		LOGGER.info("");
		System.out.println("cn.followtry.custom.MyListener.afterPropertiesSet()");
	}
}