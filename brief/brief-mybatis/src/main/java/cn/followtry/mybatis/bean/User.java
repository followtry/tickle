/**
 * 
 */
package cn.followtry.mybatis.bean;

import com.google.common.collect.Lists;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

	private List<Integer> ids;
	
	public User() {
	}
	
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
	
	public static Integer haha = 3;
	public static String[] arr = {"123","456","789"};
	public static List list = Lists.newArrayList("a","b","c");

	public static Map map = new HashMap(){{
		put("123","followtry");
		put("4567","jing1");
		put("1239","jing2");
	}};
	
	public static String name(){
		Objects.equals(1,2);
		return "jingzhongzhi";
	}

	public String name2(){
		return "jingzhongzhi2";
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
}
