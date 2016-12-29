package cn.followtry.kafka.executor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * brief-kafka/cn.followtry.kafka.executor.MsgBody
 * 
 * @author jingzz
 * @since 2016年12月29日 下午7:31:09
 */
public class MsgBody {

	private String type;

	private String methodName;

	@SuppressWarnings("rawtypes")
	private Class[] argsType;

	private Object[] argsValue;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		if (methodName != null) {
			this.methodName = methodName.substring(methodName.lastIndexOf(".") + 1);
		}
	}

	@SuppressWarnings("rawtypes")
	public Class[] getArgsType() {
		return argsType;
	}

	@SuppressWarnings("rawtypes")
	public void setArgsType(Class... argsType) {
		this.argsType = argsType;
	}

	public Object[] getArgsValue() {
		return argsValue;
	}

	public void setArgsValue(Object... argsValue) {
		this.argsValue = argsValue;
	}

	public void sayHello(String name, String remark) {
		System.out.println("hello " + name + ",remark=" + remark);
	}

	public static void main(String[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException,
			ClassNotFoundException, NoSuchMethodException, SecurityException {

		MsgBody msgBody = new MsgBody();
		msgBody.setType(HelloBean.class.getName());
		msgBody.setMethodName("cn.followtry.kafka.executor.HelloBean.sayHello");
		msgBody.setArgsType(String.class, Integer.class);
		msgBody.setArgsValue("荆中志", 24);
		Object target = Class.forName(msgBody.getType()).newInstance();
		Method method = target.getClass().getMethod(msgBody.getMethodName(), msgBody.getArgsType());
		method.invoke(target, msgBody.getArgsValue());

	}
}
