/**
 * 
 */
package cn.followtry.mybatis.xml.mapper;

import cn.followtry.mybatis.bean.ParamDO;
import cn.followtry.mybatis.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jingzz
 * @time 2016年10月18日 下午3:01:35
 * @name brief-example/cn.jingzztech.prac.org.mybatis.UserMapper
 * @since 2016年10月18日 下午3:01:35
 */
public interface UserMapper {
	
	/**
	 * 获取用户信息
	 * @param id id
	 * @param name 名称
	 * @return
	 */
	User getUserById(@Param("id") Long id,@Param("name") String name,@Param("user")User user);

	int insert(User user);

	List<User> getUserList(@Param("param")ParamDO param);
}
