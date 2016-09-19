/**
 * 
 */
package cn.jingzz.brief.springinaction.boot;

import javax.inject.Named;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import cn.jingzz.brief.springinaction.MyComponentScan;

/**
 * @author jingzz
 * @time 2016年9月18日 上午9:32:18
 * @name brief-service/cn.jingzz.brief.springinaction.AnnotationComponent
 * @since 2016年9月18日 上午9:32:18
 */
@Component("test")
//可以使用basePackageClasses 扫描标记接口，避免对实际应用代码的依赖
//@ComponentScan(value="cn.jingzz.brief.springinaction")
//使用标记接口扫描接口所在的包及其所有子包
@ComponentScan(basePackageClasses=MyComponentScan.class)
public class AnnotationComponent {
	
	public void play(){
		System.out.println("AnnotationComponent.play()");
	}
}
