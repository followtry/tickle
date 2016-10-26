/**
 * 
 */
package cn.followtry.dubbo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.followtry.dubbo.api.UserService;
import cn.followtry.dubbo.bean.User;

/**
 * @author jingzz
 * @time 2016年10月26日 上午11:17:47
 * @name brief-dubbo-customer/cn.followtry.dubbo.customer.ServiceHandler
 * @since 2016年10月26日 上午11:17:47
 */
@Service
public class ServiceHandler {
	
	@Autowired
	@Qualifier(value="userService")
	private UserService userService;
	
	public User getUser(String id){
		User userById = userService.getUserById(id);
		return userById;
	}
}
