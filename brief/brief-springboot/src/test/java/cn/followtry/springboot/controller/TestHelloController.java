package cn.followtry.springboot.controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.junit.Test;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.web.servlet.MockMvc;

/**
 * mock测试.
 *
 * @author jingzz
 * @since 2016年10月14日 下午3:01:58
 */
public class TestHelloController {

  @Test
  public void testHelloPage() throws Exception {
    HelloController helloController = new HelloController();
    MockMvc mockmvc =
            org.springframework.test.web.servlet.setup.MockMvcBuilders
                    .standaloneSetup(helloController).build();
    mockmvc.perform(
            org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/"))
            .andReturn().equals("this is my app end-point");
  }

  /**
   * main.
   */
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
