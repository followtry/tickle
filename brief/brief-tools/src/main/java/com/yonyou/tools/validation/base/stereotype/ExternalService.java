package com.yonyou.tools.validation.base.stereotype;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Service;

/**
 * ExternalService 用于标识一个服务是对外部系统调用的封装；
 * 
 * @author haiq
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface ExternalService {
	
	String name() default("");
	
}
