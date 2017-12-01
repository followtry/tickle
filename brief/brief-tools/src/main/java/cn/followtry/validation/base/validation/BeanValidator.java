package cn.followtry.validation.base.validation;

import cn.followtry.validation.base.common.exception.ValidationException;
import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.BeanUtils;

/**
 * Java Bean 校验器.
 *
 * @author haiq
 * @author followtry
 */
public class BeanValidator implements ConstraintValidator {

  /**
   * Bean 类型跟踪表；用于跟踪在解析过程中涉及的 Bean 类型，避免出现循环解析.
   */
  private static ThreadLocal<Set<Class<?>>> beanTraceMap = new ThreadLocal<>();

  //TODO: 缓存 Bean 校验器；
  /**
   * BeanValidator 的注册表，维护了特定类型的 Bean 的校验器缓存.
   */
  @SuppressWarnings("unused")
  private static Map<Class<?>, BeanValidator> beanValidatorRegisterMap = new
          ConcurrentHashMap<Class<?>, BeanValidator>();

  /**
   * 创建 BeanValidator 的实例.
   *
   * <p>如果指定的类型不是一个符合 Java Bean 规范的类型，或者其没有标注了约束的属性，则返回 null；
   *
   * @param beanType Bean的类型
   * @return Bean校验器
   */
  public static BeanValidator create(Class<?> beanType) {
    // 如果参数的类型不是基本类型及其对应的包装类，则尝试创建 Bean 校验器；
    if (AbstractTypeUtils.isBaseType(beanType)) {
      return null;
    }
    if (beanType.getName().startsWith("java.")) {
      return null;
    }
    // 跟踪解析路径，防止循环依赖；
    trace(beanType);
    try {
      LinkedList<BeanPropertyValidator> propValidators = new LinkedList<BeanPropertyValidator>();

      PropertyDescriptor[] propDescriptors = BeanUtils.getPropertyDescriptors(beanType);
      for (PropertyDescriptor propDesc : propDescriptors) {
        BeanPropertyValidator propValidator = BeanPropertyValidator.create(propDesc);
        if (propValidator != null) {
          propValidators.add(propValidator);
        }
      }
      if (propValidators.size() == 0) {
        return null;
      }
      BeanPropertyValidator[] propValidatorChain = propValidators.toArray(new
              BeanPropertyValidator[propValidators.size()]);
      return new BeanValidator(beanType, propValidatorChain);
    } finally {
      // 移除跟踪信息；
      detrace(beanType);
    }
  }

  /**
   * 在上下文的解析链中跟踪指定的 bean 类型是否已经解析，目的是避免循环解析.
   *
   * @param beanType Bean的类型
   */
  private static void trace(Class<?> beanType) {
    Set<Class<?>> beanSet = beanTraceMap.get();
    if (beanSet == null) {
      beanSet = new HashSet<Class<?>>();
      beanTraceMap.set(beanSet);
    }
    if (beanSet.contains(beanType)) {
      throw new IllegalStateException("There are cycle dependencies for the constraint validation of bean[" + beanType.getName() + "]!");
    }
    beanSet.add(beanType);
  }

  /**
   * 移除上下文的解析链中的跟踪信息.
   *
   * @param beanType Bean的类型
   */
  private static void detrace(Class<?> beanType) {
    Set<Class<?>> beanSet = beanTraceMap.get();
    if (beanSet != null) {
      beanSet.remove(beanType);
    }
  }

  @SuppressWarnings("unused")
  private Class<?> beanType;

  private BeanPropertyValidator[] propValidators;

  private BeanValidator(Class<?> beanType, BeanPropertyValidator[] propValidators) {
    this.beanType = beanType;
    this.propValidators = propValidators;
  }


  @Override
  public void check(Object arg) throws ValidationException {
    for (BeanPropertyValidator propValidator : propValidators) {
      propValidator.check(arg);
    }
  }

}
