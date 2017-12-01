/**
 * 
 */
package cn.followtry.validation.base.validation;

import java.lang.reflect.Field;
import java.util.Date;

import cn.followtry.validation.base.common.exception.ValidationException;
import cn.followtry.validation.base.stereotype.validation.CustomConstraintHandle;

/**
 * 对时间类型的校验器
 * @author followtry 作者
 * @since 2016年3月17日 下午4:01:23 时间
 */
public class DateValidation implements CustomConstraintHandle {


    @Override
	public void check(String name, String[] args, Object value) {
		doCheck(name,args,value);
	}
	 

	/**
	 * 校验开始时间和结束时间
	 * @param name 指定显式的名称
	 * @param args 包含属性值只能是包含start和end字符串的时间属性值
	 * @param value
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private void doCheck(String name, String[] args, Object value){
		String arg1 = args[0];
		String arg2 = args[1];
		@SuppressWarnings("rawtypes")
		Class clazz = value.getClass();
		Field[] fields = clazz.getDeclaredFields();
		
		/** 获取两个属性值 **/
		Field firstField = null;
		Field secondFirld = null;
		
		//获取开始时间属性
		for (Field field : fields) {
			String fieldType = field.getType().getSimpleName();
			String dateType = Date.class.getSimpleName();
			if (field.getName().equals(arg1) && field.getName().toLowerCase().contains("start")&& dateType.equals(fieldType)) {
				firstField = field;
				break;
			}
		}
		
		//获取结束时间属性
		for (Field field : fields) {
			//判断开始时间属性
			String fieldType = field.getType().getSimpleName();
			String dateType = Date.class.getSimpleName();
			if (field.getName().equals(arg2) && field.getName().toLowerCase().contains("end") && dateType.equals(fieldType)) {
				secondFirld = field;
				
				break;
			}
		}
		
		//在属性值不为空时，才判断开始时间和结束时间的大小
		if (firstField != null && secondFirld != null) {
			firstField.setAccessible(true);
			secondFirld.setAccessible(true);
			Date startDate;
			try {
				startDate = (Date)firstField.get(value);
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				throw new ValidationException(500, firstField.getName()+"参数访问异常"+e1.getMessage());
			}
			Date endDate;
			try {
				endDate = (Date)secondFirld.get(value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ValidationException(500, secondFirld.getName()+"参数访问异常"+e.getMessage());
			}
			if (endDate != null && startDate != null && endDate.before(startDate)) {
				throw new ValidationException(4002, String.format("校验失败：%s对象的%s值要比%s值大", name,secondFirld.getName(),firstField.getName()));
			}
			
		}
		
	}

}
