/**
 * 
 */
package cn.followtry.incubate.springinaction.inject.runtime;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * 使用占位符方式在运行时注入值
 * @author jingzz
 * @time 2016年9月18日 上午9:32:18
 * @name brief-service/cn.jingzz.brief.springinaction.AnnotationComponent
 * @since 2016年9月18日 上午9:32:18
 */
public class InjectComponent2 {
	
	public static String testName; 
	
	
	@Value("${test2.name}")
	private String name;
	
	@Value("${test2.age}")
	private Integer age;
	
	@Value("${test2.isMale}")
	private Boolean isMale;
	
	//直接获取系统属性的集合
	@Value("#{systemProperties}")
	private Map<Object,Object> systemProperties;
	
	@Value("#{T(cn.jingzztech.prac.springinaction.inject.runtime.InjectComponent2).testName != null ? T(cn.jingzztech.prac.springinaction.inject.runtime.InjectComponent2).testName : 'woshijingzz'}")
	private String userName;
	
	//直接获取系统属性的集合
	@Value("#{systemProperties['file.encoding']}")
	private String fileEncoding;
	
	@Autowired
	private InjectComponent annotationComponent;
	
	public void play(){
		if (isMale) {
			System.out.println(name+"是一个男孩");
		}else {
			System.out.println(name+"是一个女孩");
		}
		if (age > 0) {
			System.out.println(name+"的年龄是1:"+age);
		}else{
			System.out.println(name+"的年龄是2:"+age);
		}
		annotationComponent.play();
		System.out.println("ConfigComponent2.play()");
		showSystemProperties();
	}
	
	private void showSystemProperties(){
		Set<Object> keySet = systemProperties.keySet();
		for (Object key : keySet) {
			System.out.println(key+":"+systemProperties.get(key));
		}
		System.out.println("用户名："+userName);
		System.out.println("文件编码："+fileEncoding);
	}
}
