package cn.followtry.custom.spring.tag;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import java.text.SimpleDateFormat;

/**
 * 当NameSpaceHander遇到xml元素类型映射到了指定的bean定义解析器时，对应的BeanDefinitionParser就会被使用。
 * 在解析时，我们可以访问到XMl元素，因此我们可以解析到自定义的xml的内容
 * Created by followtry on 2017/3/15.
 */
public class SimpleDateFormatBeanDefinitionParser extends AbstractSingleBeanDefinitionParser{

	//指定需要实例化的bean的name
    @Override
	protected Class<?> getBeanClass(Element element) {
		return SimpleDateFormat.class;
	}

	//解析命名空间下的自定义xml元素
    @Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {
		String pattern=element.getAttribute("pattern");
		builder.addConstructorArgValue(pattern);

		String lenient=element.getAttribute("lenient");
		if (StringUtils.hasText(lenient)){
			builder.addPropertyValue("lenient",lenient);
		}
	}
}
