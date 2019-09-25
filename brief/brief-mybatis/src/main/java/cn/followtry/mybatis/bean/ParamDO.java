/**
 * 
 */
package cn.followtry.mybatis.bean;

import java.util.List;

/**
 * 
 * @author jingzz
 * @time 2016年10月18日 下午3:05:28
 * @name brief-example/cn.jingzztech.prac.org.mybatis.bean.User
 * @since 2016年10月18日 下午3:05:28
 */
public class ParamDO {

	private List<Long> id;

	private User user;

	public List<Long> getId() {
		return id;
	}

	public void setId(List<Long> id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
