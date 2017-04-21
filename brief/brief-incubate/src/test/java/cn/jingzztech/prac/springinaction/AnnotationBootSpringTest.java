package cn.jingzztech.prac.springinaction;

import cn.followtry.incubate.springinaction.boot.anno.AnnotationComponent2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * 测试{@link AnnotationComponent2}.
 *
 * @author jingzz
 * @since 2016年9月18日 上午9:45:18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AnnotationComponent2.class)
public class AnnotationBootSpringTest {

  @Autowired
  private AnnotationComponent2 component2;

  /**
   * 测试AnnotationComponent2是否注入成功.
   */
  @Test
  public void testAnnotationComponent2() {
    assertNotNull(component2);
  }
}
