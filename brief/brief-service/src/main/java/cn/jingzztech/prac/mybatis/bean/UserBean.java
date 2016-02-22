/**
 * 
 */
package cn.jingzztech.prac.mybatis.bean;

import java.util.Date;

/**
 * @author jingzz
 * @time 2016年2月22日 上午10:32:34
 * @func 
 * @name UserBean
 */
public class UserBean extends BaseBean{

	private static final long serialVersionUID = -8630379911948752597L;

	private int id;
	
	private String name;
	
	private int age;
	
	private Date date;

	public UserBean(int id, String name, int age, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.date = date;
	}

	public UserBean() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", name=" + name + ", age=" + age + ", date=" + date + "]";
	}
}
