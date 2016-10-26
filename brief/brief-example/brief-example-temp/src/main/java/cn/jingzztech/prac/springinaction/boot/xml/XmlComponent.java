/**
 * 
 */
package cn.jingzztech.prac.springinaction.boot.xml;

import org.springframework.stereotype.Component;

/**
 * @author jingzz
 * @time 2016年9月18日 上午9:32:18
 * @name brief-service/cn.jingzz.brief.springinaction.AnnotationComponent
 * @since 2016年9月18日 上午9:32:18
 */
@Component("xmlComponent")
public class XmlComponent {
	
	public void play(){
		System.out.println("ConfigComponent.play()");
	}
}
