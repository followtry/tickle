/**
 * 
 */
package cn.followtry.dubbo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.followtry.dubbo.api.UserService;
import cn.followtry.dubbo.bean.User;

/**
 * user服务的实现
 * @author jingzz
 * @time 2016年10月26日 上午11:20:28
 * @name brief-dubbo-service/cn.followtry.dubbo.impl.UserServiceImpl
 * @since 2016年10月26日 上午11:20:28
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	public UserServiceImpl() {
		LOGGER.info("开始初始化 UserServiceImpl.UserServiceImpl()");
	}

	@Override
	public User getUserById(String id) {
		User user = new User();
		if (id != null && !"".equals(id)) {
			user.setId(id);
			user.setName("hello world");
			return user; 
		}
		user.setName("user is not found");
		return user;
	}

}
