package cn.followtry.springboot.springinaction.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 验证切面.
 *
 * @author jingzz
 * @since 2016年10月12日 上午11:18:16
 */
@Aspect
@Component
public class Audience {

  private static final String pointCutPoint = "execution(** cn.followtry.springboot"
          + ".springinaction.aop.Performance.perform(..))";

  @Before(pointCutPoint)
  public void before() {
    System.out.println("Audience.Before()");
  }

  @Before(pointCutPoint)
  public void before2() {
    System.out.println("Audience.Before()2");
  }

  @AfterReturning(pointCutPoint)
  public void afterReturning() {
    System.out.println("Audience.AfterReturning()");
  }

  @AfterThrowing(pointCutPoint)
  public void afterThrowing() {
    System.out.println("Audience.AfterThrowing()");
  }
}
