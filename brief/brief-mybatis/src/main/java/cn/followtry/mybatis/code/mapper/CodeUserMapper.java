/**
 * 
 */
package cn.followtry.mybatis.code.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import cn.followtry.mybatis.bean.User;

/**
 * @author jingzz
 * @time 2016年10月18日 下午3:01:35
 * @name brief-example/cn.jingzztech.prac.org.mybatis.UserMapper
 * @since 2016年10月18日 下午3:01:35
 */
@Mapper
public interface CodeUserMapper {
	
	@Select("select * from user where id = #{id}")
	User getUserById(Long id);
}
