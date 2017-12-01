package cn.followtry.custom.spring.tag;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by followtry on 2017/3/15.
 */
public class SimpleDateFormatNameSpaceHander extends NamespaceHandlerSupport{
    @Override
	public void init() {
		//此处可以注册多个Bean的解析器，在需要解析命名空间中的元素时将会代理的解析器
		/*
			这样清晰的关注点的分离可以使得一个Namespacehandler可以处理解析命名空间内所有自定义元素的编排。
			它会将繁重的xml解析工作代理给指定的BeanDefinitionParsers，
			这意味着每个BeanDefinitionParserker将会包含只解析单个自定义元素的逻辑。
		 */
		registerBeanDefinitionParser("dateformat",new SimpleDateFormatBeanDefinitionParser());
	}
}
