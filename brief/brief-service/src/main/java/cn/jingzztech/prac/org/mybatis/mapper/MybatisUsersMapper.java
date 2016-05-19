/**
 * 
 */
package cn.jingzztech.prac.org.mybatis.mapper;

import java.util.List;

import cn.jingzztech.prac.org.mybatis.bean.UserBean;

/**
 * Mybatis测试程序
 * @author jingzz
 * @time 2016年2月22日 上午10:29:02
 * @func 
 * @name MybatisDao
 */
public interface MybatisUsersMapper{

	List<UserBean> selectAll(); 
}
