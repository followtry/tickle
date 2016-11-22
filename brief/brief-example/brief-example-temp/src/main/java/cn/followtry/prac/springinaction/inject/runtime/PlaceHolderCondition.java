/**
 * 
 */
package cn.followtry.prac.springinaction.inject.runtime;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author jingzz
 * @time 2016年9月20日 下午2:11:20
 * @name brief-example/cn.jingzztech.prac.springinaction.inject.runtime.PlaceHolderCondition
 * @since 2016年9月20日 下午2:11:20
 */
public class PlaceHolderCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		PropertySourcesPlaceholderConfigurer bean = context.getBeanFactory().getBean(PropertySourcesPlaceholderConfigurer.class);
		if (bean != null) {
			return true;
		}
		return false;
	}

}
