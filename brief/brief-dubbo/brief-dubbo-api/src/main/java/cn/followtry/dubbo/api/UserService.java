/**
 * 
 */
package cn.followtry.dubbo.api;

import cn.followtry.dubbo.bean.User;

/**
 * @author jingzz
 * @time 2016年10月26日 上午11:12:38
 * @name brief-dubbo-api/cn.followtry.dubbo.api.UserService
 * @since 2016年10月26日 上午11:12:38
 */
public interface UserService {
	
	User getUserById(String id);
}
