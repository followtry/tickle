/**
 * 
 */
package cn.jingzztech.prac.mybatis;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jingzztech.prac.mybatis.bean.UserBean;
import cn.jingzztech.prac.mybatis.mapper.MybatisUsersMapper;

/**
 * @author jingzz
 * @time 2016年2月22日 上午10:40:26
 * @func 
 * @name service
 */
@Service
public class UserService {
	
	@Resource
	private MybatisUsersMapper usersMapper;
	
	public  List<UserBean> selectAll(){
		List<UserBean> userBeans = usersMapper.selectAll();
		return userBeans;
	}
}
  