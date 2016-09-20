/**
 * 
 */
package cn.jingzztech.prac.springinaction.boot.config;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jingzz
 * @time 2016年9月18日 上午9:32:18
 * @name brief-service/cn.jingzz.brief.springinaction.AnnotationComponent
 * @since 2016年9月18日 上午9:32:18
 */
public class ConfigComponent2 {
	
	@Autowired
	private ConfigComponent annotationComponent;
	
	public void play(){
		annotationComponent.play();
		System.out.println("ConfigComponent2.play()");
	}
}
