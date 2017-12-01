package cn.followtry.validation.base.validation;

import cn.followtry.validation.base.common.exception.ValidationException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * Bean 属性约束校验器.
 *
 * @author haiq
 * @author followtry
 */
public class BeanPropertyValidator implements ConstraintValidator {

  /**
   * 创建指定属性的约束校验器.
   *
   * <p>如果指定属性未定义约束，则返回 null；
   *
   * @param propertyDesc 属性描述
   * @return Bean属性校验器
   */
  public static BeanPropertyValidator create(PropertyDescriptor propertyDesc) {
    Method mth = propertyDesc.getReadMethod();
    Annotation[] annos = mth.getAnnotations();
    Class<?> propType = propertyDesc.getPropertyType();

    List<ConstraintValidator> validators = new LinkedList<>();

    BaseTypeValidatorChain propValidatorChain = null;
    //注解不为空时，为bean的属性创建校验链
    if (annos != null) {
      propValidatorChain = BaseTypeValidatorChain.create(propType, annos);
    }
    if (propValidatorChain != null) {
      validators.add(propValidatorChain);
    }

    if (!AbstractTypeUtils.isBaseType(propType)) {
      BeanValidator propBeanValidator = BeanValidator.create(propType);
      if (propBeanValidator != null) {
        validators.add(propBeanValidator);
      }
    }

    if (validators.size() == 0) {
      return null;
    }
    return new BeanPropertyValidator(propertyDesc, validators.toArray(new
            ConstraintValidator[validators.size()]));
  }

  private PropertyDescriptor propertyDescriptor;

  private ConstraintValidator[] validatorChain;

  /**
   * 构造函数.
   */
  private BeanPropertyValidator(PropertyDescriptor propertyDescriptor, ConstraintValidator[]
          validatorChain) {
    this.propertyDescriptor = propertyDescriptor;
    this.validatorChain = validatorChain;
  }


  @Override
  public void check(Object value) throws ValidationException {
    Object propValue = getPropertyValue(value);
    for (ConstraintValidator validator : validatorChain) {
      validator.check(propValue);
    }
  }

  private Object getPropertyValue(Object obj) {
    try {
      //通过属性的get方法获取到属性的值
      return propertyDescriptor.getReadMethod().invoke(obj, (Object[]) null);
    } catch (IllegalAccessException e) {
      throw new IllegalStateException(e.getMessage(), e);
    } catch (IllegalArgumentException e) {
      throw new IllegalStateException(e.getMessage(), e);
    } catch (InvocationTargetException e) {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }

}
