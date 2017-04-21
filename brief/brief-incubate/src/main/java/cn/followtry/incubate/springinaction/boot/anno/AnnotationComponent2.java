package cn.followtry.incubate.springinaction.boot.anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 注解组件.
 *
 * @author jingzz
 * @since 2016年9月18日 上午9:32:18
 */
//两者自定义的名称要么只能有用一个，要么必须相同
@Component("annotationComponent2")
public class AnnotationComponent2 {

  @Autowired
  private AnnotationComponent annotationComponent;

  public void play() {
    annotationComponent.play();
    System.out.println("AnnotationComponent2.play()");
  }
}
