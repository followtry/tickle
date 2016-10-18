/**
 * 
 */
package cn.followtry.mybatis;

import cn.followtry.mybatis.bean.User;

/**
 * @author jingzz
 * @time 2016年10月18日 下午3:01:35
 * @name brief-example/cn.jingzztech.prac.org.mybatis.UserMapper
 * @since 2016年10月18日 下午3:01:35
 */
public interface UserMapper {
	
	User getUserById(Long id);
}
