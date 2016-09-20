/**
 * 
 */
package cn.jingzztech.prac.springinaction.boot.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jingzz
 * @time 2016年9月18日 上午9:32:18
 * @name brief-service/cn.jingzz.brief.springinaction.AnnotationComponent
 * @since 2016年9月18日 上午9:32:18
 */
@Component("xmlComponent2")
public class XmlComponent2 {
	
	@Autowired
	private XmlComponent annotationComponent;
	
	public void play(){
		annotationComponent.play();
		System.out.println("ConfigComponent2.play()");
	}
}
