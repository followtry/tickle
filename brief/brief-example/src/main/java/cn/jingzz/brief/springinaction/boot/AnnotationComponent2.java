/**
 * 
 */
package cn.jingzz.brief.springinaction.boot;

import javax.inject.Named;

import org.springframework.stereotype.Component;

/**
 * @author jingzz
 * @time 2016年9月18日 上午9:32:18
 * @name brief-service/cn.jingzz.brief.springinaction.AnnotationComponent
 * @since 2016年9月18日 上午9:32:18
 */
//两者自定义的名称要么只能有用一个，要么必须相同
@Component("ac2")
@Named

public class AnnotationComponent2 {
	
	public void play(){
		System.out.println("AnnotationComponent2.play()");
	}
}
