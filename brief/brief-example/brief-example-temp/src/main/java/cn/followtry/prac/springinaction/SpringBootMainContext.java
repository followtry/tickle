/**
 * 
 */
package cn.followtry.prac.springinaction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.followtry.prac.springinaction.boot.anno.AnnotationComponent;
import cn.followtry.prac.springinaction.boot.anno.AnnotationComponent2;
import cn.followtry.prac.springinaction.boot.config.ConfigComponent;
import cn.followtry.prac.springinaction.boot.config.ConfigComponent2;
import cn.followtry.prac.springinaction.boot.config.ConfigInject;
import cn.followtry.prac.springinaction.boot.xml.XmlComponent;
import cn.followtry.prac.springinaction.boot.xml.XmlComponent2;

/**
 * 启动spring的方式
 * 
 * @author jingzz
 * @time 2016年9月18日 上午9:34:19
 * @name brief-service/cn.jingzz.brief.springinaction.SpringMainContext
 * @since 2016年9月18日 上午9:34:19
 */
public class SpringBootMainContext {

	public static void main(String[] args) {
		boolean multiExecute = false;
		
		if (multiExecute) {
			BootType[] values = BootType.values();
			for (BootType type : values) {
				executeSpringBoot(type);
			}
		}else {
			BootType bootType = BootType.XML_CLASSPATH;
			executeSpringBoot(bootType);
			
		}
		

	}

	/**
	 * 根据指定类型执行不同方式的spring启动
	 * @author jingzz
	 * @param bootType
	 */
	protected static void executeSpringBoot(BootType bootType) {
		switch (bootType) {
		case ANNOTATION:
			// 注解方式注入bean
			annotationBootSpring();
			break;
		case CONFIG:
			// java显式配置方式注入bean
			javaConfigBootSpring();
			break;
		case XML_FS:
			// xml方式注入bean
			xmlBootSpring(BootType.XML_FS);
			break;
		case XML_CLASSPATH:
			// xml方式注入bean
			xmlBootSpring(BootType.XML_CLASSPATH);
			break;

		default:
			break;
		}
	}

	/**
	 * @author jingzz
	 */
	@SuppressWarnings("resource")
	private static void xmlBootSpring(BootType bootType) {
		if (BootType.XML_FS.equals(bootType)) {
			String configLocation = "D:\\open-source-project\\mycode\\practice\\tickle\\brief\\brief-example\\src\\main\\resources\\applicationContext.xml";
			ApplicationContext context = new FileSystemXmlApplicationContext(configLocation);
			XmlComponent bean = context.getBean(XmlComponent.class);
			System.out.println("SpringBootMainContext.xmlBootSpring(classpath1)");
			bean.play();
			XmlComponent2 bean2 = context.getBean(XmlComponent2.class);
			bean2.play();
		}else if (BootType.XML_CLASSPATH.equals(bootType)) {
			String configLocation = "classpath*:applicationContext.xml";
			ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
			XmlComponent bean = context.getBean(XmlComponent.class);
			System.out.println("SpringBootMainContext.xmlBootSpring(classpath2)");
			bean.play();
			XmlComponent2 bean2 = context.getBean(XmlComponent2.class);
			bean2.play();
		}
	}

	/**
	 * 该方式通过引入注解的类获取spring的上下文，如果该类中有@{@link ComponentScan}
	 * 注解，会根据该注解的value扫描指定base-package下的注解了的类
	 * 
	 * @author jingzz
	 */
	@SuppressWarnings("resource")
	protected static void annotationBootSpring() {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationComponent.class);
		AnnotationComponent bean = context.getBean(AnnotationComponent.class);
		bean.play();
		AnnotationComponent2 bean2 = context.getBean(AnnotationComponent2.class);
		bean2.play();
	}

	/**
	 * 通过显式通过java代码方式注入bean，根据@{@link Configuration}注入实例
	 * 
	 * @author jingzz
	 */
	@SuppressWarnings("resource")
	protected static void javaConfigBootSpring() {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigInject.class);
		ConfigComponent bean = context.getBean(ConfigComponent.class);
		bean.play();
		ConfigComponent2 bean2 = context.getBean(ConfigComponent2.class);
		bean2.play();
	}
	
	
}
