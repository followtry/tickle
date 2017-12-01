package cn.followtry.java8.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * 集合流式使用的样例Model
 * Created by followtry on 2017/4/10.
 */
public class User {

	enum SEX{
		/**男*/
		FEMALE("女"),
		/**女*/
		MALE("男");

		String desc;
		SEX(String desc){
			this.desc=desc;}

		public String getDesc() {
			return desc;
		}
	}

	private Integer id;

	private String name;

	private Integer age;

	private List<String> hobbies;

	private SEX sex;

	public User() {
	}

	public User(Integer id, String name, Integer age, List<String> hobbies, SEX sex) {
		this.id=id;
		this.name=name;
		this.age=age;
		this.hobbies=hobbies;
		this.sex=sex;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id=id;
	}

	public SEX getSex() {
		return sex;
	}

	public void setSex(SEX sex) {
		this.sex=sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name=name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age=age;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies=hobbies;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", hobbies=" + hobbies +
				", sex=" + sex.getDesc() +
				'}';
	}

	static class Average {

		List<User> users=new ArrayList<>();

		public void accept(User user) {
			users.add(user);
		}

	}


}
