/**
 * 
 */
package cn.jingzztech.prac.mybatis.mapper;

import java.util.List;

import cn.jingzztech.prac.mybatis.bean.UserBean;

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
