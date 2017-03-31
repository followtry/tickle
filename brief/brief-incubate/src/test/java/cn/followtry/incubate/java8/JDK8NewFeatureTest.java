package cn.followtry.incubate.java8;

import org.junit.Test;

/**
 * jdk8新特性测试用例
 * Created by followtry on 2017/3/27 0027.
 */
public class JDK8NewFeatureTest {


	@Test
	public void defaultMethod001(){
		DefaultMethodFeature dMethodFeature=new DefaultMethodFeature(){

			@Override
			public String display() {
				return "I'm a interface";
			}
		};
		String showme=dMethodFeature.showMe();
		String display=dMethodFeature.display();
		System.out.println(display);
		System.out.println(showme);
	}
}
