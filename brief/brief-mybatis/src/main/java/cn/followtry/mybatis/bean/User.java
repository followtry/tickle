/**
 * 
 */
package cn.followtry.mybatis.bean;

import java.util.Date;

/**
 * 
 * @author jingzz
 * @time 2016年10月18日 下午3:05:28
 * @name brief-example/cn.jingzztech.prac.org.mybatis.bean.User
 * @since 2016年10月18日 下午3:05:28
 */
public class User{

	private Long id;
	
	private String name;
	
	private Integer age;
	
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
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
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", date=" + date + "]";
	}
	
}
