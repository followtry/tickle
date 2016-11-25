/**
 * 
 */
package cn.followtry.springboot.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.annotation.AnnotationBeanUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.web.servlet.MockMvc;

/**
 * mock测试
 * @author jingzz
 * @time 2016年10月14日 下午3:01:58
 * @name brief-springboot/cn.followtry.springboot.controller.TestHelloController
 * @since 2016年10月14日 下午3:01:58
 */
public class TestHelloController {
	
	@Test
	public void testHelloPage() throws Exception{
		HelloController helloController = new HelloController();
		MockMvc mockmvc = standaloneSetup(helloController).build();
		mockmvc.perform(get("/")).andReturn().equals("this is my app end-point");
	}
	
	public static void main(String[] args) {
		Method[] methods = TestHelloController.class.getMethods();
		for (Method method : methods) {
			if (method.getAnnotations() != null) {
				Annotation[] annotations = AnnotationUtils.getAnnotations(method);
				for (Annotation annotation : annotations) {
					System.out.println(annotation.getClass().getName());
				}
			}
		}
	}
}