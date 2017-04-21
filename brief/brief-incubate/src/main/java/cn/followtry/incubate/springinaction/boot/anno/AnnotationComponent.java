
package cn.followtry.incubate.springinaction.boot.anno;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * 注解.
 * @author jingzz
 * @since 2016年9月18日 上午9:32:18
 */
@Component(MyComponentScan.ANNOTATION_COMPONENT_BEAN_NAME)
//可以使用basePackageClasses 扫描标记接口，避免对实际应用代码的依赖
//@ComponentScan(value="cn.jingzz.brief.springinaction")
//使用标记接口扫描接口所在的包及其所有子包
@ComponentScan(basePackageClasses = MyComponentScan.class)
public class AnnotationComponent {

  public void play() {
    System.out.println("AnnotationComponent.play()");
  }
}
