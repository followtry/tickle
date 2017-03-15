package cn.followtry.incubate.java.annotation;

/**
 * Created by followtry on 2017/3/15.
 */
@FirstAnno(value = "userController")
public class UserController {
	String userName;

	String userId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName=userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId=userId;
	}
}
