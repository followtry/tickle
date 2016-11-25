/**
 * 
 */
package cn.followtry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.followtry.dao.IHelloDao;
import cn.followtry.validation.base.stereotype.XService;

/**
 * @author jingzz
 * @time 2016年11月23日 下午3:20:50
 * @name brief-service/cn.followtry.service.HelloServiceInp
 * @since 2016年11月23日 下午3:20:50
 */
@Service
@XService
public class HelloServiceImpl implements IHello {
	
	@Autowired
	private IHelloDao helloDao;
	
	@Override
	public String sayHello(String content) {
		String sayHello = helloDao.sayHello(content);
		return sayHello;
	}

}
