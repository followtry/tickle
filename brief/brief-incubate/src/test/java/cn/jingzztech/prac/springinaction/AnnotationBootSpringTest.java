/**
 * 
 */
package cn.jingzztech.prac.springinaction;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.followtry.incubate.springinaction.boot.anno.AnnotationComponent2;

/**
 * 测试{@link AnnotationComponent2}
 * @author jingzz
 * @time 2016年9月18日 上午9:45:18
 * @name brief-service/cn.jingzz.brief.springinaction.AnnotationBootSpringTest
 * @since 2016年9月18日 上午9:45:18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AnnotationComponent2.class)
public class AnnotationBootSpringTest {
	
	@Autowired
	private AnnotationComponent2 aComponent2;
	
	/**
	 * 测试AnnotationComponent2是否注入成功
	 * @author jingzz
	 */
	@Test
	public void testAnnotationComponent2(){
		assertNotNull(aComponent2);
	}
}
