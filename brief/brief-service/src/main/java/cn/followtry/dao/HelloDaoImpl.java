/**
 * 
 */
package cn.followtry.dao;

import org.springframework.stereotype.Repository;

import cn.followtry.validation.base.stereotype.XRepository;

/**
 * @author jingzz
 * @time 2016年11月23日 下午3:20:50
 * @name brief-service/cn.followtry.service.HelloServiceInp
 * @since 2016年11月23日 下午3:20:50
 */
@Repository
@XRepository
public class HelloDaoImpl implements IHelloDao {

	@Override
	public String sayHello(String content) {

		return "hello " + content;
	}

}
