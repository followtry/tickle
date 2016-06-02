/**
 * 
 */
package cn.jingzz.brief.service.impl;

import org.springframework.stereotype.Service;

import cn.jingzz.brief.service.TestService;

/**
 * @author jingzz
 * @time 2016年6月2日 下午8:29:49
 * @name brief-service/cn.jingzz.brief.service.impl.TestServiceImpl
 * @since 2016年6月2日 下午8:29:49
 */
@Service
public class TestServiceImpl implements TestService {

	@Override
	public void test() {
		System.out.println("TestServiceImpl.Test()");
	}

}
